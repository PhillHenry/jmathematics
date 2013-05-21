package com.google.code.jmathematics.stats;

/**
 * NB: can overflow
 */
public class SimpleDoubleStandardDeviation implements DoubleStandardDeviation {

    @Override
    public double calculate(double[] variables) {
        double average = average(variables);
        double total = 0;
        int length = variables.length;
        for (int i = 0 ; i < length ; i++) {
            double diff = variables[i] - average;
            total += diff * diff;
        }
        return Math.sqrt(total / length);
    }
    
    private double average(double[] vals) {
        return total(vals) / vals.length;
    }
    
    private double total(double[] vals) {
        double total = 0;
        for (int i = 0 ; i < vals.length ; i++) {
            total += vals[i];
        }
        return total;
    }

}
