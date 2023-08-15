package com.javarush.island.anokhov.streams;

import com.javarush.island.anokhov.Island.Island;
import com.javarush.island.anokhov.actions.Refresher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RefresherStream extends Thread implements Stream{
    private Island island;
    public RefresherStream(Island island){
        this.island = island;
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
                       Refresher refresher = new Refresher(island.getMassive()[i][j]);
                        executorServiceForMove.submit(refresher);

                }
            }
        }
        executorServiceForMove.shutdown();
    }


}

