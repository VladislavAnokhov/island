package com.javarush.island.anokhov.constants.animalsBasic;

import java.util.HashMap;

import static com.javarush.island.anokhov.constants.Animals.*;
import static com.javarush.island.anokhov.constants.OtherConstants.*;

public class BoaBasic implements Basic{
   static private int maxQuantity =  30;//30
   static private Double weight = 15.0;
   static private int speed = 1;
   static private Double wellFed = 3.0;
    static private String name = boa;
    static private int reproduceCounter = 1;
    static public HashMap<String, Integer> menu = new HashMap<>();

    static  {
        menu.put(fox,15);
        menu.put(rabbit,20);
        menu.put(mouse,40);
        menu.put(duck,10);
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
