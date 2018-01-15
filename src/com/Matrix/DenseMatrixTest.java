package com.Matrix;

public class DenseMatrixTest {
    public static void main(String args[]) {
        Matrix matrix1 = new DenseMatrix(2,3);
        Matrix matrix2 = new DenseMatrix(3,2);
        for(int i = 0 ; i < 2; i++){
            for (int j = 0 ; j < 3; j ++){
                matrix1.put(i,j,i+j);
            }
        }

        for(int i = 0 ; i < 3; i++){
            for(int j = 0 ; j < 2; j++){
                matrix2.put(i,j,i-j);
            }
        }

        DenseMatrix result = (DenseMatrix)matrix1.multiplication(matrix2);

        for(int i = 0 ; i < result.getNumberOfRows(); i++){
            for(int j = 0 ; j < result.getNumberOfColumns() ; j++){
                System.out.print(result.getArray()[i][j] + " ");
            }
            System.out.println();
        }
    }
}
