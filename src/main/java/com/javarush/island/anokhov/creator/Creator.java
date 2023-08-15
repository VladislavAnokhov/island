package com.javarush.island.anokhov.creator;
import java.util.concurrent.ThreadLocalRandom;

import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.constants.animalsBasic.Basic;
import com.javarush.island.anokhov.constants.animalsBasic.BasicList;
import com.javarush.island.anokhov.nature.Animals.Herbivores.*;
import com.javarush.island.anokhov.nature.Animals.Predators.*;
import com.javarush.island.anokhov.nature.plants.Plant;
import com.javarush.island.anokhov.statistics.Statistics;


import static com.javarush.island.anokhov.constants.Animals.*;

public class Creator {
  static  public int totalCount = 0;

    public void spawn(Island island) {
        for (int i = 0; i < island.getMassive().length; i++) {
            for (int x = 0; x < island.getMassive()[i].length; x++) {
                Randomizer.random(bear, island.getMassive()[i][x]);
                Randomizer.random(boa, island.getMassive()[i][x]);
                Randomizer.random(boar, island.getMassive()[i][x]);
                Randomizer.random(buffalo, island.getMassive()[i][x]);
                Randomizer.random(caterpillar, island.getMassive()[i][x]);
                Randomizer.random(deer, island.getMassive()[i][x]);
                Randomizer.random(duck, island.getMassive()[i][x]);
                Randomizer.random(eagle, island.getMassive()[i][x]);
                Randomizer.random(fox, island.getMassive()[i][x]);
                Randomizer.random(goat, island.getMassive()[i][x]);
                Randomizer.random(horse, island.getMassive()[i][x]);
                Randomizer.random(mouse, island.getMassive()[i][x]);
               Randomizer.random(rabbit, island.getMassive()[i][x]);
               Randomizer.random(wolf, island.getMassive()[i][x]);
                Randomizer.random(sheep, island.getMassive()[i][x]);
                Randomizer.random(plant, island.getMassive()[i][x]);
            }
        }
    }

    class Randomizer {
        static public void random(String nature, Location location) {
            int counter = 0;
            for (int i = 0; i < BasicList.basicAnimalsList.size(); i++) {
                Basic base = BasicList.basicAnimalsList.get(i);
                if (base.getName().equalsIgnoreCase(nature)) {
                    counter = ThreadLocalRandom.current().nextInt(base.getMaxQuantity());
                }
                while (counter > 0) {
                    totalCount++;
                    Randomizer.create(nature, base, location);
                    counter--;
                }
            }
        }

        static public void create(String nature, Basic basic, Location location) {

            switch (nature) {
                case wolf -> {
                    Wolf wolfAlfa = new Wolf(wolf + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(wolfAlfa);
                    wolfAlfa.setLocation(location);
                    Statistics.setPredatorsList(wolfAlfa);
                }
                case sheep -> {
                    Sheep sheepAlfa = new Sheep(sheep + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(sheepAlfa);
                    sheepAlfa.setLocation(location);
                    Statistics.setHerbivoresList(sheepAlfa);
                }
                case bear -> {
                    Bear bearAlfa = new Bear(bear + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(bearAlfa);
                    bearAlfa.setLocation(location);
                    Statistics.setPredatorsList(bearAlfa);
                }case boa -> {
                    Boa boaAlfa = new Boa(boa + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(boaAlfa);
                    boaAlfa.setLocation(location);
                    Statistics.setPredatorsList(boaAlfa);
                }case boar -> {
                    Boar boarAlfa = new Boar(boar + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(boarAlfa);
                    boarAlfa.setLocation(location);
                    Statistics.setHerbivoresList(boarAlfa);
                }case buffalo -> {
                    Buffalo bufalloAlfa = new Buffalo(buffalo + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(bufalloAlfa);
                    bufalloAlfa.setLocation(location);
                    Statistics.setHerbivoresList(bufalloAlfa);
                }case caterpillar -> {
                    Caterpillar  caterpillarAlfa = new Caterpillar(caterpillar + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(caterpillarAlfa);
                    caterpillarAlfa.setLocation(location);
                    Statistics.setHerbivoresList(caterpillarAlfa);
                }case deer -> {
                    Deer deerAlfa = new Deer(deer + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(deerAlfa);
                    deerAlfa.setLocation(location);
                    Statistics.setHerbivoresList(deerAlfa);
                }case duck -> {
                    Duck duckAlfa = new Duck(duck + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(duckAlfa);
                    duckAlfa.setLocation(location);
                    Statistics.setHerbivoresList(duckAlfa);
                }case eagle -> {
                    Eagle eagleAlfa = new Eagle(eagle + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(eagleAlfa);
                    eagleAlfa.setLocation(location);
                    Statistics.setPredatorsList(eagleAlfa);
                }case fox -> {
                    Fox foxAlfa = new Fox(fox + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(foxAlfa);
                    foxAlfa.setLocation(location);
                    Statistics.setHerbivoresList(foxAlfa);
                }case goat -> {
                    Goat goatAlfa = new Goat(goat + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(goatAlfa);
                    goatAlfa.setLocation(location);
                    Statistics.setHerbivoresList(goatAlfa);
                }case horse -> {
                    Horse horseAlfa = new Horse(horse + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(horseAlfa);
                    horseAlfa.setLocation(location);
                    Statistics.setHerbivoresList(horseAlfa);
                }case mouse -> {
                    Mouse mouseAlfa = new Mouse(mouse + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(),basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(mouseAlfa);
                    mouseAlfa.setLocation(location);
                    Statistics.setHerbivoresList(mouseAlfa);
                }case rabbit -> {
                    Rabbit rabbitAlfa = new Rabbit(rabbit + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight(), basic.getSpeed(), basic.getReproduceCounter(),
                            basic.getWellFed() / ThreadLocalRandom.current().nextInt(1, 3));
                    location.comeIn(rabbitAlfa);
                    rabbitAlfa.setLocation(location);
                    Statistics.setHerbivoresList(rabbitAlfa);
                }case plant -> {
                    Plant plantAlfa = new Plant(plant + ThreadLocalRandom.current().nextInt(1000),
                            basic.getMaxQuantity(), basic.getWeight());
                    location.comeIn(plantAlfa);
                    plantAlfa.setLocation(location);
                    Statistics.setPlantsList(plantAlfa);
                }
            }
        }
    }
}

