package com.ewan.landgenerator.math;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

//https://rosettacode.org/wiki/Julia_set#J

public class JuliaSet extends JPanel implements KeyListener {
    private static final int MAX_ITERATIONS = 300;
    private static final double ZOOM = 1;
    private static double CX = -0.7;
    private static double CY = 0.27015;
    private static final double MOVE_X = 0;
    private static final double MOVE_Y = 0;

    public JuliaSet() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.white);
    }

    void drawJuliaSet(Graphics2D g) {
        int w = getWidth();
        int h = getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                double zx = 1.5 * (x - w / 2) / (0.5 * ZOOM * w) + MOVE_X;
                double zy = (y - h / 2) / (0.5 * ZOOM * h) + MOVE_Y;
                float i = MAX_ITERATIONS;
                while (zx * zx + zy * zy < 4 && i > 0) {
                    double tmp = zx * zx - zy * zy + CX;
                    zy = 2.0 * zx * zy + CY;
                    zx = tmp;
                    i--;
                }
                int c = Color.HSBtoRGB((MAX_ITERATIONS / i) % 1, 1, i > 0 ? 1 : 0);
                image.setRGB(x, y, c);
            }
        }
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawJuliaSet(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Julia Set");
            f.setResizable(false);
            JuliaSet j = new JuliaSet();
            f.add(j, BorderLayout.CENTER);
            f.addKeyListener(j);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);


            new Thread(() -> {
                while(true){
                    f.repaint();
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            CY += 0.01;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            CY -= 0.01;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            CX -= 0.01;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            CX += 0.01;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("cx = " + CX);
            System.out.println("cy = " + CY);
            System.out.println("maxIterations = " + MAX_ITERATIONS);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}