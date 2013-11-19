package com.google.code.jmathematics.matrix.number;

import com.google.code.jmathematics.matrix.MatrixChecker;
import com.google.code.jmathematics.matrix.NoisyMatrixChecker;
import com.google.code.jmathematics.matrix.determinant.NumberDeterminantVisitor;

public abstract class MutableNumberMatrix<T extends Number> implements NumberMatrix<T> {
    
    private final T[][]             matrix;
    private final int               width;
    private final int               height;
    private final MatrixChecker   sizeChecker;

    public MutableNumberMatrix(int rows, int columns) {
        width       = columns;
        height      = rows;
        matrix      = (T[][]) new Number[width][height];
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
    public <U extends NumberMatrix<T>> U transpose() {
        MutableNumberMatrix<T> other = create(height, width);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                other.set(y, x, this.get(x, y));
            }
        }
        return (U)other;
    }

    @Override
    public MutableNumberMatrix<T> cross(NumberMatrix<T> other) {
        sizeChecker.checkDimensions(other);
        MutableNumberMatrix<T> toReturn = create(height, other.getWidth());

        for (int y = 0; y < height; y++) {
            for (int z = 0; z < other.getWidth(); z++) {
                T total = zero();
                for (int x = 0; x < width; x++) {
                    T thisVal = this.get(x, y);
                    T thatVal = other.get(z, x);
                    total = multiplyAndAdd(total, thisVal, thatVal);
                }
                toReturn = toReturn.set(z, y, total);
            }
        }

        return toReturn;
    }
    
    public abstract T zero();
    
    public abstract T multiplyAndAdd(T accumulator, T thisValue, T thatValue);
    
    public abstract T multiply(T thisValue, T thatValue);
    
    protected abstract MutableNumberMatrix<T> create(int width, int height);

    @Override
    public MutableNumberMatrix<T> set(int x, int y, T value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public T get(int x, int y) {
        return matrix[x][y];
    }

    @Override
	public T dotProduct(NumberMatrix<T> other) {
		return dot(other);
	}

	@Override
    public <U extends NumberMatrix<T>> T dot(U other) {
        sizeChecker.checkDimensions(other);

        T total = zero();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total = multiplyAndAdd(total, matrix[x][y], other.get(y, x));
            }
        }
        return (T)total;
    }

    @Override
    public NumberMatrix<T> add(T value) {
        ADD.mutate(value);
        return this;
    }

    @Override
    public <U extends NumberMatrix<T>> U scalar(T other) {
        MULTIPLY.mutate(other);
        return (U)this;
    }

    @Override
    public <U extends NumberMatrix<T>> U add(NumberMatrix<T> other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, add(get(x, y), other.get(x, y)));
            }
        }
        return (U)this;
    }

    private final Mutator MULTIPLY = new Mutator() {
        @Override
        protected T transform(T value, T other) {
            return multiply(value, other);
        }
    };
    
    private final Mutator ADD = new Mutator() {
        @Override
        protected T transform(T value, T other) {
            return add(value, other);
        }
    };
    
    private abstract class Mutator {
        public void mutate(T other) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    set(x, y, transform(get(x, y), other));
                }
            }
        }
        protected abstract T transform(T value, T other);
    }
    
    
    @Override
    public T determinant(NumberDeterminantVisitor<T> visitor) {
        return visitor.calculateDeterminant(this);
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
}
