import java.util.Arrays;

/**
 * Created by Colin Benist on 12/05/2015.
 * Purpose -- This is a Matrix Module for my Software Development 2 Class
 *
 *   This Class can be used to create Matrices and perform matrix operations such as:
 *      addition, multiplication, scaling, Row Reductions, etc.[WIP]
 *   This will be bundled as a jar library
 */
public class Matrix {

    private int numRows;
    private int numColumns;
    private double[][] columnRows;

    /* constructor for matrix dimensions -- mostly for in class operations
     * @param x -- is the number of rows
     * @param y -- is the number of columns
     */
    public Matrix(int x, int y){
        columnRows = new double[x][y];
        numRows = x;
        numColumns= y;

    }

    /*constructor using arrays
     * @param x -- this is an array of arrays filled with doubles
     */
    public Matrix(double[][] x){
        columnRows = x;
        numRows = x.length;
        numColumns = x[0].length;
    }


    /* Matrix.size(x) method that returns the size of the matrix in a 2 integer long array
     * @params x -- this is the matrix that you are checking the size of
     */
    public static int[] size(Matrix x) {
        int[] size = {x.getNumRows(), x.getNumColumns()};
        return size;
    }

    /* matrixAdd(x,y) method that adds 2 matrices if they are the same size
     * @params x -- the first matrix to be added
     * @params y -- the second matrix to be added
     *  *****Work In Progress Still needs to throw an exception if the sizes are different
     */
    public static Matrix matrixAdd(Matrix x, Matrix y){
        Matrix result;
        double[][] xColumnRows = x.getColumnRows();
        double[][] yColumnRows = y.getColumnRows();
        double[][] resultColumnRows = new double[x.getNumRows()][x.getNumColumns()];

        if(size(x)[0] != size(y)[0] || size(x)[1] != size(y)[1])
        {
            //throws an Exception because of unequal sizes of the matrices
            throw new IllegalArgumentException("The Matrices are not the same size");
            //System.err.println("Matrices need to be the same size (dimensions)");
        }
        else
        {


            for(int i = 0; i < x.getNumRows(); i++)
            {
                for(int j = 0; j<x.getNumColumns(); j++)
                {
                    resultColumnRows[i][j] = xColumnRows[i][j] + yColumnRows[i][j];

                }
            }

        }

        result = new Matrix(resultColumnRows);
        return result;
    }

    /* scale(x,y) a method to scale every number in a matrix
     * @params x -- this is the matrix that you want to scale
     * @params y -- this is the double that you want to scale the matrix by
     */
    public static Matrix scale(Matrix x, double y){
        Matrix result;
        double[][] xColumnRows = x.getColumnRows();
        double[][] resultColumnRows = new double[x.getNumRows()][x.getNumColumns()];

        for(int i = 0; i < x.getNumRows(); i++) {
            for (int j = 0; j < x.getNumColumns(); j++) {
                resultColumnRows[i][j] = xColumnRows[i][j] * y;
            }
        }

        result = new Matrix(resultColumnRows);
        return result;
    }


    /* splitCurrentRow(x,y) -- this method is for returning the current row this is meant to be used exclusively in this class
     * @params x -- this is the matrix that you want the row from
     * @params y -- this is the number of the row that you want returned
     */

    public static double[] splitCurrentRow(Matrix x, int y){
        double[] result;
        double[][] xColumnRows = x.getColumnRows();

        result = xColumnRows[y];

        return result;
    }

    /* matrixMultiply() -- this method is used to multiply 2 matrices together
     * @params x -- The Left Matrix to multiply
     * @params y -- the Right Matrix to multiply
     */
    public static Matrix matrixMultiply(Matrix x, Matrix y){
        Matrix result;
        double[][] xColumnRows = x.getColumnRows();
        double[][] yColumnRows = y.getColumnRows();
        double[][] resultColumnRows = new double[x.getNumRows()][y.getNumColumns()];

        for(int i = 0; i < resultColumnRows.length; i++)
        {
            Arrays.fill(resultColumnRows[i], 0);
        }

        if(x.getNumColumns() != y.getNumRows())
        {
            throw new IllegalArgumentException("The number of columns of the left matrix must be equal " +
                    "to the number of rows of the right matrix!");
            //System.err.println("The number of columns of the left matrix must be equal to the number of rows of the right matrix");
        }
        else
        {
            for(int i = 0; i < x.getNumRows(); i++)
            {
                for(int j = 0; j < y.getNumColumns(); j++)
                {
                    for(int k = 0; k < x.getNumColumns(); k++)
                    {
                        resultColumnRows[i][j] += xColumnRows[i][k] * yColumnRows[k][j];
                    }
                }
            }
        }
        result = new Matrix(resultColumnRows);
        return result;
    }

