package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.number.Number2DMatrix;

public interface NumberDeterminantVisitor {

    public Number calculateDeterminant(Number2DMatrix matrix);
    
}
