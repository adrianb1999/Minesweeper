package com.adrian99.gui;

import com.adrian99.game.GameManager;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    JFrame frame = new JFrame("Main menu");
    private final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    GameManager gameManager;

    public MainMenu(GameManager gameManager) throws HeadlessException {
        this.gameManager = gameManager;
    }

    public void showMainMenu() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JButton beginnerButton = new JButton("Beginner");
        beginnerButton.setBounds(screenHeight / 6 - 60, 15, 120, 30);
        beginnerButton.addActionListener(e -> {
            gameManager.setMapWidth(10);
            gameManager.setMapHeight(10);
            gameManager.setNumOfBombs(10);
            frame.setVisible(false);
            gameManager.startGame();
        });

        JButton intermediateButton = new JButton("Intermediate");
        intermediateButton.setBounds(screenHeight / 6 - 60, 55, 120, 30);
        intermediateButton.addActionListener(e -> {
            gameManager.setMapWidth(15);
            gameManager.setMapHeight(15);
            gameManager.setNumOfBombs(25);
            frame.setVisible(false);
            gameManager.startGame();
        });

        JButton expertButton = new JButton("Expert");
        expertButton.setBounds(screenHeight / 6 - 60, 95, 120, 30);
        expertButton.addActionListener(e -> {
            gameManager.setMapWidth(30);
            gameManager.setMapHeight(15);
            gameManager.setNumOfBombs(45);
            frame.setVisible(false);
            gameManager.startGame();
        });

        JButton hardcoreButton = new JButton("Hardcore");
        hardcoreButton.setBounds(screenHeight / 6 - 60, 135, 120, 30);
        hardcoreButton.addActionListener(e -> {
            gameManager.setMapWidth(36);
            gameManager.setMapHeight(18);
            gameManager.setNumOfBombs(129);
            frame.setVisible(false);
            gameManager.startGame();
        });

        frame.add(beginnerButton);
        frame.add(intermediateButton);
        frame.add(expertButton);
        frame.add(hardcoreButton);

        frame.setSize(screenHeight / 3, (int) (screenHeight / 2.5));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); //to be centered
        frame.setVisible(true);
    }
}
