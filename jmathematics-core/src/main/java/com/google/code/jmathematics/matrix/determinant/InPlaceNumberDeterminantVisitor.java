package com.google.code.jmathematics.matrix.determinant;

import com.google.code.jmathematics.matrix.TwoDimensionalMatrix;
import com.google.code.jmathematics.matrix.number.Number2DMatrix;
import com.google.code.jmathematics.permutation.IntPermutations;
import com.google.code.jmathematics.permutation.SimpleIntPermutation;
import com.google.code.jmathematics.permutation.leviCivita.LeviCivita;
import com.google.code.jmathematics.permutation.leviCivita.SimpleLeviCivita;

public class InPlaceNumberDeterminantVisitor implements
        NumberDeterminantVisitor {

    private static final IllegalStateException NOT_SQUARE_X = new IllegalStateException("Not a square matrix");
    
    private final LeviCivita      leviCivita      = new SimpleLeviCivita();
    private final IntPermutations intPermutation  = new SimpleIntPermutation();

    @Override
    public Number calculateDeterminant(Number2DMatrix matrix) {
        return determinant(matrix);
    }

    private void checkSquare(TwoDimensionalMatrix matrix) {
        if (matrix.getHeight() != matrix.getWidth()) throw NOT_SQUARE_X;
    }

    private Number determinant(Number2DMatrix matrix) {
        checkSquare(matrix);
        int         n               = matrix.getHeight();
        Number      accumulator     = 0;
        int[][]     permutations    = intPermutation.permutate(n);
        for (int j = 0 ; j < permutations.length ; j++) {
            Number term = term(permutations[j], matrix);
            accumulator = matrix.add(term, accumulator);
        }
        return accumulator;
    }
    
    private Number term(int[] indexes, Number2DMatrix matrix) {
        int     n       = indexes.length;
        Number    term  = 1;
        int     sign    = leviCivita.apply(indexes);
        if (sign == 0) return 0;
        
        for (int j = 0 ; j < n ; j++) {
            int k = indexes[j];
            term = matrix.multiply(term, matrix.get(j, k));
        }
        term = matrix.multiply(term, sign);
        return term;
    }

}
