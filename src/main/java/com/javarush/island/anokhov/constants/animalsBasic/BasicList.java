package com.javarush.island.anokhov.constants.animalsBasic;

import com.javarush.island.anokhov.nature.Animals.Herbivores.Sheep;
import com.javarush.island.anokhov.nature.Nature;

import java.util.*;

public class BasicList {
    static public final List <Basic> basicAnimalsList = new ArrayList<>();
    static {
            basicAnimalsList.add(new WolfBasic());
            basicAnimalsList.add(new BearBasic());
            basicAnimalsList.add(new BoaBasic());
            basicAnimalsList.add(new BoarBasic());
            basicAnimalsList.add(new BuffaloBasic());
            basicAnimalsList.add(new CaterpillarBasic());
            basicAnimalsList.add(new DeerBasic());
            basicAnimalsList.add(new DuckBasic());
            basicAnimalsList.add(new EagleBasic());
            basicAnimalsList.add(new FoxBasic());
            basicAnimalsList.add(new GoatBasic());
            basicAnimalsList.add(new HorseBasic());
            basicAnimalsList.add(new MouseBasic());
            basicAnimalsList.add(new PlantBasic());
            basicAnimalsList.add(new RabbitBasic());
            basicAnimalsList.add(new SheepBasic());


    }
}
