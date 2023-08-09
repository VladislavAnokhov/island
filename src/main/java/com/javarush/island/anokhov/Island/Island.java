package com.javarush.island.anokhov.Island;

public class Island {
    private Location[][] massive;

    public Island(int height, int width) {
        massive = new Location[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Location location = new Location();
                massive[i][j] = location;
            }
        }
    }

    public Location[][] getMassive() {
        return massive;
    }

    public Location findLocation(Location location) {
        Location result = null;
        for (int i = 0; i < massive.length; i++) {
            for (int j = 0; j < massive[i].length; j++) {
                if (massive[i][j] == location) {
                    result = massive[i][j];
                }
            }

        }
        return result;
    }



}
