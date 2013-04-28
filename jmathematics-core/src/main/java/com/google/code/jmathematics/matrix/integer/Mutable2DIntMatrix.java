package com.google.code.jmathematics.matrix.integer;

import com.google.code.jmathematics.matrix.Matrix2DChecker;
import com.google.code.jmathematics.matrix.NoisyMatrix2DChecker;
import com.google.code.jmathematics.matrix.determinant.IntDeterminantVisitor;

public class Mutable2DIntMatrix implements Int2DMatrix {
    
    private final int[][]           matrix;
    private final int               width;
    private final int               height;
    private final Matrix2DChecker   sizeChecker;

    public Mutable2DIntMatrix(int rows, int columns) {
        width       = columns;
        height      = rows;
        matrix      = new int[width][height];
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
    public Int2DMatrix transpose() {
        Mutable2DIntMatrix other = new Mutable2DIntMatrix(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                other.set(y, x, this.get(x, y));
            }
        }
        return other;
    }

    @Override
    public Mutable2DIntMatrix cross(Int2DMatrix other) {
        sizeChecker.checkDimensions(other);
        
        Mutable2DIntMatrix toReturn = new Mutable2DIntMatrix(height, other.getWidth());
        
        for (int y = 0; y < height; y++) {
            for (int z = 0; z < other.getWidth(); z++) {
                int total = 0;
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
    public Mutable2DIntMatrix set(int x, int y, int value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public int get(int x, int y) {
        return matrix[x][y];
    }

    @Override
    public int dot(Int2DMatrix other) {
        sizeChecker.checkDimensions(other);

        int total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total += matrix[x][y] * other.get(y, x);
            }
        }
        return total;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Mutable2DIntMatrix scalar(int other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, get(x, y) * other);
            }
        }
        return this;
    }

    @Override
    public int determinant(IntDeterminantVisitor visitor) {
        return visitor.calculateDeterminant(this);
    }

}
