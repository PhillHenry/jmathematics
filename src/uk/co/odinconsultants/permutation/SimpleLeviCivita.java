package uk.co.odinconsultants.permutation;

import java.util.Arrays;

/**
 * Thread safe.
 * Not memory efficient.
 */
public class SimpleLeviCivita implements LeviCivita {

    /**
     * TODO overflow
     */
    @Override
    public int apply(int... permutation) { 
        if (isNotPermutation(permutation)) return 0;
        int accumulator = 1;
        for (int i = 0 ; i < permutation.length - 1 ; i++) {
            for (int j = i + 1 ; j < permutation.length ; j++) {
                accumulator *= (permutation[i] * permutation[j]);
            }
        }
        return accumulator < 0 ? -1 : 1;
    }

    private boolean isNotPermutation(int[] permutation) {
        if (permutation == null || permutation.length == 0) return true;
        int[] clone = permutation.clone();
        Arrays.sort(clone);
        int lastIndex = 0;
        for (int i = 1 ; i < clone.length ; i++) {
            if (clone[i] == clone[lastIndex]) return true;
            lastIndex = i;
        }
        
        return false;
    }

}
