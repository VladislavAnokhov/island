package com.javarush.island.anokhov.streams;

import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.actions.MoveMaker;
import com.javarush.island.anokhov.nature.Animals.Animal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoveStream extends Thread  implements Stream  {
    private Island island;

    public MoveStream(Island island){
        this.island=island;
    }
    @Override
    public void run (){
        stream();
    }
    @Override
    public Void call() throws Exception {
        stream();
        return null;
    }

    public void stream() {

        ExecutorService executorServiceForMove = Executors.newWorkStealingPool();
        for (int i = 0; i < island.getMassive().length; i++) {
            for (int j = 0; j < island.getMassive()[i].length; j++) {
                synchronized (island.getMassive()[i][j]) {

                    for (Animal animal : island.getMassive()[i][j].getAnimals()) {

                        MoveMaker moveMaker = animal.move();
                        moveMaker.setIsland(island);
                        executorServiceForMove.submit(moveMaker);
                    }
                }
            }

        } executorServiceForMove.shutdown();
    }


}