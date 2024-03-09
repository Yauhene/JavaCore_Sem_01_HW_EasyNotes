package ru.gb.jcore.service;

import java.io.*;
import java.util.*;

public class StartYourCoffins {
    public String destPath; // путь к файлу назначения
    public ArrayList<String> notesList;

    /**
     * Стартовый метод.
     * При отсутствии аргумента командной строки выводит предупреждение и закрывает программу.
     * @param path - параметр командной строки
     * @throws IOException
     */
    public StartYourCoffins(String[] path) throws IOException {
        System.out.println();
        if (path.length == 0) {
            System.out.println("Файл-приемник не указан в командной строке");
            System.out.println("Печально, но это - ФИАСКО !!!");
        } else {
            this.destPath = path[0];
            notesList = Process.notes_from_File(destPath);
            Menu.menu(destPath, notesList);
        }
    }
}
