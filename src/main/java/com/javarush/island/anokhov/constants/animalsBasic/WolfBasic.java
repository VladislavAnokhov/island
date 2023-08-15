package com.javarush.island.anokhov.constants.animalsBasic;

import com.javarush.island.anokhov.nature.Animals.Herbivores.*;

import javax.swing.*;
import java.util.HashMap;

import static com.javarush.island.anokhov.constants.Animals.*;
import static com.javarush.island.anokhov.constants.OtherConstants.*;

public class WolfBasic implements Basic{
   static private int maxQuantity = 30;//30
   static private Double weight = 50.0;
   static private int speed = 3;
   static private Double wellFed = 8.0;
   static private int reproduceCounter = 1;
    static private String name = wolf;
    static public HashMap<String,Object> basicInfo = new HashMap<>();
   static public HashMap<String, Integer> menu = new HashMap<>();

   static  {
        menu.put(horse,10);
        menu.put(deer,15);
        menu.put(rabbit,60);
        menu.put(mouse,80);
        menu.put(goat,60);
        menu.put(sheep,70);
        menu.put(boa,15);
        menu.put(buffalo,10);
        menu.put(deer,40);
    }

    static {
        basicInfo.put(weightString,weight);
        basicInfo.put(speedString,speed);
        basicInfo.put(wellFedString,wellFed);
        basicInfo.put(maxQuantityString,maxQuantityString);
        basicInfo.put(nameString,name);
    }

   static public HashMap<String, Integer> getMenu(){
       return  menu;
   }

   @Override
    public  String getName() {
        return name;
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
}
