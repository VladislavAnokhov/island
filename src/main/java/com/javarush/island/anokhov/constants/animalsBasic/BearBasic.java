package com.javarush.island.anokhov.constants.animalsBasic;

import java.util.HashMap;

import static com.javarush.island.anokhov.constants.Animals.*;
import static com.javarush.island.anokhov.constants.OtherConstants.*;

public class BearBasic implements Basic{
   static private int maxQuantity =  5;
   static   private Double weight = 500.0;
   static private int speed = 2;
   static private Double wellFed = 80.0;
    static private String name = bear;
    static private int reproduceCounter = 1;
    static public HashMap<String, Integer> menu = new HashMap<>();

    static  {
        menu.put(boa,80);
        menu.put(horse,40);
        menu.put(deer,80);
        menu.put(rabbit,80);
        menu.put(mouse,90);
        menu.put(goat,70);
        menu.put(sheep,70);
        menu.put(boar,50);
        menu.put(buffalo,20);
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
