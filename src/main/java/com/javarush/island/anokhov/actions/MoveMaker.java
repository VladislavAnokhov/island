package com.javarush.island.anokhov.actions;

import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.constants.animalsBasic.Basic;
import com.javarush.island.anokhov.constants.animalsBasic.BasicList;
import com.javarush.island.anokhov.constants.animalsBasic.BearBasic;
import com.javarush.island.anokhov.nature.Animals.Predators.Wolf;
import com.javarush.island.anokhov.statistics.Statistics;
import com.javarush.island.anokhov.nature.Nature;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.javarush.island.anokhov.constants.ApplicationCommunication.drowned;
import static com.javarush.island.anokhov.constants.ApplicationCommunication.mainWelcome;


//доработать движение , interupt если тонет животное

public class MoveMaker implements Callable<Void> {
     private Island island;
     private int direction;
     private Location location;
     private int speed;
     private Nature nature;
     static int [] keys;
     private Location neighbourUpLocation ;
     private Location neighbourRightLocation ;
     private Location neighbourDownLocation ;
     private Location neighbourLeftLocation ;


    public MoveMaker(int speed, Nature nature) {
        this.nature = nature;
        this.speed = ThreadLocalRandom.current().nextInt(0, speed + 1);
        if (this.speed == 0) {

        }

        }

    @Override
    public Void call()  {
        while (speed>0) {            // проверка скорости и проверка успешности передвижения
            move();
            clean();
        }
        return null;
    }

    public boolean move() {
        boolean result = true;
        direction = ThreadLocalRandom.current().nextInt(1, 5);
        location = island.findLocation(nature.getLocation());    // поиск локации
        location.remove(nature);                     //убрал из локации
        Desk desk = new Desk();
        switch (direction){
           case  1-> {
          //     System.out.println(nature.getName()+" пошел наверх");
             if (desk.moveUp(nature, location)){
                 synchronized (neighbourUpLocation){
                     neighbourUpLocation.comeIn(nature); //передача в локацию
                nature.setLocation(neighbourUpLocation);}
           }else { return false;
             }
           }

           case  2->{
          //   System.out.println(nature.getName()+" пошел вправо");
            if (desk.moveRight(nature, location)){
                   synchronized (neighbourRightLocation){
                       neighbourRightLocation.comeIn(nature);
               nature.setLocation(neighbourRightLocation);}
            }else { return false;
            }
           }
           case  3-> {
          //    System.out.println(nature.getName()+" пошел вниз");
               if (desk.moveDown(nature, location)){
                   synchronized (neighbourDownLocation){
                       neighbourDownLocation.comeIn(nature);
               nature.setLocation(neighbourDownLocation); }
               } else{ return false;
               }
           }

           case  4->{
         //     System.out.println(nature.getName()+" пошел влево");
               if (desk.moveLeft(nature, location)) {
                synchronized (neighbourLeftLocation) {
                    neighbourLeftLocation.comeIn(nature);
                    nature.setLocation(neighbourLeftLocation);
                }
           } else {return false;}
           }
        }
        return result;
    }

    public void  setIsland(Island island){
        this.island=island;
    }
    public void clean(){
        for (int i = 0; i < island.getMassive().length; i++) {
            for (int j = 0; j < island.getMassive()[i].length; j++) {
                Location location = island.getMassive()[i][j];
                List<Nature> clearNatures =  location.getNatures().stream().filter(e->e!=null).collect(Collectors.toList());
                    location.setNatures(clearNatures);
            }
        }
    }

    /**Класс для управления передвижения.
     * В себе содержит проверку на вместимость клетки.
     * Если клетка переполнена, выдаст false и можно будет
     * заново пройти цикл передвижения*/
    private  class Desk {
        public  boolean moveUp(Nature nature, Location location) {
            boolean result = true;
            keys=new int[3];
            for (int i = 0; i < island.getMassive().length; i++) {
                for (int j = 0; j < island.getMassive()[i].length; j++) {
                    if (island.getMassive()[i][j] == location) {
                        if (i==0){
                            drown(nature);
                            result= false;
                       }
                        else {
                            neighbourUpLocation=island.getMassive()[i-1][j];
                          if (checkLocationOnQuantity(neighbourUpLocation)){
                              speed--;
                              result= true;
                          }
                          else { location.comeIn(nature);
                              result= false;}
                        }
                   }
                }
            }
            return result;
        }

        public boolean moveDown(Nature nature, Location location){
            boolean result = true;
            keys=new int[3];
            for (int i = 0; i < island.getMassive().length; i++) {
                for (int j = 0; j < island.getMassive()[i].length; j++) {
                    if (island.getMassive()[i][j] == location) {
                        if (i==island.getMassive().length-1){
                            drown(nature);
                            result= false;
                        }
                        else {
                            neighbourDownLocation = island.getMassive()[i + 1][j];
                            if (checkLocationOnQuantity(neighbourDownLocation)) {
                                speed--;
                                result= true;
                            } else { location.comeIn(nature);
                                result= false;
                            }
                        }
                    }
                }
            }
            return result;
        }

        public boolean moveRight(Nature nature, Location location){
            boolean result = true;
            keys=new int[3];
            for (int i = 0; i < island.getMassive().length; i++) {
                for (int j = 0; j < island.getMassive()[i].length; j++) {
                    if (island.getMassive()[i][j] == location) {            //получение координат локации
                        if (j == island.getMassive()[i].length - 1) {            //если оно стоит на краю то смысла больше нет, срауз бан XD
                            drown(nature);
                            result= false;
                        } else {
                            neighbourRightLocation = island.getMassive()[i][j + 1];
                            if (checkLocationOnQuantity(neighbourRightLocation)) {
                                speed--;
                                result= true;
                            } else { location.comeIn(nature);
                                result= false;
                            }
                        }
                    }
                }
            }
            return result;
        }

        public boolean moveLeft(Nature nature, Location location) {
            boolean result = true;
            keys=new int[3];
            for (int i = 0; i < island.getMassive().length; i++) {
                for (int j = 0; j < island.getMassive()[i].length; j++) {
                    if (island.getMassive()[i][j] == location) {
                        if (j == 0) {
                            drown(nature);
                            result= false;
                        }
                        else {
                            neighbourLeftLocation=island.getMassive()[i][j-1];
                           if (checkLocationOnQuantity(neighbourLeftLocation)) {
                               speed--;
                               result = true;
                           } else { location.comeIn(nature);
                               result= false;
                           }
                        }
                    }
                }
            }
            return result;
        }
        /** метод топит животных,
         * обнуляя скорость
         * */
        public void drown(Nature nature){

            nature.die();
            speed=0;
            synchronized (Statistics.fall){
            Statistics.setFall(nature);}

        }
        /**проверка вместимости  клетки.
         * не впустит в клетку животное, если оно переполнено,
         * согласно условию*/
        synchronized private boolean checkLocationOnQuantity (Location location) {

            boolean result = true;
            int maxQuantity = 0;
            int counterNatureType =location.getTypeOfNatures(nature).size();

            for (int i = 0; i < BasicList.basicAnimalsList.size(); i++) {
                if (BasicList.basicAnimalsList.get(i).getName().equalsIgnoreCase(nature.getClass().getSimpleName())){
                  maxQuantity= BasicList.basicAnimalsList.get(i).getMaxQuantity();
                }
            }
            if (maxQuantity>counterNatureType){
                return true;
            }
            else {

                return false;
            }

        }

    }
}

