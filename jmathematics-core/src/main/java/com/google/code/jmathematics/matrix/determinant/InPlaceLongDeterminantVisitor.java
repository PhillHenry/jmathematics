package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.SizedMatrix;
import com.google.code.jmathematics.matrix.doubles.DoubleMatrix;
import com.google.code.jmathematics.matrix.longs.LongMatrix;
import com.google.code.jmathematics.permutation.IntPermutations;
import com.google.code.jmathematics.permutation.SimpleIntPermutation;
import com.google.code.jmathematics.permutation.leviCivita.LeviCivita;
import com.google.code.jmathematics.permutation.leviCivita.SimpleLeviCivita;

public class InPlaceLongDeterminantVisitor implements LongDeterminantVisitor {
    
    private static final IllegalStateException NOT_SQUARE_X = new IllegalStateException("Not a square matrix");
    
    private final LeviCivita      leviCivita      = new SimpleLeviCivita();
    private final IntPermutations intPermutation  = new SimpleIntPermutation();

    @Override
    public long calculateDeterminant(LongMatrix matrix) {
        return determinant(matrix);
    }

    private void checkSquare(SizedMatrix matrix) {
        if (matrix.getHeight() != matrix.getWidth()) throw NOT_SQUARE_X;
    }

    private long determinant(LongMatrix matrix) {
        checkSquare(matrix);
        int         n               = matrix.getHeight();
        long        accumulator     = 0;
        int[][]     permutations    = intPermutation.permutate(n);
        for (int j = 0 ; j < permutations.length ; j++) {
            long term = term(permutations[j], matrix);
            accumulator += term;
        }
        return accumulator;
    }
    
    private long term(int[] indexes, LongMatrix matrix) {
        int     n       = indexes.length;
        long    term    = 1;
        int     sign    = leviCivita.apply(indexes);
        if (sign == 0) return 0;
        
        for (int j = 0 ; j < n ; j++) {
            int k = indexes[j];
            term *= matrix.get(j, k);
        }
        term *= sign;
        return term;
    }
}
