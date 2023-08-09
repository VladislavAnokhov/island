package com.javarush.island.anokhov;

import com.javarush.island.anokhov.view.ConsoleView;
import com.javarush.island.anokhov.view.View;




public class Main {
    public static void main(String[] args) {
        View view = new ConsoleView();
        view.createWorld();
    }
}




