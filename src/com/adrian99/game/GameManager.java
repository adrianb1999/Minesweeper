package com.adrian99.game;

import com.adrian99.gui.GameOver;
import com.adrian99.gui.GameWin;
import com.adrian99.gui.GraphicsInitialization;

public class GameManager {

    GraphicsInitialization graphicsInitialization;
    
    private int mapWidth;
    private int mapHeight;
    private int numOfBombs;

    public GameManager()
    {}

    public GameManager(int mapWidth, int mapHeight, int numOfBombs) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.numOfBombs = numOfBombs;
    }

    public void startGame() {
        MapGenerator mapGenerator = new MapGenerator(mapWidth, mapHeight, numOfBombs);
        mapGenerator.generateMap();
        GameParser gameParser = new GameParser(mapWidth, mapHeight, numOfBombs, mapGenerator.getBombsCoordinatesList(),this);

        int squareSize = 40;
        graphicsInitialization = new GraphicsInitialization(mapWidth, mapHeight, squareSize);
        graphicsInitialization.init(mapGenerator, gameParser);
        gameParser.setSquares(graphicsInitialization.getSquares());
    }

    public void gameOver()
    {
        new GameOver(graphicsInitialization.getFrame(), mapWidth, mapHeight,numOfBombs).showGameOver();
    }
    public void gameWin()
    {
        new GameWin(graphicsInitialization.getFrame(), mapWidth, mapHeight,numOfBombs).showGameWin();
    }
    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setNumOfBombs(int numOfBombs) {
        this.numOfBombs = numOfBombs;
    }
}
