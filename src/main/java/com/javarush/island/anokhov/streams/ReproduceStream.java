package com.javarush.island.anokhov.streams;

import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.actions.Reproducer;
import com.javarush.island.anokhov.nature.Animals.Animal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReproduceStream extends Thread implements Stream {
    private Island island;
    public void  run(){
        stream();
    }
    @Override
    public Void call() throws Exception {
        stream();
        return null;
    }

    public ReproduceStream(Island island) {
        this.island = island;
    }

    public void stream() {
        ExecutorService executorServiceForMove = Executors.newWorkStealingPool();
        for (int i = 0; i < island.getMassive().length; i++) {
            for (int j = 0; j < island.getMassive()[i].length; j++) {
                synchronized (island.getMassive()[i][j]) {
                    for (Animal animal : island.getMassive()[i][j].getAnimals()) {
                            Reproducer reproducer = animal.reproduce();
                            executorServiceForMove.submit(reproducer);
                    }
                }
            }
        }
        executorServiceForMove.shutdown();
    }


}