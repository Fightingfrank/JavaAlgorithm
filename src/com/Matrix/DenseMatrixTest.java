package com.Matrix;

public class DenseMatrixTest {
    public static void main(String args[]) {
        Matrix matrix1 = new DenseMatrix(2,3);
        Matrix matrix2 = new DenseMatrix(2,3);
        for(int i = 0 ; i < 2; i++){
            for(int j = 0 ; j < 3; j ++){
                matrix1.put(i,j,i+j);
            }
        }

        for(int i = 0 ; i < 2; i++){
            for(int j = 0 ; j < 3; j++){
                matrix2.put(i,j,i-j);
            }
        }

//        DenseMatrix result = (DenseMatrix)matrix1.multiplication(matrix2);
        System.out.println("original matrix1:");
        for(int i = 0 ; i < ((DenseMatrix)matrix1).getNumberOfRows(); i++){
            for(int j = 0 ; j < ((DenseMatrix)matrix1).getNumberOfColumns() ; j++){
                System.out.print(((DenseMatrix)matrix1).getArray()[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("original matrix2:");
        for(int i = 0 ; i < ((DenseMatrix)matrix2).getNumberOfRows(); i++){
            for(int j = 0 ; j < ((DenseMatrix)matrix2).getNumberOfColumns() ; j++){
                System.out.print(((DenseMatrix)matrix2).getArray()[i][j] + " ");
            }
            System.out.println();
        }

        DenseMatrix result1 = (DenseMatrix)matrix1.transpose();
        DenseMatrix result2 = (DenseMatrix)matrix1.plus(matrix2);
        for(int i = 0 ; i < result1.getNumberOfRows(); i++){
            for(int j = 0 ; j < result1.getNumberOfColumns() ; j++){
                System.out.print(result1.getArray()[i][j] + " ");
            }
            System.out.println();
        }

        for(int i = 0 ; i < result2.getNumberOfRows(); i++){
            for(int j = 0 ; j < result2.getNumberOfColumns() ; j++){
                System.out.print(result2.getArray()[i][j] + " ");
            }
            System.out.println();
        }
    }
}
