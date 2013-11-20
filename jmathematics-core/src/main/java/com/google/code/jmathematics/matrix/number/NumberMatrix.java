package com.google.code.jmathematics.matrix.number;

import com.google.code.jmathematics.matrix.Matrix;
import com.google.code.jmathematics.matrix.determinant.NumberDeterminantVisitor;

public interface NumberMatrix<U extends Number> extends Matrix<NumberMatrix<U>, U> {
    
    U get(int x, int y);
    
    <T extends NumberMatrix<U>> U dot(T other);
    
    public U determinant(NumberDeterminantVisitor<U> visitor);
    
    public abstract U multiplyAndAdd(U accumulator, U thisValue, U thatValue);
    
    public abstract U multiply(U thisValue, U thatValue);
    
    public abstract U add(U thisValue, U thatValue);
    
}

