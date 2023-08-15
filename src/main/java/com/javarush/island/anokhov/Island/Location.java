package com.javarush.island.anokhov.Island;

import com.javarush.island.anokhov.nature.Animals.Animal;
import com.javarush.island.anokhov.nature.Nature;

import java.awt.desktop.PreferencesEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.javarush.island.anokhov.constants.ApplicationCommunication.locationInfo;

public class Location {



    private List <Nature> natures = Collections.synchronizedList( new ArrayList<>());
     private  List<Animal> animals = Collections.synchronizedList( new ArrayList<>());

   synchronized public void comeIn(Nature animal){
        natures.add(animal);
        if (animal instanceof Animal animal1){
            animals.add(animal1);
        }
    }
  synchronized   public void remove (Nature animalOrPlant){
        natures.remove(animalOrPlant);
      if (animalOrPlant instanceof Animal animal1){
          animals.remove(animal1);
      }
    }
   synchronized public void setNatures(List<Nature> natures) {
        this.natures = natures;
    }
   synchronized public List<Nature> getNatures() {
        return natures;
    }
   synchronized public List<Animal> getAnimals(){
       return animals;
    }


    public List<Nature> getTypeOfNatures (Nature nature){
        List<Nature> result = new ArrayList<>();
        for (Nature findNature : natures){
            if (findNature.getClass().getSimpleName().equalsIgnoreCase(nature.getClass().getSimpleName())){
                result.add(findNature);
            }
        }
        return result;
    }
}
