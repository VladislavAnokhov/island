package com.javarush.island.anokhov.controller;

import com.javarush.island.anokhov.actions.DiningRoom;
import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.actions.Refresher;
import com.javarush.island.anokhov.actions.MoveMaker;
import com.javarush.island.anokhov.actions.Reproducer;
import com.javarush.island.anokhov.nature.Animals.Animal;
import com.javarush.island.anokhov.nature.Nature;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.*;



public class Behavior implements Runnable{
   private Island island;
   public List<MoveMaker> moveTasks = new ArrayList<>();
   private List<Reproducer> reproduceTasks = new ArrayList<>();
   private List<DiningRoom> eatTasks = new ArrayList<>();
   private List<Refresher> refresherTasks = new ArrayList<>();
   private boolean workOfSimulation = true;

    @Override
    public void run() {
    stream();
    }
   public Behavior(Island island){
      this.island = island;
   }
   public  void stream(){
 ExecutorService executorServiceForMove = Executors.newWorkStealingPool();
            //  ExecutorService executorServiceForReproduce= new ThreadPoolExecutor(4,5,2,TimeUnit.SECONDS, new ArrayBlockingQueue<>(500000000));


      List<Future<Void>> futures =null;
      try {
          futures =executorServiceForMove.invokeAll(moveStream()) ;
       futures =executorServiceForMove.invokeAll(reproduceStream()) ;
        futures =executorServiceForMove.invokeAll(eatStream()) ;
         futures =executorServiceForMove.invokeAll(refreshStream());
      } catch (InterruptedException e) {
          throw new RuntimeException(e);

      }
       executorServiceForMove.shutdown();
   }


   public  List<MoveMaker> moveStream (){
      for (int i = 0; i < island.getMassive().length; i++) {
         for (int j = 0; j < island.getMassive()[i].length; j++) {
            Location location = island.getMassive()[i][j];
            for (Nature nature : location.getNatures()){
               if (nature instanceof Animal){
                  Animal animal = (Animal) nature;
                  MoveMaker moveMaker = animal.move();
                  moveMaker.setIsland(island);
                  moveTasks.add(moveMaker);
               }
            }
         }
      }
      return moveTasks;
   }

   public List<Reproducer> reproduceStream () {
       for (int i = 0; i < island.getMassive().length; i++) {
           for (int j = 0; j < island.getMassive()[i].length; j++) {
               Location location = island.getMassive()[i][j];
               for (Nature nature : location.getNatures()) {
                   if (nature instanceof Animal) {
                       Animal animal = (Animal) nature;
                        Reproducer reproducer = animal.reproduce();
                        reproduceTasks.add(reproducer);


                   }
               }
           }
       }
       return reproduceTasks;
   }
   private List<DiningRoom> eatStream(){
       for (int i = 0; i < island.getMassive().length; i++) {
           for (int j = 0; j < island.getMassive()[i].length; j++) {
               Location location = island.getMassive()[i][j];
               for (Nature nature : location.getNatures()) {
                   if (nature instanceof Animal) {
                       Animal animal = (Animal) nature;
                       DiningRoom diningRoom = animal.eat();
                       eatTasks.add(diningRoom);
                     }
                   else break;
               }
           }
       }return eatTasks;
   }

   private List<Refresher> refreshStream(){
       for (int i = 0; i < island.getMassive().length; i++) {
           for (int j = 0; j < island.getMassive()[i].length; j++) {
               Location location = island.getMassive()[i][j];
               refresherTasks.add(new Refresher(location));
           }
       }
       return refresherTasks;
    }



}

