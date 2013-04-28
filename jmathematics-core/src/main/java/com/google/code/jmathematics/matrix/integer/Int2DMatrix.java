package com.google.code.jmathematics.matrix.integer;

import com.google.code.jmathematics.matrix.Matrix;
import com.google.code.jmathematics.matrix.TwoDimensionalMatrix;
import com.google.code.jmathematics.matrix.determinant.IntDeterminantVisitor;

public interface Int2DMatrix extends TwoDimensionalMatrix, Matrix<Int2DMatrix> {
    
    <T extends Int2DMatrix> T set(int x, int y, int value);
    
    int get(int x, int y);
    
    <T extends Int2DMatrix> int dot(T other);
    
    public <T extends Int2DMatrix> T scalar(int other);
    
    public int determinant(IntDeterminantVisitor visitor);
    
}

