package main.java;

import java.time.LocalDate;

public class StatisticData {
    private static StatisticManager instance;
    private int totalWords;
    private int wordsToday;
    private int wordsThisMonth;
    private String lastDate;

    StatisticData(){
        this.totalWords = 0;
        this.wordsToday = 0;
        this.wordsThisMonth = 0;
        this.lastDate = LocalDate.now().toString();
    }

    StatisticData(int totalWords, int wordsToday, int wordsThisMonth, String lastDate){
        this.totalWords = totalWords;
        this.wordsToday = wordsToday;
        this.wordsThisMonth = wordsThisMonth;
        this.lastDate = lastDate;
    }

    //геттеры
    public int getTotalWords() { return this.totalWords; }
    public int getWordsToday() { return this.wordsToday; }
    public int getWordsThisMonth() { return this.wordsThisMonth; }
    public String getLastDate() { return lastDate; }

    //сеттеры
    public void setTotalWords(int TotalWords) { this.totalWords = TotalWords; }
    public void setWordsToday(int wordsToday) { this.wordsToday = wordsToday; }
    public void setWordsThisMonth(int wordsThisMonth) { this.wordsThisMonth = wordsThisMonth; }
    public void setLastDate(String date) { this.lastDate = date; }
}
