package uk.co.odinconsultants.matrix;

public class Mutable2DDoubleMatrix implements Double2DMatrix {

    private final double[][] matrix;
    private final int        width;
    private final int        height;

    public Mutable2DDoubleMatrix(int rows, int columns) {
        width = columns;
        height = rows;
        matrix = new double[width][height];
    }

    @Override
    public Double2DMatrix set(int x, int y, double value) {
        matrix[x][y] = value;
        return this;
    }

    @Override
    public Double2DMatrix cross(Double2DMatrix other) {
        checkDimensions(other);
        Double2DMatrix toReturn = new Mutable2DDoubleMatrix(height,
                other.getWidth());

        for (int y = 0; y < height; y++) {
            for (int z = 0; z < other.getWidth(); z++) {
                double total = 0;
                for (int x = 0; x < width; x++) {
                    double thisVal = this.get(x, y);
                    double thatVal = other.get(z, x);
                    total += thisVal * thatVal;
                }
                toReturn = toReturn.set(z, y, total);
            }
        }

        return toReturn;
    }

    @Override
    public double dot(Double2DMatrix other) {
        checkDimensions(other);

        double total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total += matrix[x][y] * other.get(y, x);
            }
        }
        return total;
    }

    @Override
    public Double2DMatrix transpose() {
        Double2DMatrix other = new Mutable2DDoubleMatrix(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                other.set(y, x, this.get(x, y));
            }
        }
        return other;
    }

    private void checkDimensions(Double2DMatrix other) {
        if (other.getHeight() != this.getWidth()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Width not equal. This width is <%d>. Other's height <%d>.",
                            this.width, other.getHeight()));
        }
        if (other.getWidth() != this.getHeight()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Incompatible dimenstions. This height is <%d>. Other's width is <%d>.",
                            this.height, other.getWidth()));
        }
    }

    @Override
    public double get(int x, int y) {
        return matrix[x][y];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public Mutable2DDoubleMatrix scalar(double other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, get(x, y) * other);
            }
        }
        return this;
    }

    @Override
    public boolean equals(Object something) { // does this board equal y?
        if (something == null)
            return false;
        if (!(something instanceof Double2DMatrix))
            return false;
        Double2DMatrix other = (Double2DMatrix) something;
        if (other.getHeight() != this.getHeight())
            return false;
        if (other.getWidth() != this.getWidth())
            return false;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (other.get(x, y) != this.get(x, y))
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer szb = new StringBuffer();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                szb.append("\t").append(this.get(x, y));
            }
            szb.append("\n");
        }
        return szb.toString();
    }

    public double determinant() {
        if (height != width) throw new IllegalStateException("Not a square matrix");
        
        Mutable2DDoubleMatrix matrix = this;
        return determinant(matrix, 0d);
    }

    private double determinant(Mutable2DDoubleMatrix outer, double accumulator) {
        int rowIndx = 0;
        //for (int rowIndx = 0 ; rowIndx < outer.getHeight() ; rowIndx++) {
            for (int colIndx = 0 ; colIndx < outer.getWidth() ; colIndx++) {
                Mutable2DDoubleMatrix inner = initializeInner(outer, rowIndx, colIndx);
                if (inner.getHeight() == 2) {
                    double myVal = outer.get(colIndx, rowIndx);
                    double innerDeterminant = (inner.get(0, 0) * inner.get(1, 1)) - (inner.get(0, 1) * inner.get(1, 0));
                    accumulator += myVal * innerDeterminant * (colIndx % 2 == 0 ? 1 : -1);
                } else {
                    return accumulator + determinant(inner, accumulator);
                }
            }
        //}
        return accumulator;
    }

    private Mutable2DDoubleMatrix initializeInner(Mutable2DDoubleMatrix outer, int rowIndx,
            int colIndx) {
        Mutable2DDoubleMatrix inner = smaller(outer);
        for (int x = 0 ; x < outer.getWidth() ; x++) {
            if (x == colIndx) continue;
            for (int y = 0 ; y < outer.getHeight() ; y++) {
                if (y == rowIndx) continue;
                inner.set(x > colIndx ? x - 1 : x, y > rowIndx ? y - 1 : y, outer.get(x, y));
            }
        }
        return inner;
    }

    private Mutable2DDoubleMatrix smaller(Mutable2DDoubleMatrix matrix) {
        return new Mutable2DDoubleMatrix(matrix.getHeight() - 1, matrix.getWidth() - 1);
    }
    
    
}
