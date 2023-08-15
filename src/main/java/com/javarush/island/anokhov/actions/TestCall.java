package com.javarush.island.anokhov.actions;

import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.Island.Location;
import com.javarush.island.anokhov.nature.Animals.Animal;
import com.javarush.island.anokhov.nature.Animals.Predators.Wolf;
import com.javarush.island.anokhov.nature.Nature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCall {
    private Island island;
    private List<MoveMaker> moveTasks = new ArrayList<>();

    public TestCall(Island island) {
        this.island = island;
    }
    public void stream() {
        ExecutorService executorServiceForMove = Executors.newWorkStealingPool();
        for (int i = 0; i < island.getMassive().length; i++) {
            for (int j = 0; j < island.getMassive()[i].length; j++) {
                synchronized (island.getMassive()[i][j]) {

                    for (Animal animal : island.getMassive()[i][j].getAnimals()) {
                        synchronized (island.getMassive()[i][j]){
                        MoveMaker moveMaker = animal.move();
                        moveMaker.setIsland(island);
                        executorServiceForMove.submit(moveMaker);
                    }

                }}
            }
        } executorServiceForMove.shutdown();
    }

}


