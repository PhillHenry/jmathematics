package com.google.code.jmathematics.matrix;

public class NoisyMatrix2DChecker implements Matrix2DChecker {
    
    private static final IllegalStateException NOT_SQUARE_X = new IllegalStateException("Not a square matrix");
    
    private final TwoDimensionalMatrix me;

    NoisyMatrix2DChecker(TwoDimensionalMatrix me) {
        super();
        this.me = me;
    }

    @Override
    public void checkDimensions(TwoDimensionalMatrix other) {
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

    @Override
    public void checkSquare() {
        if (me.getHeight() != me.getWidth()) throw NOT_SQUARE_X;
    }

}
