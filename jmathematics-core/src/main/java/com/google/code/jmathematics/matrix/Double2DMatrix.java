package com.google.code.jmathematics.matrix;

public interface Double2DMatrix extends TwoDimensionalMatrix {

    <T extends Double2DMatrix> T set(int x, int y, double value);
    
    double get(int x, int y);
    
    <T extends Double2DMatrix> double dot(T other);

    public <T extends Double2DMatrix> T transpose();
    
    public <T extends Double2DMatrix> T cross(T other);
    
    public <T extends Double2DMatrix> T scalar(double other);
    
}
