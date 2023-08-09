package com.javarush.island.anokhov.view;

import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.controller.Behavior;
import com.javarush.island.anokhov.controller.Creator;
import com.javarush.island.anokhov.nature.Animals.Herbivore;
import com.javarush.island.anokhov.nature.Animals.Predator;
import com.javarush.island.anokhov.nature.Nature;
import com.javarush.island.anokhov.nature.plants.Plant;
import com.javarush.island.anokhov.statistics.Statistics;

import java.util.Scanner;

import static com.javarush.island.anokhov.constants.Animals.*;
import static com.javarush.island.anokhov.constants.ApplicationCommunication.*;

public class ConsoleView implements View {
    private int intHeight;
    private int intWidth;

    @Override
    public void createWorld() {
        System.out.println(mainWelcome);
        Scanner scanner = new Scanner(System.in);
        System.out.println(height);
        intHeight = scanner.nextInt();
        System.out.println(width);
        intWidth = scanner.nextInt();
        System.out.println(startWorld);

        Island island = new Island(intHeight, intWidth);
        Creator creator = new Creator();
        creator.spawn(island);

        System.out.println(theWorldIsBuilt);
        printResult(island);


        System.out.println(startSimulation);
        String string = scanner.nextLine();
        if (string == "" || string == " ") {
            scanner.close();
        }
        System.out.println(simulationOnWork);
        printResult(island);
        do {

            Behavior behavior = new Behavior(island);
            Thread thread = new Thread(behavior);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printResult(island);
        } while (checkAlive(island));
        System.out.println("--------------------------");
        printResult(island);

       /* for (int i = 0; i < island.getMassive().length; i++) {
            System.out.println();
            for (int j = 0; j < island.getMassive()[i].length; j++) {
                Location location = island.getMassive()[i][j];
                    for (Nature nature :location.getNatures()){
                        System.out.println(nature.getName()+" ,");
                    }
            }
        }*/
    }

    private void printResult(Island island) {
        int preda = 0;
        int travoyad = 0;
        int plant = 0;

        for (int i = 0; i < island.getMassive().length; i++) {
            for (int j = 0; j < island.getMassive()[i].length; j++) {
                Location location = island.getMassive()[i][j];
                for (Nature nature : location.getNatures()) {
                    Statistics.setTotalAnimals(nature);
                    if (nature instanceof Predator) {
                        preda++;
                    } else if (nature instanceof Herbivore) {
                        travoyad++;
                    } else if (nature instanceof Plant) {
                        plant++;
                    }

                }
            }
        }
        System.out.println(Statistics.getFall().size() + animalsWereDiedFromFalling);
        System.out.println(Statistics.getWasBorn().size() + animalsWereBorn);
        System.out.println(Statistics.getWasKilled().size() + animalsWereKilled);
        System.out.println(Statistics.getHungryDeath().size() + animalsWereDiedFromHungry);

        System.out.println("--------------------------");
        System.out.println(predators + preda);
        System.out.println(herbivores + travoyad);
        System.out.println(plants + plant);
        int total = preda + travoyad + plant;
        System.out.println(totalStringAnimals + total);
        System.out.println();
    }

    private boolean checkAlive(Island island) {
        boolean result = true;
        int predadors = 0;
        int travoyad = 0;
        for (int i = 0; i < island.getMassive().length; i++) {
            for (int j = 0; j < island.getMassive()[i].length; j++) {
                Location location = island.getMassive()[i][j];
                for (Nature nature : location.getNatures()) {
                    Statistics.setTotalAnimals(nature);
                    if (nature instanceof Predator) {
                        predadors++;
                    } else if (nature instanceof Herbivore) {
                        travoyad++;
                    }
                }
            }
        }
        if (predadors == 0) {
            return false;}
            if (travoyad == 0) {
                return false;
            }


        return result;
    }
}
