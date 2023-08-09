package com.javarush.island.anokhov.constants.animalsBasic;

import java.util.HashMap;

import static com.javarush.island.anokhov.constants.Animals.*;
import static com.javarush.island.anokhov.constants.OtherConstants.*;

public class BoarBasic implements Basic {
    static private int maxQuantity =  50;
    static private Double weight = 400.0;
    static private int speed = 2;
    static private Double wellFed = 50.0;
    static private String name = boar;
    static private int reproduceCounter = 1;
    static public HashMap<String, Integer> menu = new HashMap<>();

    static  {
        menu.put(mouse,50);
        menu.put(caterpillar,90);
        menu.put(plant,100);
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
