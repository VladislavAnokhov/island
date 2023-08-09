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
        synchronized (location){
        hunger(location);}
        return null;
    }
   public Refresher(Location location){
        this.location=location;
    }

    public synchronized  void hunger(Location location){
        Double maxWellFed = 0.0;

        for (Nature nature : location.getNatures()){
            if (nature instanceof Animal){
               Animal animalNature = (Animal) nature;

               Double wellFed =  animalNature.getWellFed();

                for (int i = 0; i < BasicList.basicAnimalsList.size(); i++) {
                    if (BasicList.basicAnimalsList.get(i).getName().equalsIgnoreCase(animalNature.getClass().getSimpleName())) {
                        maxWellFed = BasicList.basicAnimalsList.get(i).getWellFed();
                    }
                }
                Double resultWellFed =wellFed-maxWellFed*0.20;

                if (resultWellFed<0){

                    animalNature.die();
                    synchronized (Statistics.getHungryDeath()){
                    Statistics.setHungryDeath(animalNature);}
                }
                else {
                    animalNature.setWellFed(resultWellFed);      // голод скосил после всех действий
                    animalNature.setReproduceCounter(1);         // возврат репродуктивной системы

                }
            }
        }
    }
}
