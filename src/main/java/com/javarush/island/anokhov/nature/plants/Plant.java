package com.javarush.island.anokhov.nature.plants;

import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.nature.Nature;
import com.javarush.island.anokhov.statistics.Statistics;

public class Plant extends Nature {

    private Double weight ;

    private int  maxQuantity ;

    private String name;
    private Location location;
    private volatile boolean isAlive =true ;
    public  Plant( String name , int maxQuantity,Double weight ){
        this.name = name;
        this.maxQuantity=maxQuantity;
        this.weight= weight;
    }
    @Override
    public boolean getIsAlive(){
        return isAlive;
    }
    @Override
    public void setIsAlive(Boolean isAlive){
        this.isAlive=isAlive;
    }



    public void die (){
        isAlive=false;
        location.remove(this);
        Statistics.getPlantsList().remove(this);
    }

    @Override
    public int getMaxQuantity() {
        return maxQuantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public void setLocation(Location location) {
            this.location=location;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}
