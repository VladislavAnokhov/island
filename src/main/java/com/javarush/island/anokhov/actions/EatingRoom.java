package com.javarush.island.anokhov.actions;


import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.statistics.Statistics;
import com.javarush.island.anokhov.constants.animalsBasic.Basic;
import com.javarush.island.anokhov.constants.animalsBasic.BasicList;
import com.javarush.island.anokhov.nature.Animals.Animal;

import com.javarush.island.anokhov.nature.Nature;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


public  class EatingRoom implements Callable <Void> {
        private Animal animalToEat;
        private Nature animalForDinner;
        private Location location;
        private Double wellFed;
        private HashMap<String,Integer> menu;
        private boolean doNotEat ;
        private List<Nature> getListNatures =  new ArrayList<>();

    @Override
    public Void call() throws Exception {
      //  System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animalToEat.getName()+ " начал обед");
        if (!animalToEat.getIsAlive() && doNotEat){   //проверка на сытость и живое ли животное
            return null;
        }

        // блок для синхронизации
        int fromHash = System.identityHashCode(animalToEat);
        int toHash = System.identityHashCode(animalForDinner);
        if (fromHash < toHash) {
           synchronized (animalToEat) {
                synchronized (animalForDinner) {
                    if (animalForDinner == null) {
                        return null;
                    }
                    timeToEat();
                }
            }
        }

        else if (fromHash > toHash) {
            synchronized (animalForDinner) {
                synchronized (animalToEat) {
                    if (animalForDinner == null) {
                        return null;
                    }
                    timeToEat();

                }
            }
        }
        clean();
        return null;
    }

    public EatingRoom(Animal animal, Location location, Double wellFed){
        animalToEat=animal;
        this.location=location;
        this.wellFed=wellFed;
        menu = animal.getMenu();
        if (isWellFed()){// проверка на сытость
         //   System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animal.getName()+ " сыто");
            doNotEat=true;
        }
        else {
         //   System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animal.getName()+ " другое");
            synchronized (location.getNatures()){
                clean();
                getListNatures.addAll(location.getNatures());
            }
        //    System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animal.getName()+ " поиск добычи");
        animalForDinner=findVictim(animal);

        }
      //  System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animalToEat.getName()+ " конструктор завершен");
    }

        public  void timeToEat () {

           if(!animalForDinner.getIsAlive()){                           //проверка обоих, что живы
               return;
           }
           if(!animalToEat.getIsAlive()){
               return;
           }
          //  System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animalToEat.getName()+ " проверки пройдены начало поедания");
            int chanceToEat = ThreadLocalRandom.current().nextInt( 0,100);    //рандом поесть (поймает добычу или нет)
            if (chanceToEat<menu.get(animalForDinner.getClass().getSimpleName())){
                Double resultWellFed= checkWeight(animalToEat,wellFed + animalForDinner.getWeight());   //проверка, чтобы не поставилось сытость, больше допустимой
                animalToEat.setWellFed(resultWellFed);
               // System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animalForDinner.getName()+" существо съедино им -> " + animalToEat.getName());
                 animalForDinner.die();                                          //добыча поймана, вызов метода на уничтожение
                synchronized (Statistics.wasKilled){
                    Statistics.setWasKilled(animalForDinner);
                }
                synchronized (Statistics.wasKilledDaily){
                    Statistics.setWasKilledDaily(animalForDinner);
                }
            }
            else  {
           //     System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animalToEat+" не удалось поймать - к сытости");
                wellFed= wellFed-wellFed*0.20;                                     // потратил силы на поимку, но неудачно - это минус к сытости
                if (wellFed<0 || wellFed<0.001){
                    animalToEat.die();
                    synchronized (Statistics.hungryDeath){
                        Statistics.setHungryDeath(animalToEat);
                    }
                    synchronized (Statistics.hungryDeathDaily){
                        Statistics.setHungryDeathDaily(animalToEat);
                    }
                }

            }


        }


            /** метод на проверку веса по базе данных*/
        public  Double checkWeight (Animal animal,Double weight){
        Double maxWeight = 0.0;
            for (int i = 0; i < BasicList.basicAnimalsList.size(); i++) {
                if (BasicList.basicAnimalsList.get(i).getName().equalsIgnoreCase(animal.getClass().getSimpleName())){
                    if (BasicList.basicAnimalsList.get(i).getWellFed()<weight){
                        maxWeight=BasicList.basicAnimalsList.get(i).getWellFed();
                    }
                    else {
                        maxWeight=weight;
                    }
                }
            }
        return maxWeight;
        }

        /**метод для поиска добычи в ячейке */
        public  Nature findVictim (Animal animal){
            if(doNotEat){
                return null;
            }
            List<Nature> futureVictims = new ArrayList<>();
            int i = 0;
            for (Nature nature : getListNatures) {
                for (String string : menu.keySet()) {
                    if (string.equalsIgnoreCase(nature.getClass().getSimpleName()) && animal!=nature ) {
                        futureVictims.add(nature);
                        i++;

                    }
                }
            }
            int randomAnimal=0;
            if (futureVictims.size()>1){                                                        //рандомный выбор кого съесть
                randomAnimal = ThreadLocalRandom.current().nextInt(0,futureVictims.size());
            }
            else if (futureVictims.size()==0){
                return null;
            }
            else randomAnimal=0;

            animalForDinner =futureVictims.get(randomAnimal);
          //  System.out.println("поток №"+Thread.currentThread().threadId()+"  "+animalToEat.getName()+ " добычей будет "+ animalForDinner.getName());
            return animalForDinner;
    }

    /**метод для проверки сытости */
    public boolean isWellFed (){
        boolean result=true;
        Double maxWeigh = 0.0;
        for (int i = 0; i < BasicList.basicAnimalsList.size(); i++) {
            if (BasicList.basicAnimalsList.get(i).getName().equalsIgnoreCase(animalToEat.getClass().getSimpleName())) {
                maxWeigh = BasicList.basicAnimalsList.get(i).getWellFed();
            }
        }
        if (Math.abs(maxWeigh - wellFed) < 0.00001)
        {
             result =true;
        }
        else {
            result=false;}
       return result;
    }

    public synchronized void clean() {
        synchronized (location.getNatures()) {
            List<Nature> clearNatures = location.getNatures().stream().filter(Objects::nonNull).filter(Nature::getIsAlive).collect(Collectors.toList());
         //   List<Nature> clearNatures2 = location.getNatures().stream().filter(Objects::nonNull).collect(Collectors.toList());
            location.setNatures(clearNatures);
        }
    }



}
