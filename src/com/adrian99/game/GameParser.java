package com.adrian99.game;

import com.adrian99.enums.GameState;
import com.adrian99.enums.SquareState;

import java.util.List;

public class GameParser {
    Square[][] squares;
    GameManager gameManager;

    private final int mapWidth;
    private final int mapHeight;
    private final int numOfBombs;

    List<Coordinates> bombsCoordinatesList;
    private int squaresChecked = 0;
    private GameState gameState;

    public GameParser(int mapWidth, int mapHeight, int numOfBombs, List<Coordinates> bombsCoordinatesList, GameManager gameManager) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.numOfBombs = numOfBombs;
        this.bombsCoordinatesList = bombsCoordinatesList;
        this.gameState = GameState.ACTIVE;
        this.gameManager = gameManager;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public void parse(int x, int y) {
        Square currentSquare = squares[x][y];
        if (currentSquare.getType() == 0) {
            currentSquare.setSquareIcon("emptySquare");
            for (int i = Integer.max(x - 1, 0); i <= Integer.min(x + 1, mapWidth - 1); i++)
                for (int j = Integer.max(y - 1, 0); j <= Integer.min(y + 1, mapHeight - 1); j++) {
                    if (squares[i][j].getSquareState() == SquareState.CHECKED) continue;
                    if (squares[i][j].getSquareState() == SquareState.FLAG) continue;
                    squares[i][j].setSquareState(SquareState.CHECKED);
                    squaresChecked++;
                    parse(i, j);
                }
        } else if (currentSquare.getType() >= 1 && currentSquare.getType() <= 8)
            currentSquare.setSquareIcon("square_" + currentSquare.getType());
        else if (currentSquare.getType() == 9) {
            for (Coordinates coordinates : bombsCoordinatesList) {
                squares[coordinates.getX()][coordinates.getY()].setSquareIcon("bombSquare");
            }
            currentSquare.setSquareIcon("currentBombSquare");
            gameState = GameState.OVER;
            gameManager.gameOver();
            return;
        }

        if (currentSquare.getSquareState() != SquareState.CHECKED) {
            currentSquare.setSquareState(SquareState.CHECKED);
            squaresChecked++;
        }
        if (squaresChecked == (mapWidth * mapHeight - numOfBombs)) {
            gameState = GameState.WIN;
            gameManager.gameWin();
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}
