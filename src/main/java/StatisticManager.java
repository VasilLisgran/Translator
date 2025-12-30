package main.java;

public class StatisticManager {
    private static StatisticManager instance;
    private int totalWords;

    private StatisticManager(){

    }

    private StatisticManager(int Total){
        this.totalWords = Total;
    }

    //использую Singletone, так как один глобальный объект,
    //глобальная точка доступа для вызова из любого класса
    public static StatisticManager getInstance(){
        if(instance == null){
            instance = new StatisticManager();
        }
        return instance;
    }

    public int getTotalWords() {
        return this.totalWords;
    }
    public void setTotalWords(int TotalWords){
        this.totalWords = TotalWords;
    }

    public void ShowStats(){
        System.out.println(this.totalWords);
    }
}
