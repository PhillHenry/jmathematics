package com.google.code.jmathematics.matrix.longs;

import com.google.code.jmathematics.matrix.MatrixChecker;
import com.google.code.jmathematics.matrix.NoisyMatrixChecker;
import com.google.code.jmathematics.matrix.determinant.DoubleDeterminantVisitor;
import com.google.code.jmathematics.matrix.determinant.LongDeterminantVisitor;

public class MutableLongMatrix implements LongMatrix {
    
    private final long[][]          matrix;
    private final int               width;
    private final int               height;
    private final MatrixChecker   sizeChecker;

    public MutableLongMatrix(int rows, int columns) {
        width       = columns;
        height      = rows;
        matrix      = new long[width][height];
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
    public LongMatrix transpose() {
        MutableLongMatrix other = new MutableLongMatrix(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                other.set(y, x, this.get(x, y));
            }
        }
        return other;
    }

    @Override
    public MutableLongMatrix cross(LongMatrix other) {
        sizeChecker.checkDimensions(other);
        MutableLongMatrix toReturn = new MutableLongMatrix(height,
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

    @Override
	public LongMatrix set(int x, int y, Number value) {
    	return set(x, y, value.longValue());
	}

	@SuppressWarnings("unchecked")
    @Override
    public MutableLongMatrix set(int x, int y, long value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public long get(int x, int y) {
        return matrix[x][y];
    }

    @Override
	public Number dotProduct(LongMatrix other) {
		return dot(other);
	}

	@Override
    public long dot(LongMatrix other) {
        sizeChecker.checkDimensions(other);

        long total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total += matrix[x][y] * other.get(y, x);
            }
        }
        return total;
    }

    @Override
    public LongMatrix add(LongMatrix other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, get(x, y) + other.get(x, y));
            }
        }
        return this;
    }

    @Override
    public LongMatrix scalar(Number value) {
        return scalar(value.longValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public MutableLongMatrix scalar(long other) {
        MULTIPLY.mutate(other);
        return this;
    }
    
    @Override
    public LongMatrix add(Number value) {
        ADD.mutate(value.longValue());
        return this;
    }

    private final Mutator MULTIPLY = new Mutator() {
        @Override
        protected double transform(long value, long other) {
            return value * other;
        }
    };
    
    private final Mutator ADD = new Mutator() {
        @Override
        protected double transform(long value, long other) {
            return value + other;
        }
    };
    
    private abstract class Mutator {
        public void mutate(long other) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    set(x, y, transform(get(x, y), other));
                }
            }
        }
        protected abstract double transform(long value, long other);
    }
    @Override
    public long determinant(LongDeterminantVisitor visitor) {
        return visitor.calculateDeterminant(this);
    }

}
