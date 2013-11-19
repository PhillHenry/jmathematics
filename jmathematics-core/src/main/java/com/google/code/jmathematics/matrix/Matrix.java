package com.google.code.jmathematics.matrix;


public interface Matrix<T extends Matrix<?>> extends SizedMatrix {

    public <U extends T> U transpose();
    
    public T cross(T other);

    public <U extends T> U add(T other);
    
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
    public <U extends T> U scalar(Number value);
    
}
