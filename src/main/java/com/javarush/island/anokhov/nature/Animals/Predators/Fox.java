package com.javarush.island.anokhov.nature.Animals.Predators;


import com.javarush.island.anokhov.actions.DiningRoom;
import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.actions.MoveMaker;
import com.javarush.island.anokhov.actions.Reproducer;
import com.javarush.island.anokhov.constants.animalsBasic.FoxBasic;
import com.javarush.island.anokhov.nature.Animals.Predator;
import com.javarush.island.anokhov.statistics.Statistics;

import java.util.HashMap;

public class Fox extends Predator {
    private int maxQuantity ;
    private Double weight ;
    private int speed  ;
    private volatile Double wellFed ;
    private Location location;
    private String name;
    private volatile int reproduceCounter;
    private volatile boolean isAlive =true;

    public  Fox( String name , int maxQuantity,Double weight,int speed,int reproduceCounter,Double wellFed ){
        this.name = name;
        this.maxQuantity=maxQuantity;
        this.weight= weight;
        this.speed=speed;
        this.wellFed=wellFed;
        this.reproduceCounter=reproduceCounter;
    }public String getName() {
        return name;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    public Double getWeight() {
        return weight;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public int getSpeed() {
        return speed;
    }
    @Override
    public Double getWellFed() {
        return wellFed;
    }

    @Override
    public void setWellFed(Double wellFed) {
        this.wellFed=wellFed;
    }

    @Override
    public int getReproduceCounter() {
        return reproduceCounter;
    }
    @Override
    public void setReproduceCounter(int reproduceCounter) {
        this.reproduceCounter=reproduceCounter;
    }

    public Location getLocation() {
        return location;
    }


    @Override
    public MoveMaker move() {
        MoveMaker moveMaker = new MoveMaker(speed,this);
        return moveMaker;
    }

    @Override
    public DiningRoom eat() {
        DiningRoom diningRoom =new DiningRoom(this,location,wellFed);
        return diningRoom;
    }

    @Override
    public HashMap<String, Integer> getMenu() {
        return FoxBasic.getMenu();
    }


    @Override
    public Reproducer reproduce() {
        Reproducer reproducer = null;
        synchronized (location) {
            reproducer = new Reproducer(this);
        }
        return reproducer;
    }


    @Override
    public void die() {
        isAlive=false;
        location.remove(this);
        synchronized (Statistics.predatorsList){
        Statistics.getPredatorsList().remove(this);}
    }
    @Override
    public boolean getIsAlive(){
        return isAlive;
    }
    @Override
    public void setIsAlive(Boolean isAlive){
        this.isAlive=isAlive;
    }
}