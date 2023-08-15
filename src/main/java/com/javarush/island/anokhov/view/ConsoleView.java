package com.javarush.island.anokhov.view;

import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.creator.Creator;
import com.javarush.island.anokhov.nature.Animals.Herbivore;
import com.javarush.island.anokhov.nature.Animals.Predator;
import com.javarush.island.anokhov.nature.Nature;
import com.javarush.island.anokhov.statistics.Statistics;
import com.javarush.island.anokhov.streams.MainStream;


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
        System.out.println(dayCounter);
        int counter =0;
        counter = scanner.nextInt();
        System.out.println(startWorld);

        Island island = new Island(intHeight, intWidth);
        Creator creator = new Creator();
        creator.spawn(island);

        System.out.println(theWorldIsBuilt);
        System.out.println(startSimulation);
        System.out.println(simulationOnWork);


        printResult(island);
        if (counter>0){
            doCycleByCounter(island,counter);
        }
        else
        doCycleToEnd(island);
        System.out.println("--------------------------");
        System.out.println(endOfSimulation);
    }
    private synchronized void dailyPrintResult (int day){
        System.out.println(dailyStatistics+day);
        System.out.println(Statistics.getFallDaily().size() + animalsWereDiedFromFalling);
        System.out.println(Statistics.getWasKilledDaily().size() + animalsWereKilled);
        System.out.println(Statistics.getWasBornDaily().size() + animalsWereBorn);
        System.out.println(Statistics.getHungryDeathDaily().size() + animalsWereDiedFromHungry);
        Statistics.getFallDaily().clear();
        Statistics.getWasBornDaily().clear();
        Statistics.getWasKilledDaily().clear();
        Statistics.getHungryDeathDaily().clear();
    }

    private void doCycleToEnd (Island island){
        int dailyCounter =0;
        do {
            dailyCounter++;
            long time = System.currentTimeMillis();

            MainStream mainStream = new MainStream(island);
            mainStream.stream();

            System.out.println("цикл для закончен, время цикла : " + (System.currentTimeMillis() - time)/1000 + " секунд");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dailyPrintResult(dailyCounter);
            printResult(island);
        } while (checkAlive(island));
    }
    private void doCycleByCounter(Island island, int counter){
        int dailyCounter=0;
        do {
            counter--;
            dailyCounter++;
            System.out.println();
            long time = System.currentTimeMillis();

            MainStream mainStream = new MainStream(island);
            mainStream.stream();

            System.out.println("цикл для закончен, время цикла : " + (System.currentTimeMillis() - time)/1000 + " секунд");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dailyPrintResult(dailyCounter);
            printResult(island);
        } while (counter>0);
    }

    private  void printResult(Island island) {
        int predadors = 0;
        int travoyad = 0;
        int plant = 0;

        for (int i = 0; i < island.getMassive().length; i++) {
            for (int j = 0; j < island.getMassive()[i].length; j++) {
                Location location = island.getMassive()[i][j];
                    synchronized (location){
                    for (Nature nature : location.getNatures()) {
                        synchronized (nature){
                        Statistics.setTotalAnimals(nature);
                        if (nature instanceof Predator) {
                            predadors++;
                        } else if (nature instanceof Herbivore) {
                            travoyad++;
                        } else plant++;
                   }}
                }
            }
        }
        System.out.println("--------------------------");
        System.out.println(predators + predadors);
        System.out.println(herbivores + travoyad);
        System.out.println(plants + plant);
        int total = predadors + travoyad + plant;
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
                synchronized (location){
                for (Nature nature : location.getNatures()) {
                    synchronized (nature){
                    Statistics.setTotalAnimals(nature);
                    if (nature instanceof Predator) {
                        predadors++;
                    } else if (nature instanceof Herbivore) {
                        travoyad++;}
                    }}
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
