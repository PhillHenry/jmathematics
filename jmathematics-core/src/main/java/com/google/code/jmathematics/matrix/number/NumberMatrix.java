package com.google.code.jmathematics.matrix.number;

import com.google.code.jmathematics.matrix.Matrix;
import com.google.code.jmathematics.matrix.SizedMatrix;
import com.google.code.jmathematics.matrix.determinant.NumberDeterminantVisitor;

public interface NumberMatrix extends SizedMatrix, Matrix<NumberMatrix> {
    
    <T extends NumberMatrix> T set(int x, int y, Number value);
    
    Number get(int x, int y);
    
    <T extends NumberMatrix> Number dot(T other);
    
    public <T extends NumberMatrix> T scalar(Number other);
    
    public Number determinant(NumberDeterminantVisitor visitor);
    
    public abstract Number multiplyAndAdd(Number accumulator, Number thisValue, Number thatValue);
    
    public abstract Number multiply(Number thisValue, Number thatValue);
    
    public abstract Number add(Number thisValue, Number thatValue);
    
}

