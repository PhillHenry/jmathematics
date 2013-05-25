package com.google.code.jmathematics.matrix.integer;

import com.google.code.jmathematics.matrix.Matrix;
import com.google.code.jmathematics.matrix.SizedMatrix;
import com.google.code.jmathematics.matrix.determinant.IntDeterminantVisitor;

public interface IntMatrix extends SizedMatrix, Matrix<IntMatrix> {
    
    <T extends IntMatrix> T set(int x, int y, int value);
    
    int get(int x, int y);
    
    <T extends IntMatrix> int dot(T other);
    
    public <T extends IntMatrix> T scalar(int other);
    
    public int determinant(IntDeterminantVisitor visitor);
    
}

