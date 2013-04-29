package com.google.code.jmathematics.matrix.number;

import com.google.code.jmathematics.matrix.Matrix;
import com.google.code.jmathematics.matrix.TwoDimensionalMatrix;
import com.google.code.jmathematics.matrix.determinant.NumberDeterminantVisitor;

public interface Number2DMatrix extends TwoDimensionalMatrix, Matrix<Number2DMatrix> {
    
    <T extends Number2DMatrix> T set(int x, int y, Number value);
    
    Number get(int x, int y);
    
    <T extends Number2DMatrix> Number dot(T other);
    
    public <T extends Number2DMatrix> T scalar(Number other);
    
    public Number determinant(NumberDeterminantVisitor visitor);
    
    public abstract Number multiplyAndAdd(Number accumulator, Number thisValue, Number thatValue);
    
    public abstract Number multiply(Number thisValue, Number thatValue);
    
    public abstract Number add(Number thisValue, Number thatValue);
    
}

