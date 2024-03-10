package ru.gb.jcore.service;

import java.io.*;
import java.nio.charset.*;
import java.text.*;
import java.util.*;

public class Menu {

    /**
     * Метод меню.
     * Принимает ответ пользователя и в соответствии с ним запускает:
     *      1. Процесс выхода
     *      2. Процесс изменения файла-приемника
     *      3. Принимает новую заметку
     * @param path - путь к файлу-приемнику
     * @param notes - массив заметок в файле
     * @throws IOException
     */
    public static void menu(String path, ArrayList<String> notes) throws IOException {
        boolean getOut = false;
        String answer = "";
        String note = "";
        int procSelector = 0;
        Scanner scanner = new Scanner(System.in);
        // ============================== цикл меню =============================
        while (!getOut) {
            prompt(path); // Вывод приглашения
            answer = scanner.nextLine();
            switch (Process.takeIt(answer)) {
                case "exit": {
                    getOut = true;
                    break;
                }
                case "change_path": {
                    path = changePath();
                    break;
                }
                default: {
                    Date currTime;
                    currTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//                    note = "Лошаааа-дкааааа!!!";
//                    answer = "Лошаааа-дкааааа!!!";

//                    note = answer;
//           Забито временно
//                    answer = new String (answer.getBytes(StandardCharsets.UTF_8));
//                    answer= "Added " + formatter.format(currTime) + " : " + answer;
//                    Process.writeToFile(path, answer);


                    note = new String (answer.getBytes("UTF-8"));
                    note = "Added " + formatter.format(currTime) + " : " + note;
                    Process.writeToFile(path, note);

                    System.out.println("note = " + note);
                    System.out.println("answer = " + answer);
                }
            }
        }
        // ============================== конец цикла меню ======================
    }

    public static void prompt(String path) {
        if (path != null) {
            System.out.println("Файл-приемник: " + path);
        } else {
            System.out.println("Файл-приемник не указан. ");
        }
        System.out.println();
        System.out.println("Для выхода просто нажмите Enter");
        System.out.println("Для изменения файла-приемника введите \"-cp\"");
        System.out.println();
        System.out.println("Введите заметку: ");
    }

    /**
     * Функция, возвращающая новый путь к файлу-приемнику
     * @return
     */
    public static String changePath() {
        String result = "";
        String newPath = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новый путь к файлу: ");
        newPath = scanner.nextLine();
        if (!newPath.equals("")) {
            result = newPath;
        }
        return newPath;
    }
}
