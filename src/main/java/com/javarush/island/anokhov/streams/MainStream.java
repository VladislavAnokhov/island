package com.javarush.island.anokhov.streams;

import com.javarush.island.anokhov.Island.Island;

import java.util.ArrayList;
import java.util.List;

public class MainStream {

    private Island island;
    private List <Stream> streams = new ArrayList<>();
    public MainStream(Island island){
        this.island=island;
    }
    public void stream (){

       /* ExecutorService executorService = Executors.newWorkStealingPool();
        Stream moveStream = new MoveStream(island);
        streams.add(moveStream);
        Stream eatStream = new EatRoomStream(island);
        streams.add(eatStream);
        Stream reproduceStream = new ReproduceStream(island);
        streams.add(reproduceStream);
        Stream refreshStream = new RefresherStream(island);
        streams.add(refreshStream);
        for (int i = 0; i < streams.size(); i++) {
            executorService.submit(streams.get(i));
        }
        executorService.shutdown();*/

        MoveStream moveStream  = new MoveStream(island);
        moveStream.start();
        try {
            moveStream.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        moveStream.interrupt();

        EatRoomStream eatRoomStream = new EatRoomStream(island);
        eatRoomStream.start();
        try {
            eatRoomStream.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        eatRoomStream.interrupt();

        ReproduceStream reproduceStream = new ReproduceStream(island);
        reproduceStream.start();
        try {
            reproduceStream.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        reproduceStream.interrupt();

        RefresherStream refresherStream = new RefresherStream(island);
        refresherStream.start();
        try {
            refresherStream.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        refresherStream.interrupt();
    }

}
