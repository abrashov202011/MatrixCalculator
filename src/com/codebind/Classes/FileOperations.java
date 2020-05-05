package com.codebind.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * Класс для работы с файлами
 * @autor Абрашов
 * @version 1.0
 */
public class FileOperations {
    /**
     * Функция записи в файл результатов результатов операции с 1 матрицей
     */
    public static void writeOneMatrixOperationToFile(String operand1, String result, String operation) {
        try {
            File myObj = new File("log.txt");
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter("log.txt", true);
            myWriter.append(System.lineSeparator());
            myWriter.append(getCurrentDate());
            myWriter.append(System.lineSeparator());
            myWriter.append(System.lineSeparator());

            myWriter.append(operand1);

            myWriter.append(System.lineSeparator());

            myWriter.append(operation);

            myWriter.append(System.lineSeparator());
            myWriter.append(System.lineSeparator());

            myWriter.append(result);
            myWriter.append(System.lineSeparator());
            myWriter.append("***********************************************************************************************");
            myWriter.append(System.lineSeparator());

            myWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Функция записи в файл результатов результатов операции с 2 матрицами
     */
    public static void writeTwoMatrixOperationToFile(String operand1, String operand2, String result, String operation) {
        try {
            File myObj = new File("log.txt");
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter("log.txt", true);
            myWriter.append(System.lineSeparator());
            myWriter.append(getCurrentDate());
            myWriter.append(System.lineSeparator());
            myWriter.append(System.lineSeparator());

            myWriter.append(operand1);
            myWriter.append(System.lineSeparator());

            myWriter.append(operation);

            myWriter.append(System.lineSeparator());
            myWriter.append(System.lineSeparator());

            myWriter.append(operand2);
            myWriter.append(System.lineSeparator());
            myWriter.append("=");
            myWriter.append(System.lineSeparator());
            myWriter.append(System.lineSeparator());

            myWriter.append(result);
            myWriter.append(System.lineSeparator());
            myWriter.append("***********************************************************************************************");
            myWriter.append(System.lineSeparator());

            myWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Функция возвращающая текущую дату для записи в файл
     * @return возвращает текущую дату
     */
    private static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
