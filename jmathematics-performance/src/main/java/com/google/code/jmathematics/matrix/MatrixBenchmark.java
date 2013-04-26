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

    /**
     * On a 1.8 GHz Intel Core i7 Mac OS X 10.7.5:
     *  0% Scenario{vm=java, trial=0, benchmark=Mutable2DDoubleMatrixCross} 1253573.57 ns; ?=276697.20 ns @ 10 trials
     *  0% Scenario{vm=java, trial=0, benchmark=Mutable2DDoubleMatrixCross} 1220446.18 ns; ?=53137.85 ns @ 10 trials
     */
    public int timeMutable2DDoubleMatrixCross(int reps) {
        Mutable2DDoubleMatrix accumulator = null;
        for (int i = 0 ; i < reps ; i++) {
            accumulator = mutable2DDoubleMatrix.cross(mutable2DDoubleMatrix);
        }
        return accumulator == null ? 0 : accumulator.getWidth();
    }

}
