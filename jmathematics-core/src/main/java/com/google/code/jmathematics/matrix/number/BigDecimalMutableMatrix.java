package com.google.code.jmathematics.matrix.number;

import java.math.BigDecimal;

public class BigDecimalMutableMatrix extends MutableNumberMatrix {

    public BigDecimalMutableMatrix(int rows, int columns) {
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
    protected MutableNumberMatrix create(int width, int height) {
        return new BigDecimalMutableMatrix(height, width);
    }
    
    @Override
    public Number zero() {
        return new BigDecimal(0);
    }
}