    /* augment(x,y) -- This method is used to add more columns to a matrix
     * @params x -- this is the matrix that you want to augment
     * @params y -- this is the matrix you want to augment matrix x with
     */
    public static Matrix augment(Matrix x, Matrix y){
        Matrix result;
        double[][] resultColumnRows = new double[x.numRows][x.numColumns + y.numColumns];
        double[][] xColumnRows = x.getColumnRows();
        double[][] yColumnRows = y.getColumnRows();
        if(x.numRows != y.numRows)
        {
            throw new IllegalArgumentException("The matrices must have the same number of rows if you want to augment them");
        }
        else
        {
            for(int i = 0; i < resultColumnRows.length; i++)
            {
                for(int j = 0; j < resultColumnRows[0].length; j++)
                {
                    if(j < x.numColumns )
                    {
                        resultColumnRows[i][j] = xColumnRows[i][j];
                    }
                    else
                    {
                        resultColumnRows[i][j] = yColumnRows[i][j-x.numColumns];
                    }
                }
            }
        }

        result = new Matrix(resultColumnRows);
        return result;
    }

    /* rowMultiplication(x,y) -- this method is a util for reduceToEchelon and reduceToReducedEchelon
     * @params x -- this is the row that is passed in
     * @params y -- this is the multiple that is passed in
     */
    private static double[] rowMultiplication(double[] x, double y){
        double[] result = new double[x.length];
        for(int i = 0; i < x.length; i++)
        {
            result[i] = x[i] * y;
        }

        return result;
    }

    /* rowSubtraction(x,y) -- this method is a util method for reduceToEchelon and reduceToReducedEchelon
     * @params x -- this is the row being subtracted from
     * @params y -- this is the row doing the subtracting
     */
    private static double[] rowSubtraction(double[] x, double[] y){
        double[] result = new double[x.length];
        for(int i = 0; i < x.length; i++)
        {
            result[i] = x[i] - y[i];
        }

        return result;
    }

    /* reduceToEchelon(x) -- this method is used to reduce a matrix to echelon form
     * ***NOTE*** echelon form is different than reduced echelon form make sure you use the right one
     * @params x -- this is the matrix that you want to reduce to echelon form
     */
    public static Matrix reduceToEchelon(Matrix x){
        double[][] xColumnRows = x.getColumnRows();
        double[][] resultColumnRows = new double[x.numRows][x.numColumns];
        double[] rowA;
        double[] rowB;

        for(int i = 0; i<x.numRows;i++){
            for( int j = 0; j<x.numColumns;j++){
                resultColumnRows[i][j] = xColumnRows[i][j];
            }
        }

        for(int i = 0; i < x.numRows; i++)
        {
            if(resultColumnRows[i][i] == 0)
            {
                rowA = resultColumnRows[i];
                for(int k = i+1; k<x.numRows; k++)
                {
                    if(resultColumnRows[k][i] != 0)
                    {
                        rowB = resultColumnRows[k];
                        resultColumnRows[k] = rowA;
                        resultColumnRows[i] = rowB;
                        break;
                    }
                }
            }
            else
            {
                for(int k = i+1; k<x.numRows; k++ )
                {
                    rowA = Matrix.rowMultiplication(resultColumnRows[i],resultColumnRows[k][i]/resultColumnRows[i][i]);
                    resultColumnRows[k] = Matrix.rowSubtraction(resultColumnRows[k],rowA);
                }
            }
        }

        Matrix result = new Matrix(resultColumnRows);
        return result;


    }


    /* reduceToReducedEchelonForm(x) -- this method is used to reduce a matrix to reduced echelon form
     * ***NOTE*** echelon form is different than reduced echelon form make sure you are using the right one
     * @params x -- this is the matrix that you want reduced
     */
    public static Matrix reduceToReducedEchelonForm(Matrix x){
        Matrix result;
        Matrix temp = Matrix.reduceToEchelon(x);
        double[][] tempColumnRows = temp.getColumnRows();
        int rowNumA = temp.numRows;
        double[] rowA;
        for(int i = 0; i <temp.numRows; i++)
        {
            if(tempColumnRows[i][i] != 0){
                rowA = Matrix.rowMultiplication(tempColumnRows[i],1.0/tempColumnRows[i][i]);
                tempColumnRows[i] = rowA;
            }
        }


        for(int i = rowNumA - 1; i >= 0; i--) {
            for (int j = 0; j < temp.numColumns; j++) {
                if (tempColumnRows[i][j] != 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (tempColumnRows[k][j] != 0) {
                            rowA = Matrix.rowMultiplication(tempColumnRows[i], tempColumnRows[k][j] / tempColumnRows[i][j]);
                            tempColumnRows[k] = Matrix.rowSubtraction(tempColumnRows[k], rowA);
                        }
                    }
                    break;
                }
            }
        }

