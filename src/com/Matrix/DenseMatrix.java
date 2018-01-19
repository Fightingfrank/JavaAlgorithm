package com.Matrix;

public class DenseMatrix implements Matrix{


    private int numberOfRows;
    private int numberOfColumns;
    private double[][] array;

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public double[][] getArray() {
        return array;
    }

    public DenseMatrix(int numberOfRows, int numberOfColumns){
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        array = new double[numberOfRows][numberOfColumns];
    }

    @Override
    public double get(int i, int j) {
        return array[i][j];
    }

    @Override
    public void put(int i, int j, double d) {
        this.array[i][j] = d;
    }

    @Override
    public Matrix transpose() {
        Matrix result= new DenseMatrix(this.numberOfColumns,this.getNumberOfRows());
        for(int i = 0 ; i < this.getNumberOfRows() ; i++){
            for(int j = 0 ; j < this.getNumberOfColumns() ; j++){
                result.put(j,i,this.get(i,j));
            }
        }
        return result;
    }

    @Override
    public Matrix plus(Matrix matrix) {
        DenseMatrix dMatrix = (DenseMatrix)matrix;
        if(dMatrix.getNumberOfColumns() != this.getNumberOfColumns() || dMatrix.getNumberOfRows() != this.getNumberOfRows()){
            System.out.println("only two matrixs have same m*n could make plus operation making sense.");
            return null;
        }

        DenseMatrix result = new DenseMatrix(dMatrix.getNumberOfRows(),dMatrix.getNumberOfColumns());
        for(int i = 0 ; i < result.getNumberOfRows(); i++){
            for(int j = 0 ; j < result.getNumberOfColumns(); j++){
                result.put(i,j,this.get(i,j)+dMatrix.get(i,j));
            }
        }

        return result;
    }

    @Override
    public Matrix subtraction(Matrix matrix) {
        DenseMatrix dMatrix = (DenseMatrix)matrix;
        if(dMatrix.getNumberOfColumns() != this.getNumberOfColumns() || dMatrix.getNumberOfRows() != this.getNumberOfRows()){
            System.out.println("only two matrixs have same m*n could make plus operation making sense.");
            return null;
        }

        DenseMatrix result = new DenseMatrix(dMatrix.getNumberOfRows(),dMatrix.getNumberOfColumns());
        for(int i = 0 ; i < result.getNumberOfRows(); i++){
            for(int j = 0 ; j < result.getNumberOfColumns(); j++){
                result.put(i,j,this.get(i,j)-dMatrix.get(i,j));
            }
        }

        return result;
    }

    @Override
    public Matrix multiplication(Matrix matrix) {
        DenseMatrix dmatrix = (DenseMatrix)matrix;
        DenseMatrix result = new DenseMatrix(this.numberOfRows,dmatrix.getNumberOfColumns());
        if(this.getNumberOfColumns()!= dmatrix.getNumberOfRows())
            System.out.println("Only when the first matrix's columns number must be equal with second matrix's rows, while the multiplication while make sense!");
        else{

            for(int i = 0 ; i < result.getNumberOfRows() ; i++){
                for(int j = 0 ; j < result.getNumberOfColumns() ; j++){
                    double sum = 0;
                    for(int k = 0 ; k < this.getNumberOfColumns() ; k++){
                        sum = sum + array[i][k]*dmatrix.getArray()[k][j];
                    }
                    result.getArray()[i][j] = sum;
                }
            }
        }
        return result;
    }
}
