package com.google.code.jmathematics.matrix;

import com.google.caliper.SimpleBenchmark;

public class MatrixBenchmark extends SimpleBenchmark {

    private final Mutable2DDoubleMatrix mutable2DDoubleMatrix;

    public MatrixBenchmark() {
        mutable2DDoubleMatrix = new Mutable2DDoubleMatrix(100, 100);
        populate(mutable2DDoubleMatrix);
    }

    private void populate(Mutable2DDoubleMatrix mutable2DDoubleMatrix) {
        for (int i = 0 ; i < mutable2DDoubleMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < mutable2DDoubleMatrix.getWidth() ; j++) {
                mutable2DDoubleMatrix.set(j, i, j * i);
            }
        }
    }


    public int timeMutable2DDoubleMatrixCross(int reps) {
        Mutable2DDoubleMatrix accumulator = null;
        for (int i = 0 ; i < reps ; i++) {
            accumulator = mutable2DDoubleMatrix.cross(mutable2DDoubleMatrix);
        }
        return accumulator == null ? 0 : accumulator.getWidth();
    }

}
