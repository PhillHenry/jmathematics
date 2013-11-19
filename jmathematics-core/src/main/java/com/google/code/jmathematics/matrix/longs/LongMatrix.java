package com.google.code.jmathematics.matrix.longs;

import com.google.code.jmathematics.matrix.Matrix;
import com.google.code.jmathematics.matrix.SizedMatrix;
import com.google.code.jmathematics.matrix.determinant.DoubleDeterminantVisitor;
import com.google.code.jmathematics.matrix.determinant.LongDeterminantVisitor;

public interface LongMatrix extends Matrix<LongMatrix, Long> {
    
    <T extends LongMatrix> T set(int x, int y, long value);
    
    long get(int x, int y);
    
    <T extends LongMatrix> long dot(T other);
    
    public <T extends LongMatrix> T scalar(long other);
    
    public long determinant(LongDeterminantVisitor visitor);
    
}
