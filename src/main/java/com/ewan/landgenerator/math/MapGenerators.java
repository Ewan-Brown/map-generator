package com.ewan.landgenerator.math;

import com.ewan.landgenerator.math.utils.ImprovedPerlin;
import lombok.Builder;

//List of factory classes to generate double[] maps given parameters
//TODO clean up duplications
public class MapGenerators {

    @Builder(builderMethodName = "hiddenBuilder")
    public static class PerlinGenerator{
        private int width;
        private int height;
        private double frequency;

        public static PerlinGeneratorBuilder builder(int w, int h){
            return hiddenBuilder().width(w).height(h);
        }

        public double[][] create(){
            double[][] map = new double[height][width];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    map[j][i] = ImprovedPerlin.noise((double)(i) / frequency, (double)j / frequency, 0);
                }
            }
            return map;
        }
    }

    @Builder(builderMethodName = "hiddenBuilder")
    public static class RandomGenerator{
        private int width;
        private int height;

        public static RandomGeneratorBuilder builder(int w, int h){
            return hiddenBuilder().width(w).height(h);
        }

        public double[][] create(){
            double[][] map = new double[height][width];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    map[j][i] = Math.random();
                }
            }
            return map;
        }
    }

    @Builder(builderMethodName = "hiddenBuilder")
    public static class JuliaGenerator{
        private int width;
        private int height;

        @Builder.Default
        private int maxIterations = 300;
        @Builder.Default
        private double zoom = 1;
        @Builder.Default
        private double cx = -0.7;
        @Builder.Default
        private double cy = 0.27015;
        private double moveX;
        private double moveY;

        public static JuliaGeneratorBuilder builder(int w, int h){
            return hiddenBuilder().width(w).height(h);
        }

        public double[][] create(){
            double[][] map = new double[height][width];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    double zx = 1.5 * (x - width / 2.0) / (0.5 * zoom * width) + moveX;
                    double zy = (y - height / 2.0) / (0.5 * zoom * height) + moveY;
                    float i = maxIterations;
                    while (zx * zx + zy * zy < 4 && i > 0) {
                        double tmp = zx * zx - zy * zy + cx;
                        zy = 2.0 * zx * zy + cy;
                        zx = tmp;
                        i--;
                    }
                    map[y][x] = i / (double) maxIterations;
                    System.out.println(i);
                }
            }

            return map;
        }
    }

}
