package com.ewan.landgenerator.math;

import com.ewan.landgenerator.math.utils.ImprovedPerlin;
import lombok.Builder;

//List of factory classes to generate double[] maps given parameters
//TODO clean up duplications
public class MapGenerators {

    @Builder(builderMethodName = "hiddenBuilder")
    static class PerlinGenerator{
        private int width;
        private int height;
        private double offset = 0;
        private double amplitude = 1;
        private double frequency;

        public static PerlinGeneratorBuilder builder(int w, int h){
            return hiddenBuilder().width(w).height(h);
        }

        public double[][] create(){
            double[][] map = new double[height][width];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    map[j][i] = ImprovedPerlin.noise((double)(i) / frequency, (double)j / frequency, 0) * amplitude + offset;
                }
            }
            return map;
        }
    }

    @Builder(builderMethodName = "hiddenBuilder")
    static class RandomGenerator{
        private int width;
        private int height;
        private double amplitude = 1;
        private double offset = 0;

        public static RandomGeneratorBuilder builder(int w, int h){
            return hiddenBuilder().width(w).height(h);
        }

        public double[][] create(){
            double[][] map = new double[height][width];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    map[j][i] = Math.random() * amplitude + offset;
                }
            }
            return map;
        }
    }

}
