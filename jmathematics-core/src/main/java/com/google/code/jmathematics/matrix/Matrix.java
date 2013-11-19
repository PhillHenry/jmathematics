package com.google.code.jmathematics.matrix;


public interface Matrix<T extends Matrix<?, N>, N extends Number> extends SizedMatrix {

    public <U extends T> U transpose();
    
    public T cross(T other);

    public <U extends T> U add(T other);
    
    /**
     * A more generic interface but slower than using primitives methods.
     */
    public N dotProduct(T other);
    
    /**
     * A more generic interface but slower than using primitives methods.
     */
    public T set(int x, int y, N value);
    
    /**
     * A more generic interface but slower than using primitives methods.
     */
    public T add(N value);
    
    /**
     * A more generic interface but slower than using primitives methods.
     */
    public <U extends T> U scalar(N value);
    
}
