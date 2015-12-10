import com.sun.xml.internal.bind.v2.TODO;

import java.util.Arrays;

/**
 * Created by Colin Benist on 12/05/2015.
 * Purpose -- This is a Matrix Module for my Software Development 2 Class
 *
 *   This Class can be used to create Matrices and perform matrix operations such as:
 *      addition, multiplication, scaling, Row Reductions, etc.[WIP]
 */
public class Matrix {

    private int numRows;
    private int numColumns;
    private double[][] columnRows;

    /* constructor for matrix dimensions
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
    public static int[] size(Matrix x)
    {
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

        if(size(x)!= size(y))
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
    public static Matrix scale(Matrix x, double y)
    {
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
    //TODO
    public static double[] splitCurrentRow(Matrix x, int y)
    {
        double[] result = new double[x.getNumColumns()];
        double[][] xColumnRows = x.getColumnRows();

        result = xColumnRows[y];

        return result;
    }
    /* matrixMultiply() -- this method is used to multiply 2 matrices together
     * @params x -- The Left Matrix to multiply
     * @params y -- the Right Matrix to multiply
     */
    public static Matrix matrixMultiply(Matrix x, Matrix y)
    {
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
}
