package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.integer.Int2DMatrix;

public interface IntDeterminantVisitor {

    public int calculateDeterminant(Int2DMatrix matrix);
    
}
