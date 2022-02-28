package com.adrian99.gui;

import com.adrian99.game.GameParser;
import com.adrian99.game.MapGenerator;
import com.adrian99.game.Square;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphicsInitialization extends JFrame {

    JFrame frame = new JFrame("Adrian's Minesweeper");

    Square[][] squares;
    private final int mapWidth;
    private final int mapHeight;
    private final int buttonSize;

    public GraphicsInitialization(int mapWidth, int mapHeight, int buttonSize) throws HeadlessException {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.buttonSize = buttonSize;
        squares = new Square[mapWidth][mapHeight];
    }

    public void init(MapGenerator mapGenerator, GameParser gameParser) {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Map<String, Icon> iconMap = new HashMap<>();

        iconMap.putIfAbsent("uncheckedSquare", new ImageIcon("./resources/uncheckedSquare.png"));
        iconMap.putIfAbsent("square_1", new ImageIcon("./resources/1_square.png"));
        iconMap.putIfAbsent("square_2", new ImageIcon("./resources/2_square.png"));
        iconMap.putIfAbsent("square_3", new ImageIcon("./resources/3_square.png"));
        iconMap.putIfAbsent("square_4", new ImageIcon("./resources/4_square.png"));
        iconMap.putIfAbsent("square_5", new ImageIcon("./resources/5_square.png"));
        iconMap.putIfAbsent("square_6", new ImageIcon("./resources/6_square.png"));
        iconMap.putIfAbsent("square_7", new ImageIcon("./resources/7_square.png"));
        iconMap.putIfAbsent("square_8", new ImageIcon("./resources/8_square.png"));
        iconMap.putIfAbsent("bombSquare", new ImageIcon("./resources/bombSquare.png"));
        iconMap.putIfAbsent("emptySquare", new ImageIcon("./resources/emptySquare.png"));
        iconMap.putIfAbsent("flagSquare", new ImageIcon("./resources/flagSquare.png"));
        iconMap.putIfAbsent("currentBombSquare", new ImageIcon("./resources/currentBombSquare.png"));

        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++) {
                Square b = new Square(buttonSize, buttonSize,
                        i * buttonSize + buttonSize / 2,
                        j * buttonSize + buttonSize / 2,
                        iconMap.get("uncheckedSquare"), i, j, iconMap,
                        mapGenerator.getElement(i, j), gameParser);
                b.addMouseListener(b);
                frame.add(b);
                squares[i][j] = b;
            }

        frame.setSize((int) (buttonSize * (mapWidth + 1.5)), buttonSize * (mapHeight + 2));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Square[][] getSquares() {
        return squares;
    }

    public JFrame getFrame() {
        return frame;
    }
}
