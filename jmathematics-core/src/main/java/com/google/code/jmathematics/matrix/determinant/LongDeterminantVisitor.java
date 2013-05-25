package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.longs.LongMatrix;

public interface LongDeterminantVisitor {

    public long calculateDeterminant(LongMatrix matrix);
    
}
