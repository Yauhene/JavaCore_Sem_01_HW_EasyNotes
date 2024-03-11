package ru.gb.jcore.service;

import java.io.*;
import java.nio.charset.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

public class Process {

    /**
     * Функция обработки ответа пользователя
     * Возвращает:
     *      "exit" - если введена пустая строка
     *      "change_path" - если введена команда смены файла-приемника
     *      "take" - если введена заметка
     * @param answer - ответ пользователя
     * @return
     */
    public static String takeIt(String answer) {
        String result = "0";
        if (answer.equals("")) {
            result = "exit";
        } else if (answer.equals("-cp")) {
            result = "change_path";
        } else {
            result = "take";
        }
        return result;
    }

    /**
     * Метод добавления заметки в файл
     * @param path - путь к файлу
     * @param answer - ответ пользователя (новая заметка)
     * @throws IOException - ошибка ввода-вывода
     */
    public static void writeToFile(String path, String answer) throws IOException {
        String tempString = "";
        Date currTime;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        if (path != null){
            try {
                OutputStream outputStream = new FileOutputStream(path, true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                tempString += answer + "\n";
                outputStreamWriter.write(tempString);
                outputStreamWriter.flush();
                outputStreamWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Функция, возвращающая массив заметок из файла
     * @param path
     * @return
     */
    public static ArrayList<String> notes_from_File(String path) {
        ArrayList<String> arr = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                arr.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Указанный файл не был найден, он будет создан.");
        }
        return arr;
    }
}
