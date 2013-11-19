package com.google.code.jmathematics.matrix.number;

import java.math.BigDecimal;

public class BigDecimalMutableMatrix extends MutableNumberMatrix<BigDecimal> {

    public BigDecimalMutableMatrix(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public BigDecimal add(BigDecimal thisValue, BigDecimal thatValue) {
        return (thisValue).add(thatValue);
    }

    @Override
    public BigDecimal multiplyAndAdd(BigDecimal accumulator, BigDecimal thisValue,
        BigDecimal thatValue) {
        return add(accumulator, multiply(thisValue, thatValue));
    }

    @Override
    public BigDecimal multiply(BigDecimal thisValue, BigDecimal thatValue) {
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
