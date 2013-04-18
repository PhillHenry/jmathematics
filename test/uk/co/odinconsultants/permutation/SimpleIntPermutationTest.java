package uk.co.odinconsultants.permutation;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SimpleIntPermutationTest {

    private SimpleIntPermutation toTest;
    
    @Before
    public void setUp() {
        toTest = new SimpleIntPermutation();
    }
    
    @Test
    public void shouldProducePermutationsFrom3Elements() {
        int numElements = 3;
        int[][] permutations = toTest.permutate(numElements);
        checkCardinalityNonEmptyAndUniqeElements(permutations, 6, numElements);
        checkAllElementsInclusivelyBetween(0, numElements - 1, permutations);
        checkUnique(permutations);
    }
    
    @Test
    public void shouldProducePermutationsFrom4Elements() {
        int numElements = 4;
        int[][] permutations = toTest.permutate(numElements);
        checkCardinalityNonEmptyAndUniqeElements(permutations, 24, numElements);
        checkAllElementsInclusivelyBetween(0, numElements - 1, permutations);
        checkUnique(permutations);
    }

    private void checkUnique(int[][] permutations) {
        for (int i = 0 ; i < permutations.length ; i++) {
            for (int j = i + 1 ; j < permutations.length ; j++) {
                assertTrue("Elements " + i + " and " + j + ": " + message(permutations[i], permutations[j]) + " in " + message(permutations), 
                        !deepEquals(permutations[i], permutations[j]));
            }
        }
    }
    
    private String message(int[][] arrays) {
        StringBuffer szb = new StringBuffer("\n");
        for (int i = 0 ; i < arrays.length ; i++) {
            szb.append(SimpleIntPermutation.toString(arrays[i])).append("\n");
        }
        return szb.toString();
    }
    
    private String message(int[] a, int[] b) {
        return SimpleIntPermutation.toString(a) + " versus " + SimpleIntPermutation.toString(b);
    }
    


    private boolean deepEquals(int[] a, int[] b) {
        for (int i = 0 ; i < a.length ; i++) {
            if (a[i] != b[i]) return false; 
        }
        return true;
    }

    private void checkAllElementsInclusivelyBetween(int minInclusive, int maxInclusive,
            int[][] permutations) {
        for (int i = 0 ; i < permutations.length ; i++) {
            int[] series = permutations[i];
            for (int j = 0 ; j < series.length ; j++) {
                assertTrue(series[j] >= minInclusive);
                assertTrue(series[j] <= maxInclusive);
            }
        }
    }

    private void checkCardinalityNonEmptyAndUniqeElements(int[][] permutations, int numPermutations, int numElements) {
        assertEquals(numPermutations, permutations.length);
        for (int i = 0 ; i < permutations.length ; i++) {
            int[] series = permutations[i];
            assertEquals(numElements, series.length);
            assertUnique(series);
        }
    }

    private void assertUnique(int[] series) {
        Set<Integer> ints = new HashSet<Integer>();
        for (int i = 0 ; i < series.length ; i++) {
            ints.add(series[i]);
        }
        assertEquals(SimpleIntPermutation.toString(series), series.length, ints.size());
    }
}
