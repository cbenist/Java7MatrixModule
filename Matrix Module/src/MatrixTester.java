import java.util.Arrays;

/**
 * Created by Colin on 12/10/2015.
 */
/*
public class MatrixTester {

    public static void main(String args[])
    {

        Matrix matrixA = new Matrix(new double[][]{{1.0,0.0},{0.0,1.0}});

        System.out.println("Matrix A: " + matrixA);

        Matrix matrixB = Matrix.scale(matrixA, 5.0);

        System.out.println("Matrix B: " + matrixB);

        Matrix matrixC = new Matrix(new double[][]{{1.0,2.0},{3.0,4.0}});

        System.out.println("Matrix C: " + matrixC);

        Matrix matrixD = Matrix.matrixAdd(matrixC,matrixB);

        System.out.println("Matrix D: " + matrixD);

        Matrix matrixE = Matrix.matrixMultiply(matrixA,matrixD);

        System.out.println("Matrix E: " + matrixE);

        Matrix matrixF = Matrix.matrixMultiply(matrixC,matrixD);

        System.out.println("Matrix F: " + matrixF);

        double[] row0A = Matrix.splitCurrentRow(matrixA,0);
        double[] row1A = Matrix.splitCurrentRow(matrixA,1);

        System.out.println(Arrays.toString(row0A));
        System.out.println(Arrays.toString(row1A));

        Matrix matrixG = Matrix.augment(matrixA,matrixA);

        System.out.println("Matrix G: " + matrixG);

        Matrix matrixH = new Matrix(new double[][]{{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});

        System.out.println("Matrix H: " + matrixH);

        Matrix matrixI = Matrix.reduceToEchelon(matrixH);

        System.out.println("Matrix I: " + matrixI);

        Matrix matrixJ = Matrix.reduceToReducedEchelonForm(matrixH);

        System.out.println("Matrix J: " + matrixJ);

        //matrixH = new Matrix(new double[][]{{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});

        System.out.println("Matrix H2.0: " + matrixH);

        Matrix matrixK = Matrix.transpose(matrixH);

        System.out.println("Matrix K: " + matrixK);

        double det0 = Matrix.determinant(matrixH);

        System.out.println("det H: " + det0);

        double det1 = Matrix.determinant(matrixA);

        System.out.println("det A: " + det1);

        Matrix matrixL = new Matrix(new double[][]{{1.0,0.0,0.0},{0.0,1.0,0.0},{0.0,0.0,3.0}});

        double det2 = Matrix.determinant(matrixL);

        System.out.println("Matrix L: " + matrixL);

        System.out.println("det L: " + det2);

        Matrix matrixM = Matrix.inverse(matrixL);

        System.out.println("Matrix M: " + matrixM);

        Matrix matrixN = new Matrix(new double[][]{{1.0,0.0,0.0},{0.0,1.0,0.0},{0.0,0.0,1.0}});

        Matrix matrixO = Matrix.matrixMultiply(matrixH,matrixN);

        System.out.println("Matrix O: " + matrixO);

        Matrix matrixP = Matrix.matrixMultiply(matrixH, matrixL);

        System.out.println("Matrix P: " + matrixP);


    }
}*/