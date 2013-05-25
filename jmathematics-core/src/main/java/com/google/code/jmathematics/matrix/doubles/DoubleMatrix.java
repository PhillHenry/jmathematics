package com.google.code.jmathematics.matrix.doubles;

import com.google.code.jmathematics.matrix.Matrix;
import com.google.code.jmathematics.matrix.SizedMatrix;
import com.google.code.jmathematics.matrix.determinant.DoubleDeterminantVisitor;

public interface DoubleMatrix extends SizedMatrix, Matrix<DoubleMatrix> {

    <T extends DoubleMatrix> T set(int x, int y, double value);
    
    double get(int x, int y);
    
    <T extends DoubleMatrix> double dot(T other);
    
    public <T extends DoubleMatrix> T scalar(double other);
    
    public double determinant(DoubleDeterminantVisitor visitor);
    
}
