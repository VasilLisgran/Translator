package main.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Translator{
    public static Scanner scanner = new Scanner(System.in);

    List<Word> words = new ArrayList<>(); //список слов
    private int nextId = 1;
    private ObjectMapper mapper = new ObjectMapper();
    private final File dataFile = new File("vocabulary.json");

    public Translator() {
        this.words = new ArrayList<>();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //System.out.println("Файл словаря: " + dataFile.getAbsolutePath()); путь к словарю
        loadWords();
    }

    //добавление слова
    public void addWord(String original, String translation){

        if(original.isBlank() || translation.isBlank()) {
            System.out.println("Слово быть пустым не может!");
            return;
        }
        if (!hasNums(original, translation)){
            System.out.println("Цифр быть не может!");
            return;
        }
        if(hasWord(original, translation)){
            System.out.println("Такое слово уже есть!");
        }
        else {
            Word word = new Word(this.nextId, original, translation);
            words.add(word);
            this.nextId++;

            saveWord();

            System.out.println("Слово успешно добавлено!");
        }
    }

    //сохранение в json
    private void saveWord(){
        try {
            mapper.writeValue(dataFile, words);

        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    //загрузка всех слов
    private void loadWords() {
        try {
            //если существует файл слов и он непустой
            if(dataFile.exists() && dataFile.length() > 0){
                words = mapper.readValue(dataFile, new TypeReference<List<Word>>() {});

                for (Word word : words) {
                    if (word.getId() >= nextId) {
                        nextId = word.getId() + 1;
                    }
                }

                System.out.println("Загружено " + words.size() + " " + CasesOfWords(words.size()));
            }
        }
        catch (IOException e){
            System.out.println("Ошибка!");

            //e.printStackTrace(); //выводит сообщение конкретной ошибки
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

    //Проверка, что есть числа в строке
    public boolean hasNums(String original, String translation){
        for (int i = 0; i < original.length(); i++) {
            if ('0' <= original.charAt(i) && original.charAt(i) <= '9') {
                return false;
            }
        }
        for (int i = 0; i < translation.length(); i++) {
            if ('0' <= translation.charAt(i) && translation.charAt(i) <= '9') {
                return false;
            }
        }
        return true;
    }

    //получение всех слов
    public void showWords(){
        if(this.words.isEmpty()) System.out.println("Словарь пуст!");
        else {
            for (int i = 0; i < words.size(); i++) {
                System.out.println(i + 1 + ". " + words.get(i).getOriginal() + ", " + words.get(i).getTranslation() + ", " + words.get(i).getAddedDate());
            }
        }
    }

    //перевод
    public String Translate(String input, TranslationMode mode){
        for (Word word : words){
            if(mode == TranslationMode.RU_TO_FOREIGN){
                if(input.equalsIgnoreCase(word.getTranslation())){
                    return word.getOriginal();
                }
            }
            else {
                if(input.equalsIgnoreCase(word.getOriginal())){
                    return word.getTranslation();
                }
            }
        }
        return "Не найдено!";
    }

    public String CasesOfWords(int number){
        int lastDigit = number % 10;
        int lastTwoDigits = number%100;
        if(11 <= lastTwoDigits && lastTwoDigits <= 20) return "слов";

        return switch (lastDigit){
            case 1 -> "слово";
            case 2,3,4 -> "слова";
            default -> "слов";
        };
    }

    public enum TranslationMode { RU_TO_FOREIGN, FOREIGN_TO_RU }
}

