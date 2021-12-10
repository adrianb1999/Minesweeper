package com.adrian99.game;

import com.adrian99.enums.GameState;
import com.adrian99.enums.SquareState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class Square extends JButton implements MouseListener {

    private SquareState squareState;
    private final int xIndex;
    private final int yIndex;
    private final int type;
    Map<String, Icon> iconMap;
    GameParser gameParser;

    public Square(int xSize, int ySize, int xPosition, int yPosition, Icon squareIcon, int xIndex, int yIndex, Map<String, Icon> iconMap, int type, GameParser gameParser) throws HeadlessException {
        super(squareIcon);
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.iconMap = iconMap;
        this.squareState = SquareState.UNCHECKED;
        this.type = type;
        this.gameParser = gameParser;

        setBounds(xPosition, yPosition, xSize, ySize);
    }

    public SquareState getSquareState() {
        return squareState;
    }

    public void setSquareIcon(String iconString) {
        this.setIcon(iconMap.get(iconString));
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameParser.getGameState() == GameState.OVER) return; //if game is OVER do nothing
        if (this.squareState == SquareState.CHECKED) return; //if the square is already checked do nothing

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (this.squareState == SquareState.FLAG) return; //if the square is marked as a FLAG ignore it
            gameParser.parse(xIndex, yIndex);
        }

        if (SwingUtilities.isRightMouseButton(e)) { // we use right click to mark and unmarked squares as FLAG
            if (this.squareState != SquareState.FLAG) { //if the square isn't a FLAG
                this.setSquareIcon("flagSquare"); //change the square icon to FLAG
                setSquareState(SquareState.FLAG); //change the square state to FLAG
                return;
            }
            this.setSquareIcon("uncheckedSquare");
            setSquareState(SquareState.UNCHECKED); //change the square state to UNCHECKED
        }
    }

    public void setSquareState(SquareState squareState) {
        this.squareState = squareState;
    }

    public int getType() {
        return type;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing
    }
}
