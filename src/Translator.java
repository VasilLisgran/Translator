import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Translator{
    public static Scanner scanner = new Scanner(System.in);

    List<Word> words = new ArrayList<>(); //список слов
    private int nextId = 1;


    //добавление слова
    public void addWord(String original, String translation){
        if(original.isEmpty() || translation.isEmpty()) {
            System.out.println("Слово быть пустым не может!");
            return;
        }

        if(hasWord(original, translation)){
            System.out.println("Такое слово уже есть!");
        }
        else {
            Word word = new Word(this.nextId, original, translation);
            words.add(word);
            this.nextId++;
            System.out.println("Слово успешно добавлено!");
        }
    }

    //удаление слова
    public void removeWord(int number){
        if (number < words.size()){
            words.remove(number);
            System.out.println("Успешное удаление!");
        }
        else System.out.println("Некорректный ввод!");
    }

    //проверка, что слово есть
    private boolean hasWord(String original, String translation){
        for(Word word : words){
            if(word.getOriginal().equalsIgnoreCase(original) &&
                    word.getTranslation().equalsIgnoreCase(translation)) return true;
        }
        return false;
    }

    //получение всех слов
    public void showWords(){
        if(this.words.isEmpty()) System.out.println("Словарь пуст!");
        else {
            for (int i = 0; i < words.size(); i++) {
                System.out.println(i + 1 + ". " + words.get(i).getOriginal() + ", " + words.get(i).getTranslation() + ", " + words.get(i).getFormattedDate());
            }
        }
    }

    //перевод
    public String Translate(String input, TranslationMode mode){
        for (Word word : words){
            if(mode == TranslationMode.RU_TO_FOREIGN){
                if(input.equalsIgnoreCase(word.getOriginal())){
                    return word.getTranslation();
                }
            }
            else {
                if(input.equalsIgnoreCase(word.getTranslation())){
                    return word.getOriginal();
                }
            }
        }
        return "Не найдено!";
    }

    enum TranslationMode { RU_TO_FOREIGN, FOREIGN_TO_RU }
}

