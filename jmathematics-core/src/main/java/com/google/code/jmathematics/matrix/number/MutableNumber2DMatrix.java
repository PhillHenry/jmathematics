package com.google.code.jmathematics.matrix.number;

import com.google.code.jmathematics.matrix.Matrix2DChecker;
import com.google.code.jmathematics.matrix.NoisyMatrix2DChecker;
import com.google.code.jmathematics.matrix.determinant.NumberDeterminantVisitor;

public abstract class MutableNumber2DMatrix implements Number2DMatrix {
    
    private final Number[][]        matrix;
    private final int               width;
    private final int               height;
    private final Matrix2DChecker   sizeChecker;

    public MutableNumber2DMatrix(int rows, int columns) {
        width       = columns;
        height      = rows;
        matrix      = new Number[width][height];
        sizeChecker = new NoisyMatrix2DChecker(this);
    }
    
    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public MutableNumber2DMatrix transpose() {
        MutableNumber2DMatrix other = create(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                other.set(y, x, this.get(x, y));
            }
        }
        return other;
    }

    @Override
    public MutableNumber2DMatrix cross(Number2DMatrix other) {
        sizeChecker.checkDimensions(other);
        MutableNumber2DMatrix toReturn = create(height, other.getWidth());

        for (int y = 0; y < height; y++) {
            for (int z = 0; z < other.getWidth(); z++) {
                Number total = zero();
                for (int x = 0; x < width; x++) {
                    Number thisVal = this.get(x, y);
                    Number thatVal = other.get(z, x);
                    total = multiplyAndAdd(total, thisVal, thatVal);
                }
                toReturn = toReturn.set(z, y, total);
            }
        }

        return toReturn;
    }
    
    public abstract Number zero();
    
    public abstract Number multiplyAndAdd(Number accumulator, Number thisValue, Number thatValue);
    
    public abstract Number multiply(Number thisValue, Number thatValue);
    
    protected abstract MutableNumber2DMatrix create(int width, int height);

    @SuppressWarnings("unchecked")
    @Override
    public MutableNumber2DMatrix set(int x, int y, Number value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public Number get(int x, int y) {
        return matrix[x][y];
    }

    @Override
    public Number dot(Number2DMatrix other) {
        sizeChecker.checkDimensions(other);

        Number total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total = multiplyAndAdd(total, matrix[x][y], other.get(y, x));
            }
        }
        return total;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MutableNumber2DMatrix scalar(Number other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, multiply(get(x, y), other));
            }
        }
        return this;
    }

    @Override
    public Number determinant(NumberDeterminantVisitor visitor) {
        return visitor.calculateDeterminant(this);
    }

}
