package com.javarush.island.anokhov.streams;

import java.util.concurrent.Callable;

public interface Stream extends Callable<Void> {
    public void stream();
}
