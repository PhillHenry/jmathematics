package com.google.code.jmathematics.matrix;

public class NoisyMatrixChecker implements MatrixChecker {
    
    private final SizedMatrix me;

    public NoisyMatrixChecker(SizedMatrix me) {
        super();
        this.me = me;
    }

    @Override
    public void checkDimensions(SizedMatrix other) {
        if (other.getHeight() != me.getWidth()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Width not equal. This width is <%d>. Other's height <%d>.",
                            me.getWidth(), other.getHeight()));
        }
        if (other.getWidth() != me.getHeight()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Incompatible dimenstions. This height is <%d>. Other's width is <%d>.",
                            me.getHeight(), other.getWidth()));
        }
    }

}
