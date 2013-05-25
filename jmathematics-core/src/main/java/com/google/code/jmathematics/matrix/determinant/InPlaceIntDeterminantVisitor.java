package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.SizedMatrix;
import com.google.code.jmathematics.matrix.integer.IntMatrix;
import com.google.code.jmathematics.matrix.longs.LongMatrix;
import com.google.code.jmathematics.permutation.IntPermutations;
import com.google.code.jmathematics.permutation.SimpleIntPermutation;
import com.google.code.jmathematics.permutation.leviCivita.LeviCivita;
import com.google.code.jmathematics.permutation.leviCivita.SimpleLeviCivita;

public class InPlaceIntDeterminantVisitor implements IntDeterminantVisitor {

    private static final IllegalStateException NOT_SQUARE_X = new IllegalStateException("Not a square matrix");
    
    private final LeviCivita      leviCivita      = new SimpleLeviCivita();
    private final IntPermutations intPermutation  = new SimpleIntPermutation();

    @Override
    public int calculateDeterminant(IntMatrix matrix) {
        return determinant(matrix);
    }

    private void checkSquare(SizedMatrix matrix) {
        if (matrix.getHeight() != matrix.getWidth()) throw NOT_SQUARE_X;
    }

    private int determinant(IntMatrix matrix) {
        checkSquare(matrix);
        int         n               = matrix.getHeight();
        int         accumulator     = 0;
        int[][]     permutations    = intPermutation.permutate(n);
        for (int j = 0 ; j < permutations.length ; j++) {
            int term = term(permutations[j], matrix);
            accumulator += term;
        }
        return accumulator;
    }
    
    private int term(int[] indexes, IntMatrix matrix) {
        int     n       = indexes.length;
        int     term    = 1;
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
