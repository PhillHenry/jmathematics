package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.doubles.DoubleMatrix;

public interface DoubleDeterminantVisitor {
    
    public double calculateDeterminant(DoubleMatrix matrix);
    
}
