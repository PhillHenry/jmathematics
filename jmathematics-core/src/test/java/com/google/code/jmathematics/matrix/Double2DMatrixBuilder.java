package com.google.code.jmathematics.matrix;

import com.google.code.jmathematics.matrix.doubles.Double2DMatrix;

public class Double2DMatrixBuilder {

    public <T extends Double2DMatrix> T initialize(T initial) {
        double index = 0;
        return initialize(initial, index);
    }

    public <T extends Double2DMatrix> T initialize(T initial, double index) {
        for (int y = 0; y < initial.getHeight(); y++) {
            for (int x = 0; x < initial.getWidth(); x++) {
                initial = initial.set(x, y, index++);
            }
        }
        return initial;
    }
    
    public <T extends Double2DMatrix> T initialize(T initial, double[][] matrix) {
        for (int y = 0 ; y < matrix.length ; y++) {
            for (int x = 0 ; x < matrix[y].length ; x++) {
                initial = initial.set(x, y, matrix[y][x]);
            }
        }
        return initial;
    }

}
