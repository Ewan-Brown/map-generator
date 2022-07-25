package com.ewan.landgenerator.display;

import com.ewan.landgenerator.math.MapGenerators;

import java.awt.*;

public class TestDisplay extends Display {

    Color[][] grid;
    private int width;
    private int height;

    public TestDisplay(int w, int h){
        width = w;
        height = h;
    }

    public Color[][] getColorGrid() {
        if(grid == null) {

            MapGenerators.JuliaGenerator gen = MapGenerators.JuliaGenerator.builder(width, height).build();
            double[][] map = gen.create();

            grid = new Color[height][width];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int val = (int) Math.floor(map[i][j] * 255.0);
                    grid[i][j] = new Color(val,val,val);
                }
            }

        }
        return grid;
    }
}
