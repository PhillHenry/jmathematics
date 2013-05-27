package com.google.code.jmathematics.matrix;


public interface Matrix<T extends Matrix> extends SizedMatrix {

    public T transpose();
    
    public T cross(T other);

    public T add(T other);
    
    /**
     * A more generic interface but slower than using primitives methods.
     */
    public Number dotProduct(T other);
    
    /**
     * A more generic interface but slower than using primitives methods.
     */
    public T set(int x, int y, Number value);
    
    /**
     * A more generic interface but slower than using primitives methods.
     */
    public T add(Number value);
    
    /**
     * A more generic interface but slower than using primitives methods.
     */
    public T scalar(Number value);
    
}
