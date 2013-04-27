package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.longs.Long2DMatrix;

public interface LongDeterminantVisitor {

    public long calculateDeterminant(Long2DMatrix matrix);
    
}
