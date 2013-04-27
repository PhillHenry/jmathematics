package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.Double2DMatrix;

public interface DoubleDeterminantVisitor {
    
    public double calculateDeterminant(Double2DMatrix matrix);
    
}
