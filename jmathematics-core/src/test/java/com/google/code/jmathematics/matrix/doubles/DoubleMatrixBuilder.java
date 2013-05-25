package com.google.code.jmathematics.matrix.doubles;

import com.google.code.jmathematics.matrix.doubles.DoubleMatrix;

public class DoubleMatrixBuilder {

    public <T extends DoubleMatrix> T initialize(T initial) {
        double index = 0;
        return initialize(initial, index);
    }

    public <T extends DoubleMatrix> T initialize(T initial, double index) {
        for (int y = 0; y < initial.getHeight(); y++) {
            for (int x = 0; x < initial.getWidth(); x++) {
                initial = initial.set(x, y, index++);
            }
        }
        return initial;
    }
    
    public <T extends DoubleMatrix> T initialize(T initial, double[][] matrix) {
        for (int y = 0 ; y < matrix.length ; y++) {
            for (int x = 0 ; x < matrix[y].length ; x++) {
                initial = initial.set(x, y, matrix[y][x]);
            }
        }
        return initial;
    }

}
