package com.google.code.jmathematics.matrix;

import com.google.caliper.SimpleBenchmark;
import com.google.code.jmathematics.matrix.doubles.Double2DMatrix;
import com.google.code.jmathematics.matrix.doubles.Mutable2DDoubleMatrix;
import com.google.code.jmathematics.matrix.longs.Long2DMatrix;
import com.google.code.jmathematics.matrix.longs.Mutable2DLongMatrix;


public class MatrixBenchmark extends SimpleBenchmark {

    private final Mutable2DDoubleMatrix mutable2DDoubleMatrix;
    private final Mutable2DLongMatrix mutable2DLongMatrix;

    public MatrixBenchmark() {
        mutable2DDoubleMatrix = new Mutable2DDoubleMatrix(100, 100);
        mutable2DLongMatrix = new Mutable2DLongMatrix(100, 100);
        populate(mutable2DDoubleMatrix);
        populate(mutable2DLongMatrix);
    }

    private void populate(Double2DMatrix doubleMatrix) {
        for (int i = 0 ; i < doubleMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < doubleMatrix.getWidth() ; j++) {
                doubleMatrix = doubleMatrix.set(j, i, j * i * 1.1d);
            }
        }
    }
    
    private void populate(Long2DMatrix longMatrix) {
        for (int i = 0 ; i < longMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < longMatrix.getWidth() ; j++) {
                longMatrix = longMatrix.set(j, i, j * i);
            }
        }
    }

    /**
     * 50% Scenario{vm=java, trial=0, benchmark=Mutable2DLongMatrixCross} 1402965.83 ns; ?=12970.73 ns @ 6 trials
     */
    public int timeMutable2DLongMatrixCross(int reps) {
        Mutable2DLongMatrix accumulator = null;
        for (int i = 0 ; i < reps ; i++) {
            accumulator = mutable2DLongMatrix.cross(mutable2DLongMatrix);
        }
        return accumulator == null ? 0 : accumulator.getWidth();
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
