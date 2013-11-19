package com.google.code.jmathematics.matrix.doubles;

import com.google.code.jmathematics.matrix.MatrixChecker;
import com.google.code.jmathematics.matrix.NoisyMatrixChecker;
import com.google.code.jmathematics.matrix.determinant.DoubleDeterminantVisitor;
import com.google.code.jmathematics.permutation.IntPermutations;
import com.google.code.jmathematics.permutation.SimpleIntPermutation;
import com.google.code.jmathematics.permutation.leviCivita.LeviCivita;
import com.google.code.jmathematics.permutation.leviCivita.SimpleLeviCivita;

public class MutableDoubleMatrix implements DoubleMatrix {

    private final double[][]        matrix;
    private final int               width;
    private final int               height;
    private final MatrixChecker   sizeChecker;

    public MutableDoubleMatrix(int rows, int columns) {
        width       = columns;
        height      = rows;
        matrix      = new double[width][height];
        sizeChecker = new NoisyMatrixChecker(this);
    }

    @Override
	public DoubleMatrix set(int x, int y, Double value) {
		return set(x, y, value.doubleValue());
	}

	@Override
    public MutableDoubleMatrix set(int x, int y, double value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public MutableDoubleMatrix cross(DoubleMatrix other) {
        sizeChecker.checkDimensions(other);
        MutableDoubleMatrix toReturn = new MutableDoubleMatrix(height,
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
	public Double dotProduct(DoubleMatrix other) {
		return dot(other);
	}

	@Override
    public double dot(DoubleMatrix other) {
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
    public MutableDoubleMatrix transpose() {
        MutableDoubleMatrix other = new MutableDoubleMatrix(width, height);
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
    
    @Override
    public DoubleMatrix add(Double other) {
        ADD.mutate(other.doubleValue());
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MutableDoubleMatrix scalar(double other) {
        MULTIPLY.mutate(other);
        return this;
    }
    
    private final Mutator MULTIPLY = new Mutator() {
        @Override
        protected double transform(double value, double other) {
            return value * other;
        }
    };
    
    private final Mutator ADD = new Mutator() {
        @Override
        protected double transform(double value, double other) {
            return value + other;
        }
    };
    
    private abstract class Mutator {
        public void mutate(double other) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    set(x, y, transform(get(x, y), other));
                }
            }
        }
        protected abstract double transform(double value, double other);
    }

    @Override
    public DoubleMatrix add(DoubleMatrix other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, get(x, y) + other.get(x, y));
            }
        }
        return this;
    }

    @Override
    public DoubleMatrix scalar(Double value) {
        return scalar(value.doubleValue());
    }

    @Override
    public boolean equals(Object something) { 
        if (something == null)
            return false;
        if (!(something instanceof DoubleMatrix))
            return false;
        DoubleMatrix other = (DoubleMatrix) something;
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
