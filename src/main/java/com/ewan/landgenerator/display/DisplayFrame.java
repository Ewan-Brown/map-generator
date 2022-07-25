package com.ewan.landgenerator.display;

import sun.reflect.generics.scope.DummyScope;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Singleton. Contains logic for GUI.
public class DisplayFrame implements KeyListener {

    public static void main(String[] args) {
        new DisplayFrame(new TestDisplay(400,400));
    }

    public DisplayFrame(Display d) {
        currentDisplay = d;
        JFrame frame = new JFrame();
        final JPanel panel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.BLACK);
                g.fillRect(0,0,getWidth(),getHeight());
                paintToGraphics(g);
            };
        };
        frame.add(panel);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Thread(() -> {
            while(true){
                panel.repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Display currentDisplay;
    private int size = 1;

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

    //TODO Switch this to a list of displays, and allow user to cycle through them with keys
    public void setDisplay(Display d){
        currentDisplay = d;
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
