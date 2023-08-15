package com.javarush.island.anokhov.actions;

import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.statistics.Statistics;
import com.javarush.island.anokhov.constants.animalsBasic.BasicList;
import com.javarush.island.anokhov.nature.Animals.Animal;
import com.javarush.island.anokhov.nature.Nature;

import java.util.concurrent.Callable;

public class Refresher implements Callable<Void> {

    private Location location;
    @Override
    public Void call() {
        refresh();
        return null;
    }
   public Refresher(Location location){
        this.location=location;
    }

    public synchronized  void refresh(){
      //  System.out.println("===============REFRESHER================");

        double maxWellFed = 0.0;
      //  synchronized (location.getAnimals()){
            //for (Animal animalNature : location.getAnimals()) {
    //    System.out.println(location.getNatures().size()+" сколько животных в локации" );
        double resultWellFed=0.0;
        Animal animalNature=null;
        double wellFed = 0.0;
        int maxSpeed = 0;
        int maxReproduct = 0;
        int j = 0;
        for (; j < location.getNatures().size()+1; j++) {
            Nature nature = location.getNatures().get(j);
           animalNature = (Animal) nature;
            for (int i = 0; i < BasicList.basicAnimalsList.size(); i++) {

                if (BasicList.basicAnimalsList.get(i).getName().equalsIgnoreCase(animalNature.getClass().getSimpleName())) {
                    maxWellFed = BasicList.basicAnimalsList.get(i).getWellFed();
                    maxSpeed = BasicList.basicAnimalsList.get(i).getSpeed();
                    maxReproduct = BasicList.basicAnimalsList.get(i).getReproduceCounter();

                }
            }
            resultWellFed  = animalNature.getWellFed() - maxWellFed * 0.20;

                 if (resultWellFed <= 0) {
                     j--;
                    animalNature.die();
                   Statistics.TESTHUNGRY.add(animalNature);
                   // synchronized (Statistics.hungryDeath) {

                  //      Statistics.setHungryDeath(animalNature);
                  //  }
                   // synchronized (Statistics.hungryDeathDaily){

                        Statistics.setHungryDeathDaily(animalNature);
                   // }
                } else if (resultWellFed>0) {
                     animalNature.setWellFed(resultWellFed);      // голод скосил после всех действий
                     animalNature.setSpeed(maxSpeed);            // возврат скорости
                     animalNature.setReproduceCounter(maxReproduct); //возврат репродукции
                     //        }
                 }
        }
    }
}
