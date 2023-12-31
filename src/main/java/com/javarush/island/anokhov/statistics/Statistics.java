package com.javarush.island.anokhov.statistics;

import com.javarush.island.anokhov.nature.Nature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Statistics {
    static volatile public List<Nature> TESTHUNGRY = new ArrayList<>();
    static volatile public List<Nature> wasBorn = new ArrayList<Nature>();
    static public List<Nature> wasBornDaily = new ArrayList<>();
    static volatile public List<Nature> wasKilled = new ArrayList<Nature>();
    static public List<Nature> wasKilledDaily = new ArrayList<>();
     static volatile public List<Nature> fall = new ArrayList<Nature>();
    static public List<Nature> fallDaily = new ArrayList<>();
    static volatile public List<Nature> hungryDeath = Collections.synchronizedList( new ArrayList<>());
    static volatile public List<Nature> hungryDeathDaily =Collections.synchronizedList( new ArrayList<>());
    static volatile public List<Nature> predatorsList = new ArrayList<>();
    static volatile public List<Nature> totalAnimals = new LinkedList<>();
    static volatile public List<Nature> herbivoresList = new LinkedList<>();

    public static List<Nature> getWasBornDaily() {
        return wasBornDaily;
    }

    public static void setWasBornDaily(Nature nature) {
        wasBornDaily.add(nature);
    }

    public static List<Nature> getWasKilledDaily() {
        return wasKilledDaily;
    }

    public static void setWasKilledDaily(Nature nature) {
        wasKilledDaily.add(nature);
    }

    public static List<Nature> getFallDaily() {
        return fallDaily;
    }

    public static void setFallDaily(Nature nature) {
        fallDaily.add(nature);
    }

    public static List<Nature> getHungryDeathDaily() {
        return hungryDeathDaily;
    }

    public static void setHungryDeathDaily(Nature nature) {
       hungryDeathDaily.add(nature);
    }

    public static List<Nature> getTotalAnimals() {
        return totalAnimals;
    }

    public static void setTotalAnimals(Nature nature) {
        totalAnimals.add(nature);
    }

    public static List<Nature> getPredatorsList() {
        return predatorsList;
    }

    public static void setPredatorsList(Nature nature) {
        predatorsList.add(nature);
    }

    public static List<Nature> getHerbivoresList() {
        return herbivoresList;
    }

    public static void setHerbivoresList(Nature nature) {
        herbivoresList.add(nature);
    }

    public static List<Nature> getPlantsList() {
        return plantsList;
    }

    public static void setPlantsList(Nature nature) {
        plantsList.add(nature);
    }

    static volatile public List<Nature> plantsList = new LinkedList<>();
    public static List getWasBorn() {
        return wasBorn;
    }

    public static void setWasBorn(Nature nature) {
        Statistics.wasBorn.add(nature);
    }

    public static List getWasKilled() {
        return wasKilled;
    }

    public static void setWasKilled(Nature nature) {
        Statistics.wasKilled.add(nature);
    }

    public static List getFall() {
        return fall;
    }

    public static void setFall(Nature nature) {
        Statistics.fall.add(nature);
    }

    public static List getHungryDeath() {
        return hungryDeath;
    }

    public static String getStringHungryDeath(){
        String result="";
        System.out.println(hungryDeath.size());
        for (int i = 0; i < hungryDeath.size(); i++) {
            for (Nature nature : hungryDeath){
               result=result+ nature.getName()+", ";
                System.out.println(result);
            }
        }
        return result;
    }

    public static void setHungryDeath(Nature nature) {
        Statistics.hungryDeath.add(nature);
    }

}
