package com.google.code.jmathematics.matrix.number;

@SuppressWarnings("unchecked")
public class IntegerMutableMatrix extends MutableNumberMatrix<Integer> {

    public IntegerMutableMatrix(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public Integer add(Integer thisValue, Integer thatValue) {
        return ((Integer)thisValue) + ((Integer)thatValue);
    }

    @Override
    public Integer multiplyAndAdd(Integer accumulator, Integer thisValue,
        Integer thatValue) {
        return add(accumulator, multiply(thisValue, thatValue));
    }

    @Override
    public Integer multiply(Integer thisValue, Integer thatValue) {
        return (thisValue * thatValue);
    }

    @Override
    protected MutableNumberMatrix<Integer> create(int width, int height) {
        return new IntegerMutableMatrix(height, width);
    }

    @Override
    public Integer zero() {
        return 0;
    }

}
