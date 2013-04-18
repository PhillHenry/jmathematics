package uk.co.odinconsultants.permutation;

public class SimpleIntPermutation implements IntPermutations {
    
    public static String toString(int[] array) {
        StringBuffer szb = new StringBuffer("[");
        for (int i = 0 ; i < array.length ; i++) {
            szb.append(array[i]);
            if (i != array.length - 1) szb.append(", ");
        }
        szb.append("]");
        return szb.toString();
    }

    @Override
    public int[][] permutate(int n) {
        int size = factoral(n);
        int[][] permutations = new int[size][n];
        
        int[] series = permutations[0];
        initialize(n, series);
        
        for (int i = 1 ; i < size ; i++) {
            series = series.clone();
            permutations[i] = series;
            permute(series);
        }

        return permutations;
    }

    private void initialize(int n, int[] series) {
        for (int i = 0 ; i < n ; i++) {
            series[i] = i;
        }
    }

    /**
     * @see http://www.freewebs.com/permute/soda_submit.html
     */
    void permute(int[] series) {
        int len     = series.length;
        int key     = len - 1;

        /* The key value is the first value from the end which
           is smaller than the value to its immediate right        */
        while( (key > 0) && (series[key] <= series[key-1]) ) { 
            key--; 
        }

        key--;

        /* If key < 0 the data is in reverse sorted order, 
           which is the last permutation.                          */
        if( key < 0 )
           return;

        /* series[key+1] is greater than series[key] because of how key 
           was found. If no other is greater, series[key+1] is used   */
        int newkey = len - 1;
        while( (newkey > key) && (series[newkey] <= series[key]) ) {
           newkey--;
        }

        swap(series, key, newkey);

        /* variables len and key are used to walk through the tail,
           exchanging pairs from both ends of the tail.  len and 
           key are reused to save memory                           */
        len--;
        key++;

        /* The tail must end in sorted order to produce the
           next permutation.                                       */
        while(len > key) {
           swap(series, len, key);
           key++;
           len--;
        }
    }
    
    void swap(int[] s, int a, int b)
    {
       int temp=s[a];
       s[a] = s[b];
       s[b] = temp;
    }
    
    private int factoral(int n) {
        int accumulator = 1;
        for (int i = 1 ; i <= n ; i++) {
            accumulator *= i;
        }
        return accumulator;
    }
}

