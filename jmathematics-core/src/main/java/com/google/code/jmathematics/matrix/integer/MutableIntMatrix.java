package com.google.code.jmathematics.matrix.integer;

import com.google.code.jmathematics.matrix.MatrixChecker;
import com.google.code.jmathematics.matrix.NoisyMatrixChecker;
import com.google.code.jmathematics.matrix.determinant.IntDeterminantVisitor;
import com.google.code.jmathematics.matrix.doubles.DoubleMatrix;

public class MutableIntMatrix implements IntMatrix {
    
    private final int[][]           matrix;
    private final int               width;
    private final int               height;
    private final MatrixChecker   sizeChecker;

    public MutableIntMatrix(int rows, int columns) {
        width       = columns;
        height      = rows;
        matrix      = new int[width][height];
        sizeChecker = new NoisyMatrixChecker(this);
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
    public IntMatrix transpose() {
        MutableIntMatrix other = new MutableIntMatrix(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                other.set(y, x, this.get(x, y));
            }
        }
        return other;
    }

    @Override
    public MutableIntMatrix cross(IntMatrix other) {
        sizeChecker.checkDimensions(other);
        
        MutableIntMatrix toReturn = new MutableIntMatrix(height, other.getWidth());
        
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
    
    @Override
	public MutableIntMatrix set(int x, int y, Integer value) {
		return set(x, y, value.intValue());
	}
    
    @SuppressWarnings("unchecked")
    @Override
    public MutableIntMatrix set(int x, int y, int value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public int get(int x, int y) {
        return matrix[x][y];
    }

    @Override
	public Integer dotProduct(IntMatrix other) {
		return dot(other);
	}

	@Override
    public int dot(IntMatrix other) {
        sizeChecker.checkDimensions(other);

        int total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total += matrix[x][y] * other.get(y, x);
            }
        }
        return total;
    }

    @Override
    public IntMatrix scalar(Integer value) {
        return scalar(value.intValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public MutableIntMatrix scalar(int other) {
        MULTIPLY.mutate(other);
        return this;
    }

    @Override
    public IntMatrix add(Integer value) {
        ADD.mutate(value.intValue());
        return this;
    }

    @Override
    public IntMatrix add(IntMatrix other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, get(x, y) + other.get(x, y));
            }
        }
        return this;
    }

    private final Mutator MULTIPLY = new Mutator() {
        @Override
        protected int transform(int value, int other) {
            return value * other;
        }
    };
    
    private final Mutator ADD = new Mutator() {
        @Override
        protected int transform(int value, int other) {
            return value + other;
        }
    };
    
    private abstract class Mutator {
        public void mutate(int other) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    set(x, y, transform(get(x, y), other));
                }
            }
        }
        protected abstract int transform(int value, int other);
    }
    
    @Override
    public int determinant(IntDeterminantVisitor visitor) {
        return visitor.calculateDeterminant(this);
    }

}
