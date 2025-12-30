package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Word {
    //слово

    //переменные
    private int id;
    private String original;
    private String translation;
    private String addedDate;

    public Word(){

    }
    //конструктор
    public Word(int id, String original, String translation) {
        this.id = id;
        this.original = original;
        this.translation = translation;
        this.addedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    //методы

    public String getAddedDate() {
        return addedDate;
    }
    public void setAddedDate(String addedDate) {this.addedDate = addedDate;}

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

    public String getOriginal() {
        return original;
    }
    public void setOriginal(String original) { this.original = original; }

    public String getTranslation() {
        return translation;
    }
    public void setTranslation(String translation) {
        this.translation = translation;
    }

}
