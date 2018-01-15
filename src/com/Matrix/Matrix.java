package com.Matrix;

public interface Matrix {
    double get(int i, int j);
    void put(int i, int j, double d);
    Matrix transpose ();
    Matrix plus(Matrix matrix);
    Matrix multiplication(Matrix matrix);
}
