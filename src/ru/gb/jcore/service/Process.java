package ru.gb.jcore.service;

import java.io.*;
import java.util.*;

public class Process {

    /**
     * Функция обработки ответа пользователя
     * Возвращает:
     *      "exit" - если введена пустая строка
     *      "change_path" - если введена команда смены файла-приемника
     *      "take" - если введена заметка
     * @param answer - ответ пользователя
     */
    public static String takeIt(String answer) {
        String result;
        if (answer.isEmpty()) {
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
     * @param path - путь к файлу-источнику
     * @return arr - возвращаемое значение
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
