package com.ewan.landgenerator.display;

import sun.reflect.generics.scope.DummyScope;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Singleton. Contains logic for GUI. JPanel/JFrame can be separate?
public class DisplayFrame implements KeyListener {

    private Display currentDisplay;
    private int size;

    public void paintToGraphics(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Color[][] colorGrid = currentDisplay.getColorGrid();
        for (int i = 0; i < colorGrid.length; i++) {
            for (int j = 0; j < colorGrid[i].length; j++) {
                g2.setColor(colorGrid[i][j]);
                g2.fillRect(i * size, j * size, size, size);
            }
        }
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
