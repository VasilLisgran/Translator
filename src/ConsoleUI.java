import java.util.Scanner;

public class ConsoleUI {
    public static Scanner scanner = new Scanner(System.in);

    private Translator translator;

    public ConsoleUI() {
        this.translator = new Translator();
    }

    public void start() {
        boolean isShown = true;
        //чтобы не остановилось никогда
        while (isShown) {
            showMenu();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    this.translator.showWords();
                    showBackOptionAndWait();
                    System.out.println(" ");
                    break;

                case 2:
                    scanner.nextLine();
                    System.out.println("Введите слово на иностранном языке");
                    String original = scanner.nextLine();
                    System.out.println("Введите слово на русском языке");
                    String translated = scanner.nextLine();
                    this.translator.addWord(original, translated);
                    System.out.println(" ");
                    showBackOptionAndWait();
                    break;

                case 3:
                    System.out.println("Какое слово желаете удалить?");
                    int num3 = scanner.nextInt();
                    translator.removeWord(num3 - 1);
                    System.out.println(" ");
                    break;
                case 4:
                    System.out.println("Выберите перевод");
                    System.out.println("1. На русский");
                    System.out.println("2. На иностранный язык");
                    String input;
                    int num4 = scanner.nextInt();
                    scanner.nextLine(); // ⬅️ ОЧИСТКА БУФЕРА!

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
        }

        scanner.close();
    }

    public void showMenu() {
        System.out.println("МЕНЮ");
        System.out.println("1) Показать все слова");
        System.out.println("2) Добавить слово");
        System.out.println("3) Удалить слово");
        System.out.println("4) Найти перевод");
    }

    private void showBackOptionAndWait() {
        System.out.println("1) Назад");
        boolean isShown = false;

        int localChoice;
        do {
            localChoice = scanner.nextInt();
            if (localChoice == 1) {
                break;
            } else {
                System.out.println("Неверный ввод");
            }
        } while (localChoice != 1);

        isShown = true;
    }

}