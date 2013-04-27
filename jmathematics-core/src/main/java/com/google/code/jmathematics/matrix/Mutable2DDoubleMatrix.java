package com.google.code.jmathematics.matrix;

import com.google.code.jmathematics.matrix.determinant.DoubleDeterminantVisitor;
import com.google.code.jmathematics.permutation.IntPermutations;
import com.google.code.jmathematics.permutation.SimpleIntPermutation;
import com.google.code.jmathematics.permutation.leviCivita.LeviCivita;
import com.google.code.jmathematics.permutation.leviCivita.SimpleLeviCivita;

public class Mutable2DDoubleMatrix implements Double2DMatrix {

    private final double[][]        matrix;
    private final int               width;
    private final int               height;
    private final Matrix2DChecker   sizeChecker;

    public Mutable2DDoubleMatrix(int rows, int columns) {
        width       = columns;
        height      = rows;
        matrix      = new double[width][height];
        sizeChecker = new NoisyMatrix2DChecker(this);
    }

    @Override
    public Mutable2DDoubleMatrix set(int x, int y, double value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public Mutable2DDoubleMatrix cross(Double2DMatrix other) {
        sizeChecker.checkDimensions(other);
        Mutable2DDoubleMatrix toReturn = new Mutable2DDoubleMatrix(height,
                other.getWidth());

        for (int y = 0; y < height; y++) {
            for (int z = 0; z < other.getWidth(); z++) {
                double total = 0;
                for (int x = 0; x < width; x++) {
                    double thisVal = this.get(x, y);
                    double thatVal = other.get(z, x);
                    total += thisVal * thatVal;
                }
                toReturn = toReturn.set(z, y, total);
            }
        }

        return toReturn;
    }

    @Override
    public double dot(Double2DMatrix other) {
        sizeChecker.checkDimensions(other);

        double total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total += matrix[x][y] * other.get(y, x);
            }
        }
        return total;
    }

    @Override
    public Mutable2DDoubleMatrix transpose() {
        Mutable2DDoubleMatrix other = new Mutable2DDoubleMatrix(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                other.set(y, x, this.get(x, y));
            }
        }
        return other;
    }

    @Override
    public double get(int x, int y) {
        return matrix[x][y];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Mutable2DDoubleMatrix scalar(double other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, get(x, y) * other);
            }
        }
        return this;
    }

    @Override
    public boolean equals(Object something) { 
        if (something == null)
            return false;
        if (!(something instanceof Double2DMatrix))
            return false;
        Double2DMatrix other = (Double2DMatrix) something;
        if (other.getHeight() != this.getHeight())
            return false;
        if (other.getWidth() != this.getWidth())
            return false;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (other.get(x, y) != this.get(x, y))
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer szb = new StringBuffer();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                szb.append("\t").append(this.get(x, y));
            }
            szb.append("\n");
        }
        return szb.toString();
    }

    @Override
    public double determinant(DoubleDeterminantVisitor visitor) {
        return visitor.calculateDeterminant(this);
    }



}
