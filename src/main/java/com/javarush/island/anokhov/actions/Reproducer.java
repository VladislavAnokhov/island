package com.javarush.island.anokhov.actions;

import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.nature.Animals.Predator;
import com.javarush.island.anokhov.statistics.Statistics;
import com.javarush.island.anokhov.constants.animalsBasic.Basic;
import com.javarush.island.anokhov.constants.animalsBasic.BasicList;
import com.javarush.island.anokhov.nature.Animals.Animal;
import com.javarush.island.anokhov.nature.Animals.Herbivores.*;
import com.javarush.island.anokhov.nature.Animals.Predators.*;
import com.javarush.island.anokhov.nature.Nature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.anokhov.constants.Animals.*;

public class Reproducer implements Callable<Void> {
    private Animal firstParent;
    private Animal secondParent;
    private Location location;
    private List<Nature> naturesForSynch = new ArrayList<>();
    private boolean permissionForReproduce = true;


    @Override
    public synchronized Void call() {
        if (!permissionForReproduce) {
            return null;
        }
        int fromHash = System.identityHashCode(firstParent);
        int toHash = System.identityHashCode(secondParent);
        if (fromHash < toHash) {
            synchronized (firstParent) {
                synchronized (secondParent) {
                    if (secondParent == null) {
                        return null;
                    }
                    reproduce();
                }
            }
        }

        else if (fromHash > toHash) {
            synchronized (secondParent) {
                synchronized (firstParent) {
                    if (secondParent == null) {
                        return null;
                    }
                    reproduce();

                }
            }
        }
        return null;
    }
    public Reproducer(Animal firstParent){

        this.firstParent = firstParent;
        location = firstParent.getLocation();
        for (Nature nature : location.getNatures()){
            if (nature!=null &&nature.getClass().getSimpleName().equalsIgnoreCase(nature.getClass().getSimpleName())){
            naturesForSynch.add(nature);}
        }
        checkLocationOnQuantity ();
        if(!permissionForReproduce){
            return;
        }
        secondParent=findSecondParent(firstParent);
    }

    public  void   reproduce() {
        checkLocationOnQuantity ();
        if(!permissionForReproduce){
            return;
        }
            if (secondParent.getReproduceCounter()==0){
                return ;
            }
            if (firstParent.getReproduceCounter()==0){
                return ;}
            newBaby(firstParent, secondParent);


    }

    public synchronized Animal findSecondParent (Animal animal) {
        List<Animal> futureParents = new ArrayList<>(); //массив выбора будущего второго родителя
        synchronized (naturesForSynch) {
            for (Nature nature : naturesForSynch) {
                if (nature instanceof Animal){
                secondParent = (Animal) nature;

             if (animal.getClass().getSimpleName().equalsIgnoreCase(secondParent.getClass().getSimpleName()) && secondParent.getReproduceCounter() != 0) {
                    futureParents.add(secondParent);
                }
            }
            }

            int randomSecondParent =0;
           if (futureParents.size()>1){
               randomSecondParent = ThreadLocalRandom.current().nextInt(0, futureParents.size());
            }
           else if (futureParents.size()==0){
              return null;
           }
           else randomSecondParent=0;

            return futureParents.get(randomSecondParent);
        }
    }

     synchronized void newBaby(Animal parent1, Animal parent2) {
         checkLocationOnQuantity ();
         if(!permissionForReproduce){
             return;
         }
        parent1.setReproduceCounter(0);
         parent2.setReproduceCounter(0);
        String result = "";
        Basic base =null;
        Nature newNature =null;
        for (int i = 0; i < BasicList.basicAnimalsList.size(); i++) {
            base = BasicList.basicAnimalsList.get(i);
            if (base.getName().equalsIgnoreCase(parent1.getClass().getSimpleName())) {
                result = base.getName();
            }
        }
        switch (result) {
            case wolf -> {
                newNature = new Wolf("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }

            }
            case sheep -> {
                newNature = new Sheep("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());

                synchronized (location) {
                    setLocation(newNature);
                }

            }
            case mouse -> {
                newNature = new Mouse("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case bear -> {
                newNature = new Bear("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case boa -> {
                newNature = new Boa("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case boar -> {
                newNature = new Boar("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case buffalo -> {
                newNature = new Buffalo("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case caterpillar -> {
                newNature = new Caterpillar("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case deer -> {
                newNature = new Deer("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case duck -> {
                newNature = new Duck("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case eagle -> {
                newNature = new Eagle("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case fox -> {
                newNature = new Fox("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case goat -> {
                newNature = new Goat("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
            case horse -> {
                newNature = new Horse("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }

            case rabbit -> {
                newNature = new Rabbit("babyOf" + parent1.getName() + "+" + parent2.getName(),
                        base.getMaxQuantity(), base.getWeight(), base.getSpeed(), 0,
                        base.getWellFed());
                synchronized (location) {
                    setLocation(newNature);
                }
            }
        }
        if (newNature instanceof Predator){
            synchronized (Statistics.predatorsList){Statistics.setPredatorsList(newNature);}
        }
        else synchronized (Statistics.herbivoresList){Statistics.setHerbivoresList(newNature);}

        synchronized (Statistics.wasBorn){
         Statistics.setWasBorn(newNature);}
        }

        private synchronized void setLocation(Nature nature){
            firstParent.getLocation().comeIn(nature);
            nature.setLocation(firstParent.getLocation());
        }


        synchronized private void checkLocationOnQuantity () {
            Basic base = null;
              int counterNatureType = 0;
            for (Nature nature : firstParent.getLocation().getNatures()) {
                if (firstParent.getClass().getSimpleName().equalsIgnoreCase(nature.getClass().getSimpleName())) {
                    counterNatureType++;
                }
            }
            for (int i = 0; i < BasicList.basicAnimalsList.size(); i++) {
                base = BasicList.basicAnimalsList.get(i);
                if (base.getName().equalsIgnoreCase(firstParent.getClass().getSimpleName())) {
                    if (base.getMaxQuantity() > counterNatureType) {
                        permissionForReproduce = true;

                    } else{

                        permissionForReproduce = false;
                    }
                }
            }
        }

}
