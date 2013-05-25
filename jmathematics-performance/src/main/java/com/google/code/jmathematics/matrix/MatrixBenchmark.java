package com.google.code.jmathematics.matrix;

import com.google.caliper.SimpleBenchmark;
import com.google.code.jmathematics.matrix.doubles.DoubleMatrix;
import com.google.code.jmathematics.matrix.doubles.MutableDoubleMatrix;
import com.google.code.jmathematics.matrix.integer.IntMatrix;
import com.google.code.jmathematics.matrix.integer.MutableIntMatrix;
import com.google.code.jmathematics.matrix.longs.LongMatrix;
import com.google.code.jmathematics.matrix.longs.MutableLongMatrix;
import com.google.code.jmathematics.matrix.number.BigDecimalMutableMatrix;
import com.google.code.jmathematics.matrix.number.IntegerMutableMatrix;
import com.google.code.jmathematics.matrix.number.MutableNumberMatrix;

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

    private final MutableDoubleMatrix mutable2DDoubleMatrix;
    private final MutableLongMatrix mutable2DLongMatrix;
    private final MutableIntMatrix mutable2DIntMatrix;
    private final IntegerMutableMatrix integerMutable2DMatrix;
    private final BigDecimalMutableMatrix bigDecimalMutable2DMatrix;

    public MatrixBenchmark() {
        mutable2DDoubleMatrix = new MutableDoubleMatrix(100, 100);
        mutable2DLongMatrix = new MutableLongMatrix(100, 100);
        mutable2DIntMatrix = new MutableIntMatrix(100, 100);
        integerMutable2DMatrix = new IntegerMutableMatrix(100, 100);
        bigDecimalMutable2DMatrix = new BigDecimalMutableMatrix(100, 100);
        populate(mutable2DDoubleMatrix);
        populate(mutable2DLongMatrix);
        populate(mutable2DIntMatrix);
        populate(integerMutable2DMatrix);
        populate(bigDecimalMutable2DMatrix);
    }

    private void populate(BigDecimalMutableMatrix bigDecimalMatrix) {
        for (int i = 0 ; i < bigDecimalMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < bigDecimalMatrix.getWidth() ; j++) {
                bigDecimalMatrix = (BigDecimalMutableMatrix) bigDecimalMatrix.set(j, i, new BigDecimal(i * j));
            }
        }
    }

    private void populate(DoubleMatrix doubleMatrix) {
        for (int i = 0 ; i < doubleMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < doubleMatrix.getWidth() ; j++) {
                doubleMatrix = doubleMatrix.set(j, i, j * i * 1.1d);
            }
        }
    }
    
    private void populate(LongMatrix longMatrix) {
        for (int i = 0 ; i < longMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < longMatrix.getWidth() ; j++) {
                longMatrix = longMatrix.set(j, i, j * i);
            }
        }
    }
    
    private void populate(IntMatrix longMatrix) {
        for (int i = 0 ; i < longMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < longMatrix.getWidth() ; j++) {
                longMatrix = longMatrix.set(j, i, j * i);
            }
        }
    }
    
    private void populate(IntegerMutableMatrix longMatrix) {
        for (int i = 0 ; i < longMatrix.getHeight() ; i++) {
            for (int j = 0 ; j < longMatrix.getWidth() ; j++) {
                longMatrix = (IntegerMutableMatrix) longMatrix.set(j, i, j * i);
            }
        }
    }

    /**
     * On a 1.8 GHz Intel Core i7 Mac OS X 10.7.5:
     * 50% Scenario{vm=java, trial=0, benchmark=Mutable2DLongMatrixCross} 1402965.83 ns; ?=12970.73 ns @ 6 trials
     */
    public int timeMutable2DLongMatrixCross(int reps) {
        MutableLongMatrix accumulator = null;
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
        MutableDoubleMatrix accumulator = null;
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
        MutableIntMatrix accumulator = null;
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
        MutableNumberMatrix accumulator = null;
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
        MutableNumberMatrix accumulator = null;
        for (int i = 0 ; i < reps ; i++) {
            accumulator = bigDecimalMutable2DMatrix.cross(bigDecimalMutable2DMatrix);
        }
        return accumulator == null ? 0 : accumulator.getWidth();
    }
}
