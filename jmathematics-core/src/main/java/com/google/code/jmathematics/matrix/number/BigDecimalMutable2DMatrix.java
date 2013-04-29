package com.google.code.jmathematics.matrix.number;

import java.math.BigDecimal;

public class BigDecimalMutable2DMatrix extends MutableNumber2DMatrix {

    public BigDecimalMutable2DMatrix(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public Number add(Number thisValue, Number thatValue) {
        return ((BigDecimal)thisValue).add((BigDecimal)thatValue);
    }

    @Override
    public Number multiplyAndAdd(Number accumulator, Number thisValue,
            Number thatValue) {
        return add(accumulator, multiply(thisValue, thatValue));
    }

    @Override
    public Number multiply(Number thisValue, Number thatValue) {
        return ((BigDecimal)thisValue).multiply((BigDecimal)thatValue);
    }

    @Override
    protected MutableNumber2DMatrix create(int width, int height) {
        return new BigDecimalMutable2DMatrix(height, width);
    }
    
    @Override
    public Number zero() {
        return new BigDecimal(0);
    }
}
