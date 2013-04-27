package com.google.code.jmathematics.matrix.longs;

import com.google.code.jmathematics.matrix.Matrix2DChecker;
import com.google.code.jmathematics.matrix.NoisyMatrix2DChecker;

public class Mutable2DLongMatrix implements Long2DMatrix {
    
    private final long[][]          matrix;
    private final int               width;
    private final int               height;
    private final Matrix2DChecker   sizeChecker;

    private Mutable2DLongMatrix(int rows, int columns) {
        width       = columns;
        height      = rows;
        matrix      = new long[width][height];
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
    public Long2DMatrix transpose() {
        Mutable2DLongMatrix other = new Mutable2DLongMatrix(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                other.set(y, x, this.get(x, y));
            }
        }
        return other;
    }

    @Override
    public Mutable2DLongMatrix cross(Long2DMatrix other) {
        sizeChecker.checkDimensions(other);
        Mutable2DLongMatrix toReturn = new Mutable2DLongMatrix(height,
                other.getWidth());

        for (int y = 0; y < height; y++) {
            for (int z = 0; z < other.getWidth(); z++) {
                long total = 0;
                for (int x = 0; x < width; x++) {
                    long thisVal = this.get(x, y);
                    long thatVal = other.get(z, x);
                    total += thisVal * thatVal;
                }
                toReturn = toReturn.set(z, y, total);
            }
        }

        return toReturn;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Mutable2DLongMatrix set(int x, int y, long value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public long get(int x, int y) {
        return matrix[x][y];
    }

    @Override
    public long dot(Long2DMatrix other) {
        sizeChecker.checkDimensions(other);

        long total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total += matrix[x][y] * other.get(y, x);
            }
        }
        return total;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Mutable2DLongMatrix scalar(long other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, get(x, y) * other);
            }
        }
        return this;
    }

}
