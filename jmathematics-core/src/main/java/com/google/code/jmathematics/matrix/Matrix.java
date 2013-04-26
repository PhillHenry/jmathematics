package com.google.code.jmathematics.matrix;

public interface Matrix<T extends Matrix> {

    public T transpose();
    
    public T cross(T other);
    
}