        result = new Matrix(tempColumnRows);
        return result;
    }

    /* transpose(x) -- this method is used to transpose a matrix.
     * @params x -- This is the matrix you want transposed
     */
    public static Matrix transpose(Matrix x){
        Matrix result;
        double[][] xColumnRows = x.getColumnRows();
        double[][] resultColumnRows = new double[x.numColumns][x.numRows];

        for(int i = 0; i < x.numColumns; i++){

            for(int j = 0; j<x.numRows; j++){
                resultColumnRows[i][j] = xColumnRows[j][i];
            }

        }

            result = new Matrix(resultColumnRows);
        return result;
    }

    /* determinant(x) -- this method is used to find the determinant of a matrix (matrix must be square)
     * @params x -- this is the matrix of the determinant you want to find.
     */
    public static double determinant(Matrix x){
        double determinant = 0.0;
        if(x.numRows != x.numColumns){
            throw new IllegalArgumentException("the Matrix must be square");
        }

        double[][] xColumnRows = x.getColumnRows();
        double[][] tempColumnRows = new double[x.numRows - 1][x.numRows - 1];


        if(x.numRows == 2)
        {
            determinant = xColumnRows[0][0] * xColumnRows[1][1] - xColumnRows[0][1] * xColumnRows[1][0];
        }
        else{
            for(int i = x.numRows - 1; i >= 0; i--){
                for(int j = 0; j < x.numRows - 1; j++)
                {
                    int l = 0;
                        for(int k = 0; k < x.numRows; k++){
                         if(k != i){
                             tempColumnRows[j][l] = xColumnRows[j][k];
                             l++;
                         }
                        }
                }
                if((x.numRows + i + 1)%2 == 0){
                    determinant += xColumnRows[x.numRows-1][i] * determinant(new Matrix(tempColumnRows));
                }
                else{
                    determinant -= xColumnRows[x.numRows-1][i] * determinant(new Matrix(tempColumnRows));
                }
            }
        }



        return determinant;
    }

    /* inverse(x) -- this method is used to find the inverse of a matrix
     * @params x --this is the matrix you want inverted ***THE MATRIX MUST BE SQUARE AND THE DETERMINANT CANNOT BE ZERO***
     */
    public static Matrix inverse(Matrix x){
        if(x.numRows != x.numColumns){
            throw new IllegalArgumentException("The Matrix must be square");
        }
        if(Matrix.determinant(x) == 0){
            throw new IllegalArgumentException("The matrix's determinant is zero, not invertible.");
        }
        Matrix result;
        double[][] resultColumnRows = new double[x.numRows][x.numRows];
        double[][] idColumnRows = new double[x.numRows][x.numRows];

        for(int i = 0; i<x.numRows; i++)
        {
            for(int j = 0; j<x.numRows; j++){
                if(i == j){
                    idColumnRows[i][j] = 1.0;
                }
                else
                {
                    idColumnRows[i][j] = 0.0;
                }
            }
        }
        Matrix idMatrix = new Matrix(idColumnRows);
        Matrix invertibleMatrix = Matrix.augment(x,idMatrix);
        Matrix invertedMatrix = Matrix.reduceToReducedEchelonForm(invertibleMatrix);
        double[][] invertedColumnRows = invertedMatrix.getColumnRows();


        for(int i = 0; i < invertedMatrix.numRows; i++){
            int k = 0;
            for(int j = x.numColumns; j < invertedMatrix.numColumns; j++){
                resultColumnRows[i][k] = invertedColumnRows[i][j];
                k++;
            }
        }


        result = new Matrix(resultColumnRows);
        return result;
    }






    //Getters and Setters Start Here
    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public double[][] getColumnRows() {
        return columnRows;
    }

    public void setColumnRows(double[][] columnRows) {
        this.columnRows = columnRows;
    }

    @Override
    public String toString() {
        String arrayNums = "";
        for(int i = 0; i < numRows; i++)
        {
            if(i < numRows - 1){
            arrayNums += Arrays.toString(columnRows[i]) + ", ";
            }
            else {
                arrayNums += Arrays.toString(columnRows[i]);
            }
        }

        return "Matrix{" +
                "numRows=" + numRows +
                ", numColumns=" + numColumns +
                ", columnRows=" + arrayNums +
                '}';
    }
}
