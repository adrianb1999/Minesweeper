package com.adrian99.gui;

import com.adrian99.game.GameManager;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JFrame {
    JFrame frame = new JFrame("Game over!");
    JFrame frameToBeClosed;

    private final int mapXSize;
    private final int mapYSize;
    private final int numOfBombs;


    public GameOver(JFrame frameToBeClosed, int mapXSize, int mapYSize, int numOfBombs) throws HeadlessException {
        this.frameToBeClosed = frameToBeClosed;
        this.mapXSize = mapXSize;
        this.mapYSize = mapYSize;
        this.numOfBombs = numOfBombs;
    }

    public void showGameOver() {
        frame.setResizable(false);
        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(125 - 50, 15, 100, 30);
        restartButton.addActionListener(e -> {
            frameToBeClosed.setVisible(false);
            frame.setVisible(false);
            new GameManager(mapXSize, mapYSize, numOfBombs).startGame();
        });
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(125 - 50, 55, 100, 30);
        exitButton.addActionListener(e -> {
            frameToBeClosed.setVisible(false);
            frame.setVisible(false);
            GameManager gameManager = new GameManager();
            MainMenu mainMenu = new MainMenu(gameManager);
            mainMenu.showMainMenu();
        });

        frame.add(restartButton);
        frame.add(exitButton);

        frame.setSize(250, 250);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); //to be centered
        frame.setVisible(true);
    }
}
