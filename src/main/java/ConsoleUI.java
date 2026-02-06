package main.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {
    public static Scanner scanner = new Scanner(System.in);

    private final Translator translator;
    private StatisticManager SM = StatisticManager.getInstance();

    // Константы для меню
    private static final int SHOW_ALL_WORDS = 1;
    private static final int ADD_WORD = 2;
    private static final int REMOVE_WORD = 3;
    private static final int FIND_TRANSLATION = 4;
    private static final int SHOW_STATISTICS = 5;

    public ConsoleUI() {
        this.translator = new Translator();

    }

    public void start() {
        boolean isShown = true;
        //Чтобы консольная программа работала, пока не остановим сами
        while (isShown) {
            showMenu();

            //Инициализация по умолчанию для корректного пользования в switch
            int choice = -1;

            //Ловим ошибку для адекватного ввода
            try {
                choice = scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка! Введите целое число.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case SHOW_ALL_WORDS:
                    handle_ShowAllWords();
                    break;

                case ADD_WORD:
                    handle_AddWords();
                    break;

                case REMOVE_WORD:
                    handle_RemoveWords();
                    break;

                case FIND_TRANSLATION:
                    handle_FindTranslation();
                    break;

                case SHOW_STATISTICS:
                    handle_Statistic();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        }

        scanner.close();
    }

    public void showMenu() {
        System.out.println("МЕНЮ");
        System.out.println("1) Показать все слова");
        System.out.println("2) Добавить слово");
        System.out.println("3) Удалить слово");
        System.out.println("4) Найти перевод");
        System.out.println("5) Показать статистику");
    }

    private void handle_ShowAllWords(){
        this.translator.showWords();
        showBackOptionAndWait();
        System.out.println(" ");
    }

    private void handle_AddWords(){
        scanner.nextLine();
        System.out.println("Введите слово на иностранном языке");
        String original = scanner.nextLine();
        System.out.println("Введите слово на русском языке");
        String translated = scanner.nextLine();
        this.translator.addWord(original, translated);
        System.out.println(" ");
        showBackOptionAndWait();
    }

    private void handle_RemoveWords(){
        System.out.println("Какое слово желаете удалить?");
        int num3 = -1;
        do {
            try {
                num3 = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод!");
                scanner.nextLine();
            }
        }
        while (num3 == -1);
        translator.removeWord(num3 - 1);
        System.out.println(" ");
    }

    public void handle_FindTranslation() {
        System.out.println("Выберите перевод");
        System.out.println("1. На русский");
        System.out.println("2. На иностранный язык");
        String input;
        int num4 = -1;

        do {
            try {
                num4 = scanner.nextInt();

                if (num4 == 1 || num4 == 2) {
                    break; // Выход из цикла если ввод корректен
                } else {
                    System.out.println("Неверный ввод! Введите 1 или 2:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод!");
                scanner.nextLine();
            }
        }
        while (num4 != 1 || num4 != 2);

        scanner.nextLine(); // ⬅ ОЧИСТКА БУФЕРА!

        if (num4 == 1) {
            System.out.println("Введите слово на иностранном языке");
            input = scanner.nextLine();
            System.out.println(this.translator.Translate(input, Translator.TranslationMode.FOREIGN_TO_RU));
            System.out.println(" ");
            showBackOptionAndWait();

        } else if (num4 == 2) {
            System.out.println("Введите слово на русском языке");
            input = scanner.nextLine();
            System.out.println(this.translator.Translate(input, Translator.TranslationMode.RU_TO_FOREIGN));
            System.out.println(" ");
            showBackOptionAndWait();

        } else {
            System.out.println("Неправильный ввод!");
        }
    }

    private void handle_Statistic(){
        System.out.println("Статистика:");
        this.SM.showStats();
    }

    private void showBackOptionAndWait() {
        System.out.println("1) Назад");

        int localChoice = -1;
        do {
            try {
                localChoice = scanner.nextInt();
                if (localChoice == 1) {
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Неверный ввод!");
                scanner.nextLine();
            }

        } while (localChoice != 1);
    }

}