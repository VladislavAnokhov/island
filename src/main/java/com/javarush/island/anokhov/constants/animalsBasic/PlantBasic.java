package com.javarush.island.anokhov.constants.animalsBasic;

import java.util.HashMap;

import static com.javarush.island.anokhov.constants.Animals.*;
import static com.javarush.island.anokhov.constants.Animals.deer;
import static com.javarush.island.anokhov.constants.OtherConstants.*;

public class PlantBasic implements Basic{
   static private Double weight = 1.0;

   static private int  maxQuantity = 1000;//1000


    static private String name = plant;
    static public HashMap<String, Integer> menu = new HashMap<>();
    static  {

    }
    static public HashMap<String,Object> basicInfo = new HashMap<>();
    static {
        basicInfo.put(weightString,weight);
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
        return 0;
    }

    @Override
    public Double getWellFed() {
        return null;
    }

    @Override
    public int getReproduceCounter() {
        return 0;
    }


}
