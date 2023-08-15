package com.javarush.island.anokhov.nature.Animals.Herbivores;


import com.javarush.island.anokhov.actions.EatingRoom;
import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.actions.MoveMaker;
import com.javarush.island.anokhov.actions.Reproducer;
import com.javarush.island.anokhov.constants.animalsBasic.SheepBasic;
import com.javarush.island.anokhov.nature.Animals.Herbivore;
import com.javarush.island.anokhov.statistics.Statistics;

import java.util.HashMap;

public class Sheep extends Herbivore {
    private int maxQuantity  ; //140
    private Double weight ;
    private int speed;
    private volatile Double wellFed ;
    private String name ;
    private Location location;
    private volatile Boolean isAlive=true;
    private volatile int reproduceCounter;
    public  Sheep( String name , int maxQuantity,Double weight,int speed,int reproduceCounter,Double wellFed ){
        this.name = name;
        this.maxQuantity=maxQuantity;
        this.weight= weight;
        this.speed=speed;
        this.wellFed=wellFed;
        this.reproduceCounter=reproduceCounter;
    }
    public String getName() {
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
@Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed=speed;
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
    public EatingRoom eat() {
        EatingRoom diningRoom =new EatingRoom(this,location,wellFed);
        return diningRoom;
    }

    @Override
    public HashMap<String, Integer> getMenu() {
        return SheepBasic.getMenu();
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
        synchronized (location){
            location.remove(this);}
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
