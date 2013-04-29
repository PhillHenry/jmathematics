package com.google.code.jmathematics.matrix.number;

@SuppressWarnings("unchecked")
public class IntegerMutable2DMatrix extends MutableNumber2DMatrix {

    public IntegerMutable2DMatrix(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public Number add(Number thisValue, Number thatValue) {
        return ((Integer)thisValue) + ((Integer)thatValue);
    }

    @Override
    public Number multiplyAndAdd(Number accumulator, Number thisValue,
            Number thatValue) {
        return add(accumulator, multiply(thisValue, thatValue));
    }

    @Override
    public Number multiply(Number thisValue, Number thatValue) {
        return ((Integer)thisValue) * ((Integer)thatValue);
    }

    @Override
    protected MutableNumber2DMatrix create(int width, int height) {
        return new IntegerMutable2DMatrix(height, width);
    }

    @Override
    public Number zero() {
        return 0;
    }

}
