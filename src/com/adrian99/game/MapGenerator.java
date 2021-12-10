package com.adrian99.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private final int mapWidth;
    private final int mapHeight;
    private final int numOfBombs;

    private int[][] mapMatrix;
    List<Coordinates> bombsCoordinatesList = new ArrayList<>();

    public MapGenerator(int mapWidth, int mapHeight, int numOfBombs) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.numOfBombs = numOfBombs;
    }

    void generateMap() {
        mapMatrix = new int[mapWidth][mapHeight];
        bombGenerator(); //generate @numOfBombs at random coordinates
        int counter = 0;
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                if (mapMatrix[i][j] == 9) continue; //if the current square is a bomb ignore it

                //Iterate through all 8 neighbours to check for bombs
                for (int i1 = Integer.max(i - 1, 0); i1 <= Integer.min(mapWidth - 1, i + 1); i1++) {
                    for (int j1 = Integer.max(j - 1, 0); j1 <= Integer.min(mapHeight - 1, j + 1); j1++) {
                        if (mapMatrix[i1][j1] == 9) {
                            counter++; // increase if a neighbor square is a bomb
                        }
                    }
                }
                mapMatrix[i][j] = counter;
                counter = 0;
            }
        }
    }

    //Bomb coordinates generator
    private void bombGenerator() {
        while (bombsCoordinatesList.size() != numOfBombs) {
            int x = new Random().nextInt(mapWidth);
            int y = new Random().nextInt(mapHeight);
            if (mapMatrix[x][y] != 9) {
                mapMatrix[x][y] = 9;
                bombsCoordinatesList.add(new Coordinates(x,y));
            }
        }
    }

    public int getElement(int x, int y) {
        return mapMatrix[x][y];
    }

    public List<Coordinates> getBombsCoordinatesList() {
        return bombsCoordinatesList;
    }
}
