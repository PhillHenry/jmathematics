package com.google.code.jmathematics.matrix;

import com.google.caliper.SimpleBenchmark;
import com.google.code.jmathematics.matrix.doubles.Double2DMatrix;
import com.google.code.jmathematics.matrix.doubles.Mutable2DDoubleMatrix;
import com.google.code.jmathematics.matrix.integer.Int2DMatrix;
import com.google.code.jmathematics.matrix.integer.Mutable2DIntMatrix;
import com.google.code.jmathematics.matrix.longs.Long2DMatrix;
import com.google.code.jmathematics.matrix.longs.Mutable2DLongMatrix;
import com.google.code.jmathematics.matrix.number.BigDecimalMutable2DMatrix;
import com.google.code.jmathematics.matrix.number.IntegerMutable2DMatrix;
import com.google.code.jmathematics.matrix.number.MutableNumber2DMatrix;

import java.math.BigDecimal;

/**
 * On a 64-bit, 16-core, Xeon(R) CPU E5-2687W 0 @ 3.10GHz (Using primitive doubles)
 0% Scenario{vm=java, trial=0, benchmark=Mutable2DDoubleMatrixCross} 899101.45 ns; σ=18207.88 ns @ 10 trials
 20% Scenario{vm=java, trial=0, benchmark=Mutable2DIntMatrixCross} 1124666.62 ns; σ=8221.81 ns @ 3 trials
 40% Scenario{vm=java, trial=0, benchmark=Mutable2DNumberIntMatrixCross} 9286670.69 ns; σ=87540.45 ns @ 3 trials
 60% Scenario{vm=java, trial=0, benchmark=Mutable2DNumberBigDecimalMatrixCross} 34734860.78 ns; σ=313435.58 ns @ 4 trials
 80% Scenario{vm=java, trial=0, benchmark=Mutable2DLongMatrixCross} 1042197.65 ns; σ=9154.55 ns @ 3 trials

 benchmark    us linear runtime
 Mutable2DDoubleMatrixCross             899 =
 Mutable2DIntMatrixCross               1125 =
 Mutable2DNumberIntMatrixCross         9287 ========
 Mutable2DNumberBigDecimalMatrixCross 34735 ==============================
 Mutable2DLongMatrixCross              1042 =

 *
 * On a 64-bit, 16-core, Xeon(R) CPU E5-2687W 0 @ 3.10GHz (Using Doubles)
 0% Scenario{vm=java, trial=0, benchmark=Mutable2DLongMatrixCross} 1052177.50 ns; σ=9489.12 ns @ 3 trials
 20% Scenario{vm=java, trial=0, benchmark=Mutable2DDoubleMatrixCross} 2874897.19 ns; σ=17769.76 ns @ 3 trials
 40% Scenario{vm=java, trial=0, benchmark=Mutable2DIntMatrixCross} 1130110.33 ns; σ=9865.28 ns @ 3 trials
 60% Scenario{vm=java, trial=0, benchmark=Mutable2DNumberIntMatrixCross} 9401464.68 ns; σ=93042.49 ns @ 5 trials
 80% Scenario{vm=java, trial=0, benchmark=Mutable2DNumberBigDecimalMatrixCross} 35476913.96 ns; σ=301030.49 ns @ 3 trials

 benchmark    ms linear runtime
 Mutable2DLongMatrixCross              1.05 =
 Mutable2DDoubleMatrixCross            2.87 ==
 Mutable2DIntMatrixCross               1.13 =
 Mutable2DNumberIntMatrixCross         9.40 =======
 Mutable2DNumberBigDecimalMatrixCross 35.48 ==============================

 */
public class MatrixBenchmark extends SimpleBenchmark {

    private final Mutable2DDoubleMatrix mutable2DDoubleMatrix;
    private final Mutable2DLongMatrix mutable2DLongMatrix;
    private final Mutable2DIntMatrix mutable2DIntMatrix;
    private final IntegerMutable2DMatrix integerMutable2DMatrix;
    private final BigDecimalMutable2DMatrix bigDecimalMutable2DMatrix;

    public MatrixBenchmark() {
        mutable2DDoubleMatrix = new Mutable2DDoubleMatrix(100, 100);
        mutable2DLongMatrix = new Mutable2DLongMatrix(100, 100);
        mutable2DIntMatrix = new Mutable2DIntMatrix(100, 100);
        integerMutable2DMatrix = new IntegerMutable2DMatrix(100, 100);
        bigDecimalMutable2DMatrix = new BigDecimalMutable2DMatrix(100, 100);
        populate(mutable2DDoubleMatrix);
        populate(mutable2DLongMatrix);
        populate(mutable2DIntMatrix);
        populate(integerMutable2DMatrix);
        populate(bigDecimalMutable2DMatrix);
    }

    private void populate(BigDecimalMutable2DMatrix bigDecimalMatrix) {
        for (int i = 0 ; i < bigDecimalMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < bigDecimalMatrix.getWidth() ; j++) {
                bigDecimalMatrix = (BigDecimalMutable2DMatrix) bigDecimalMatrix.set(j, i, new BigDecimal(i * j));
            }
        }
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
    
    private void populate(Int2DMatrix longMatrix) {
        for (int i = 0 ; i < longMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < longMatrix.getWidth() ; j++) {
                longMatrix = longMatrix.set(j, i, j * i);
            }
        }
    }
    
    private void populate(IntegerMutable2DMatrix longMatrix) {
        for (int i = 0 ; i < longMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < longMatrix.getWidth() ; j++) {
                longMatrix = (IntegerMutable2DMatrix) longMatrix.set(j, i, j * i);
            }
        }
    }

    /**
     * On a 1.8 GHz Intel Core i7 Mac OS X 10.7.5:
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
    
    /**
     * On a 1.8 GHz Intel Core i7 Mac OS X 10.7.5:
     * 67% Scenario{vm=java, trial=0, benchmark=Mutable2DIntMatrixCross} 1660000.58 ns; ?=194289.51 ns @ 10 trials
     */
    public int timeMutable2DIntMatrixCross(int reps) {
        Mutable2DIntMatrix accumulator = null;
        for (int i = 0 ; i < reps ; i++) {
            accumulator = mutable2DIntMatrix.cross(mutable2DIntMatrix);
        }
        return accumulator == null ? 0 : accumulator.getWidth();
    }

    /**
     * On a 1.8 GHz Intel Core i7 Mac OS X 10.7.5:
     * 75% Scenario{vm=java, trial=0, benchmark=Mutable2DNumberIntMatrixCross} 21126510.42 ns; ?=1989877.40 ns @ 10 trials
     */
    public int timeMutable2DNumberIntMatrixCross(int reps) {
        MutableNumber2DMatrix accumulator = null;
        for (int i = 0 ; i < reps ; i++) {
            accumulator = integerMutable2DMatrix.cross(integerMutable2DMatrix);
        }
        return accumulator == null ? 0 : accumulator.getWidth();
    }
    
    /**
     * On a 1.8 GHz Intel Core i7 Mac OS X 10.7.5:
     * 80% Scenario{vm=java, trial=0, benchmark=Mutable2DNumberBigDecimalMatrixCross} 58411000.00 ns; ?=3927557.14 ns @ 10 trials
     */
    public int timeMutable2DNumberBigDecimalMatrixCross(int reps) {
        MutableNumber2DMatrix accumulator = null;
        for (int i = 0 ; i < reps ; i++) {
            accumulator = bigDecimalMutable2DMatrix.cross(bigDecimalMutable2DMatrix);
        }
        return accumulator == null ? 0 : accumulator.getWidth();
    }
}
