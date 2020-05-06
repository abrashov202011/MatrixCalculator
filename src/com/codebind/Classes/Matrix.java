package com.codebind.Classes;

import javax.swing.*;
/**
 * Класс для хранения матрицы
 * @autor Абрашов
 * @version 1.0
 */
public class Matrix {
    /** Поле в котором хранится матрица */
    float[][] matrix;
    /**
     * Конструктор - создание нового объекта определенной размерности
     * @param rows - количество строк
     * @param columns - количество столбцов
     */
    public Matrix(int rows, int columns) {
        matrix = new float[rows][columns];
    }
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param matrix - матрица в формате 2 ферного массива
     */
    public Matrix(float[][] matrix) {
        this.matrix = matrix;
    }
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param table - матрица в формате JTable
     */
    public  Matrix(JTable table) {
        int rows = table.getRowCount();
        int columns = table.getColumnCount();
        matrix = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String value = ((String)table.getValueAt(i,j)).replace(",",".");
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
    /**
     * Функция вычисляет алгебраическое дополнение матрицы
     * @param mat матрица
     * @param temp алгебраическое дополнение
     * @param p количество строк
     * @param q количество столбцов
     * @param n текущая размерность матрицы
     */
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
    /**
     * Функция возвращает определитель матрицы {@link Matrix#matrix}
     * @return  возвращает определитель матрицы {@link Matrix#matrix}
     */
    public float getDeterminant()
    {
        return determinantOfMatrix(this.matrix, this.matrix.length);
    }
    /**
     * Функция рекурсивно вычисляет определитель переданной матрицы
     * @param mat матрица
     * @param n текущая размерность матрицы
     * @return  возвращает определитель переданной матрицы
     */
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
    /**
     * Функция возвращает обратную матрицу для матрицы {@link Matrix#matrix}
     * @return  возвращает обратную матрицу для матрицы {@link Matrix#matrix}
     */
    public Matrix inverse()
    {
        float[][] inverse = new float[matrix.length][matrix.length];
        float det = getDeterminant();
        if (det == 0)
        {
            throw new Error("Невозможно вычислить обратную матрицу для матрицы определитель которой равен 0");
        }
        float [][]adj = new float[matrix.length][matrix.length];
        adjoint(matrix, adj);

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                inverse[i][j] = adj[i][j]/(float)det;

        return new Matrix(inverse);
    }
    /**
     * Функция вычисляет присоединенную матрицу для переданной матрицы
     * @param mat матрица
     * @param adj присоединенная матрица
     */
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
    /**
     * Функция возвращает транспонированну матрицу для матрицы {@link Matrix#matrix}
     * @return  возвращает транспонированну матрицу для матрицы {@link Matrix#matrix}
     */
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
    /**
     * Функция возвращает результат сложения 2 матриц
     * @param matrix1 1 слагаемая матрица
     * @param matrix2 2 слагаемая матрица
     * @return  возвращает результат сложения 2 матриц
     */
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
    /**
     * Функция возвращает результат вычитания 2 матриц
     * @param matrix1 1 уменьшаемая матрица
     * @param matrix2 2 вычитаемая матрица
     * @return  возвращает результат вычитания 2 матриц
     */
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
    /**
     * Функция возвращает результат умножения 2 матриц
     * @param matrix1 1 умножаемая матрица
     * @param matrix2 2 умножаемая матрица
     * @return  возвращает результат умножения 2 матриц
     */
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        float[][] firstMatrix = matrix1.matrix;
        float[][] secondMatrix = matrix2.matrix;
        float[][] result = new float[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return new Matrix(result);
    }
    /**
     * Функция умножения для 1 ячейки
     * @param firstMatrix 1 умножаемая матрица
     * @param secondMatrix 2 умножаемая матрица
     * @param row количество строк
     * @param col 2 количество столбцов
     * @return  возвращает результат умножения 2 матриц
     */
    private static float multiplyMatricesCell(float[][] firstMatrix, float[][] secondMatrix, int row, int col) {
        float cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
    /**
     * Функция возвращает матрицу {@link Matrix#matrix}
     * @return  возвращает матрицу {@link Matrix#matrix}
     */
    public float[][] getMatrix(){
        return  matrix;
    }

    /**
     * Функция возвращает преобразованную в строку матрицу {@link Matrix#matrix}
     * @return возвращает преобразованную в строку матрицу {@link Matrix#matrix}
     */
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
                for (int k = 0; k < maxLength - curLenght; k++) {
                    result += " ";
                }

            }
            result += "|" +  System.lineSeparator();
        }
        return  result;
    }
}
