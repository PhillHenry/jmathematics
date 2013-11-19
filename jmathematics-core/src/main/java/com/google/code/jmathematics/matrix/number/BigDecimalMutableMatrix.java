package com.google.code.jmathematics.matrix.number;

import java.math.BigDecimal;

public class BigDecimalMutableMatrix extends MutableNumberMatrix {

    public BigDecimalMutableMatrix(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public BigDecimal add(Number thisValue, Number thatValue) {
        return ((BigDecimal)thisValue).add((BigDecimal)thatValue);
    }

    @Override
    public BigDecimal multiplyAndAdd(Number accumulator, Number thisValue,
            Number thatValue) {
        return add(accumulator, multiply(thisValue, thatValue));
    }

    @Override
    public BigDecimal multiply(Number thisValue, Number thatValue) {
        return ((BigDecimal)thisValue).multiply((BigDecimal)thatValue);
    }

    @Override
    protected BigDecimalMutableMatrix create(int width, int height) {
        return new BigDecimalMutableMatrix(height, width);
    }
    
    @Override
    public BigDecimal zero() {
        return new BigDecimal(0);
    }

    @Override
    public BigDecimal dotProduct(NumberMatrix other) {
        return (BigDecimal)super.dotProduct(other);
    }

    public BigDecimalMutableMatrix set(int x, int y, BigDecimal value) {
        return (BigDecimalMutableMatrix) super.set(x, y, value);
    }
    
    
}
