package com.javarush.island.anokhov.constants.animalsBasic;

import java.util.HashMap;

import static com.javarush.island.anokhov.constants.Animals.*;
import static com.javarush.island.anokhov.constants.Animals.plant;
import static com.javarush.island.anokhov.constants.OtherConstants.*;

public class BuffaloBasic implements Basic{
    static private int maxQuantity =  10;
    static private Double weight = 700.0;
    static private int speed = 3;
    static private Double wellFed = 100.0 ;
    static private String name = buffalo;
    static private int reproduceCounter = 1;
    static public HashMap<String, Integer> menu = new HashMap<>();

    static  {
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
