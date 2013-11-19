package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.number.NumberMatrix;

public interface NumberDeterminantVisitor<N extends Number> {

    public N calculateDeterminant(NumberMatrix matrix);
    
}
