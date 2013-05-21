package com.google.code.jmathematics.stats;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class SimpleDoubleStandardDeviationTest {
    
    private SimpleDoubleStandardDeviation toTest = new SimpleDoubleStandardDeviation();

    @Test
    public void test() {
        double stdDev = toTest.calculate(new double[] {2,4,4,4,5,5,7,9});
        Assert.assertEquals(2, stdDev, 0);
    }

}
