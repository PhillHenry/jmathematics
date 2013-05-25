package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.integer.IntMatrix;

public interface IntDeterminantVisitor {

    public int calculateDeterminant(IntMatrix matrix);
    
}
