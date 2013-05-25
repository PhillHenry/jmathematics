package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.number.NumberMatrix;

public interface NumberDeterminantVisitor {

    public Number calculateDeterminant(NumberMatrix matrix);
    
}
