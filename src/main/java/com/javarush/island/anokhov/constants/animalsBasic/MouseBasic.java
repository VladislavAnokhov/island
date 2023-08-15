package com.javarush.island.anokhov.constants.animalsBasic;

import com.javarush.island.anokhov.nature.Animals.Herbivores.Deer;
import com.javarush.island.anokhov.nature.plants.Plant;

import java.util.HashMap;

import static com.javarush.island.anokhov.constants.Animals.*;
import static com.javarush.island.anokhov.constants.OtherConstants.*;
import static com.javarush.island.anokhov.constants.OtherConstants.maxQuantityString;

public class MouseBasic implements Basic{

    static private int maxQuantity =  500; //500
    static private Double weight = 0.05;
    static private int speed = 1;
    static private Double wellFed = 0.01;
    static private String name = mouse;
    static private int reproduceCounter = 1;
    static public HashMap<String, Integer> menu = new HashMap<>();

    static  {
        menu.put(plant,100);
        menu.put(caterpillar,90);
    }

    static public HashMap<String,Object> basicInfo = new HashMap<>();

    static {
        basicInfo.put(weightString,weight);
        basicInfo.put(speedString,speed);
        basicInfo.put(wellFedString,wellFed);
        basicInfo.put(maxQuantityString,maxQuantityString);
        basicInfo.put(nameString,name);
    }
     public static HashMap<String, Integer> getMenu(){

        return  menu;
    }
    @Override
     public int getMaxQuantity() {
        return maxQuantity;
    }
    @Override
     public Double getWeight() {
        return weight;
    }
    @Override
     public int getSpeed() {
        return speed;
    }
    @Override
    public Double getWellFed() {
        return wellFed;
    }

    @Override
    public int getReproduceCounter() {
        return reproduceCounter;
    }

    @Override
    public String getName() {
        return name;
    }
}


