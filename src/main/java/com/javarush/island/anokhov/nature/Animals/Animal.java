package com.javarush.island.anokhov.nature.Animals;

import com.javarush.island.anokhov.actions.DiningRoom;
import com.javarush.island.anokhov.actions.MoveMaker;
import com.javarush.island.anokhov.actions.Reproducer;
import com.javarush.island.anokhov.nature.Nature;

import java.util.HashMap;

public abstract class Animal extends Nature {
    private int weight;
    private int speed;
    private int wellFed;
    abstract public Reproducer reproduce ();
   abstract public MoveMaker move ();
   abstract public DiningRoom eat ();
   abstract public HashMap<String, Integer> getMenu();
   abstract public Double getWellFed();
   abstract public void setWellFed(Double wellFed);
   abstract public int getReproduceCounter();
   abstract public void setReproduceCounter(int reproduceCounter);

}
