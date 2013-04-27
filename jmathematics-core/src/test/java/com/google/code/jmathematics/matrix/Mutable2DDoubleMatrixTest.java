package com.google.code.jmathematics.matrix;

import org.junit.Before;
import org.junit.Test;

import com.google.code.jmathematics.matrix.determinant.InPlaceDoubleDeterminantVisitor;
import com.google.code.jmathematics.matrix.doubles.Mutable2DDoubleMatrix;

import static org.junit.Assert.assertEquals;


public class Mutable2DDoubleMatrixTest {
    
    private Mutable2DDoubleMatrix _3dVector;
    private Mutable2DDoubleMatrix matrix2x3;
    private Mutable2DDoubleMatrix twiceMatrix2x3;
    private Mutable2DDoubleMatrix matrix3x2;
    private Mutable2DDoubleMatrix _3Square;
    private Mutable2DDoubleMatrix _3Identity;

    @Before
    public void setUp() {
        Double2DMatrixBuilder builder = new Double2DMatrixBuilder();
        
        _3dVector = new Mutable2DDoubleMatrix(3, 1);
        _3dVector.set(0, 0, 1).set(0, 1, 1).set(0, 2, 1);
        
        matrix2x3 = builder.initialize(new Mutable2DDoubleMatrix(2, 3), 1);
        matrix3x2 = builder.initialize(new Mutable2DDoubleMatrix(3, 2), 7);
        
        Mutable2DDoubleMatrix matrixToDouble = matrix2x3;
        double[][] matrix = twiceMatrix(matrixToDouble);
        twiceMatrix2x3 = create(matrix);
        
        _3Square = new Mutable2DDoubleMatrix(3, 3);
        builder.initialize(_3Square, 1);
        
        init3Identity();
    }

    private void init3Identity() {
        int width = 3, height = 3;
        _3Identity = new Mutable2DDoubleMatrix(width, height);
        for (int index = 0 ; index < width ; index++) {
            _3Identity.set(index, index, 1d);
        }
    }

    private double[][] twiceMatrix(Mutable2DDoubleMatrix matrixToDouble) {
        int width = matrixToDouble.getWidth();
        int height = matrixToDouble.getHeight();
        double[][] matrix = new double[height][width];
        for (int columnIndx = 0 ; columnIndx < width ; columnIndx++) {
            for (int rowIndx = 0 ; rowIndx < height ; rowIndx++) {
                matrix[rowIndx][columnIndx] = 2 * matrixToDouble.get(columnIndx, rowIndx);
            }
        }
        return matrix;
    }
    
    /**
     * @see http://www.analyzemath.com/Tutorial-System-Equations/determinants.html
     */
    @Test
    public void shouldDetermineDeterminant() {
        Mutable2DDoubleMatrix matrix = create(new double[][] { new double[] { 2d, -2d, 0d }, new double[] { -1, 5, 1 }, new double[] { 3d, 4d, 5d } });
        assertEquals(26, matrix.determinant(new InPlaceDoubleDeterminantVisitor()), 0);
    }
    
    @Test
    public void identityShouldChangeNothing() {
        assertEquals(_3Square, _3Square.cross(_3Identity));
        assertEquals(_3Square, _3Identity.cross(_3Square));
    }
    
    @Test
    public void shouldDoubleMatrix() {
        assertEquals(twiceMatrix2x3, matrix2x3.scalar(2));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void dotProductOfSelfShouldError() {
        _3dVector.dot(_3dVector);
    }
    
    @Test
    public void shouldBeAbleToDotOnTransposeOfSelf() {
        assertEquals(_3dVector.transpose().dot(_3dVector), 3d, 0d);
    }
    
    @Test
    public void shouldHaveCrossOfTransposedSelf() {
        assertEquals(create(new double[][] { new double[] {3} }), _3dVector.transpose().cross(_3dVector));
    }
    
    /**
     * @see http://www.mathsisfun.com/algebra/matrix-multiplying.html
     */
    @Test
    public void shouldMultiply3x2And2x3For2x2() {
        double[][] matrix = new double[][] { new double[] { 58d, 64d }, new double[] { 139d, 154d } };
        assertEquals(create(matrix), matrix2x3.cross(matrix3x2));
    }

    private Mutable2DDoubleMatrix create(double[][] matrix) {
        return new Double2DMatrixBuilder().initialize(new Mutable2DDoubleMatrix(matrix.length, matrix[0].length), matrix);
    }
}
