package com.adrian99;

import com.adrian99.game.GameManager;
import com.adrian99.gui.MainMenu;

public class Main {

    public static void main(String[] args) {
        System.out.println("Current directory = " + System.getProperty("user.dir"));
        GameManager gameManager = new GameManager();
        new MainMenu(gameManager).showMainMenu();
    }
}

