package com.javarush.island.anokhov.nature;

import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.nature.Animals.Predators.Boa;

import java.util.HashMap;

public abstract class Nature {



  abstract public void die ();
  abstract public int getMaxQuantity();
  abstract public String getName ();
  abstract public Double getWeight();
  abstract public void setLocation(Location location);
  abstract public Location getLocation ();
  abstract public boolean getIsAlive();
  abstract public void setIsAlive(Boolean isAlive);

}
