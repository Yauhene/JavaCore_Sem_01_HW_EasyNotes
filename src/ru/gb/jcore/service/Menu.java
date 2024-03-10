package ru.gb.jcore.service;

import java.io.*;
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
     * @throws IOException
     */
    public static void menu(String path) throws IOException {
        boolean getOut = false;
        String answer = "";
        String note = "";
        int procSelector = 0;
        Scanner scanner = new Scanner(System.in);
        // ============================== цикл меню =============================

        while (!getOut) {
            clearIt();
            prompt(path); // Вывод приглашения
            answer = scanner.nextLine();
            switch (Process.takeIt(answer)) {
                case "exit": {
                    getOut = true;
                    break;
                }
                case "change_path": {
                    path = changePath(path);
                    break;
                }
                default: {
                    Date currTime;
                    currTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

                    note = "Added " + formatter.format(currTime) + " : " + answer;
                    Process.writeToFile(path, note);

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
    public static String changePath(String oldPath) {
        String result = oldPath;
        String newPath = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новый путь к файлу: ");
        newPath = scanner.nextLine();
        if (!newPath.equals("")) {
            result = newPath;
        }
        return result;
    }

    /**
     * Метод очистки экрана
     */
    public static void clearIt() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
