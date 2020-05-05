package com.codebind.Classes;

import javax.swing.*;
import java.util.Arrays;

public class Matrix {
    float[][] matrix;
    public Matrix(int rows, int columns) {
        matrix = new float[rows][columns];
    }
    public Matrix(float[][] matrix) {
        this.matrix = matrix;
    }
    public  Matrix(JTable table) {
        int rows = table.getRowCount();
        int columns = table.getColumnCount();
        matrix = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String value = (String)table.getValueAt(i,j);
                if(value == null || value.isEmpty()) {
                    throw new Error(String.format("Элемент в строке %d и стобце %d не заполнен", i + 1, j + 1));
                }
                try {
                    matrix[i][j] = Float.parseFloat(value);
                }
                catch (Exception e) {
                    throw new Error(String.format("Элемент в строке %d и стобце %d не является числом", i + 1, j + 1));
                }

            }
        }
    }
    static void getCofactor(float mat[][], float temp[][], int p, int q, int n)    {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                if (row != p && col != q)
                {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    public float getDeterminant()
    {
        return determinantOfMatrix(this.matrix, this.matrix.length);
    }
    private static float determinantOfMatrix(float mat[][], int n)
    {
        float D = 0;
        if (n == 1)
            return mat[0][0];
        float temp[][] = new float[n][n];
        int sign = 1;
        for (int f = 0; f < n; f++)
        {
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f] * determinantOfMatrix(temp, n - 1);
            sign = -sign;
        }
        return D;
    }
    public Matrix inverse()
    {
        float[][] inverse = new float[matrix.length][matrix.length];
        float det = getDeterminant();
        if (det == 0)
        {
            throw new Error("Невозможно вычислить отраиную матрицу для матрицы определитель которой равен 0");
        }
        float [][]adj = new float[matrix.length][matrix.length];
        adjoint(matrix, adj);

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                inverse[i][j] = adj[i][j]/(float)det;

        return new Matrix(inverse);
    }
    static void adjoint(float mat[][],float [][]adj)
    {
        int N = mat.length;
        if (N == 1)
        {
            adj[0][0] = 1;
            return;
        }
        int sign = 1;
        float [][]temp = new float[N][N];

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                getCofactor(mat, temp, i, j, N);
                sign = ((i + j) % 2 == 0)? 1: -1;
                adj[j][i] = (sign)*(determinantOfMatrix(temp, N-1));
            }
        }
    }
    public Matrix transpose() {
        int columns = matrix.length;
        int rows = matrix[0].length;
        float[][] result = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return new Matrix(result);
    }
    public static Matrix addition(Matrix matrix1, Matrix matrix2)
    {
        float[][] A = matrix1.matrix;
        float[][] B = matrix2.matrix;
        int rows = A.length;
        int columns = A[0].length;
        float[][] result = new float[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                result[i][j] = A[i][j] + B[i][j];
        return new Matrix(result);
    }
    public static Matrix subtraction(Matrix matrix1, Matrix matrix2)
    {
        float[][] A = matrix1.matrix;
        float[][] B = matrix2.matrix;
        int rows = A.length;
        int columns = A[0].length;
        float[][] result = new float[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                result[i][j] = A[i][j] - B[i][j];
        return new Matrix(result);
    }
    public static Matrix multiply(Matrix matrix1, Matrix matrix2)
    {
        float[][] A = matrix1.matrix;
        float[][] B = matrix2.matrix;
        int rows1 = A.length;
        int columns1 = A[0].length;
        int rows2 = A.length;
        int columns2 = A[0].length;
        float[][] result = new float[rows1][columns2];
        for (int i = 0; i < rows1; i++)
        {
            for (int j = 0; j < columns2; j++)
            {
                result[i][j] = 0;
                for (int k = 0; k < columns1; k++)
                    result[i][j] += A[i][k] * B[k][j];
            }
        }
        return  new Matrix(result);
    }
    public float[][] getMatrix(){
        return  matrix;
    }

    @Override
    public String toString() {
        String result = "";
        int rows = matrix.length;
        int columns = matrix[0].length;
        int maxLength = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int curLenght = String.valueOf(matrix[i][j]).length();
                maxLength = maxLength > curLenght ? maxLength : curLenght;
            }
        }
        for (int i = 0; i < rows; i++) {
            result += "| ";
            for (int j = 0; j < columns; j++) {
                result += matrix[i][j] + "   ";
                int curLenght = String.valueOf(matrix[i][j]).length();
                //if(j != columns - 1) {
                    for (int k = 0; k < maxLength - curLenght; k++) {
                        result += " ";
                    }
                //}
            }
            result += "|" +  System.lineSeparator();
        }
        return  result;
    }
}
