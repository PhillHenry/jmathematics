package com.google.code.jmathematics.matrix.longs;

import com.google.code.jmathematics.matrix.Matrix;
import com.google.code.jmathematics.matrix.TwoDimensionalMatrix;

public interface Long2DMatrix extends TwoDimensionalMatrix, Matrix<Long2DMatrix> {
    
    <T extends Long2DMatrix> T set(int x, int y, long value);
    
    long get(int x, int y);
    
    <T extends Long2DMatrix> long dot(T other);
    
    public <T extends Long2DMatrix> T scalar(long other);
    
}