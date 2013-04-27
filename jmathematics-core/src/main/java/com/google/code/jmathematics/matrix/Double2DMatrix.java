package com.google.code.jmathematics.matrix;

import com.google.code.jmathematics.matrix.determinant.DoubleDeterminantVisitor;

public interface Double2DMatrix extends TwoDimensionalMatrix, Matrix<Double2DMatrix> {

    <T extends Double2DMatrix> T set(int x, int y, double value);
    
    double get(int x, int y);
    
    <T extends Double2DMatrix> double dot(T other);
    
    public <T extends Double2DMatrix> T scalar(double other);
    
    public double determinant(DoubleDeterminantVisitor visitor);
    
}
