package main.java;

import java.time.LocalDate;

public class StatisticManager {

    private static StatisticManager instance;
    private StatisticData data;

    private StatisticManager(){
        data = new StatisticData(0, 0, 0, LocalDate.now().toString());
    }

    //использую Singletone, так как один глобальный объект,
    //глобальная точка доступа для вызова из любого класса
    public static StatisticManager getInstance(){
        if(instance == null){
            instance = new StatisticManager();
        }
        return instance;
    }

    //увеличение информации
    public void recordAdded(){
        dateCheck();
        data.setTotalWords(data.getTotalWords() + 1);
        data.setWordsToday(data.getWordsToday() + 1);
        data.setWordsThisMonth(data.getWordsThisMonth() + 1);
        data.setLastDate(LocalDate.now().toString());
    }

    //получение данных
    public StatisticData getData() {
        return data;
    }

    //замена данных в синглтоне
    public void loadData(StatisticData newData){
        this.data = newData;
        dateCheck();
    }

    //проверка даты
    public void dateCheck(){
        LocalDate today = LocalDate.now();

        LocalDate lastDate = LocalDate.parse(data.getLastDate());

        if(!today.equals(lastDate)){
            data.setWordsToday(0);
            data.setLastDate(today.toString());
            if(today.getMonth() != lastDate.getMonth()){
                data.setWordsThisMonth(0);
            }
        }
    }

    public void showStats() {
        dateCheck(); // Всегда актуальные данные

        System.out.println("СТАТИСТИКА");
        System.out.println("Всего слов: " + data.getTotalWords());
        System.out.println("Добавлено сегодня: " + data.getWordsToday());
        System.out.println("Добавлено в этом месяце: " + data.getWordsThisMonth());
        System.out.println("Последнее обновление: " + data.getLastDate());
    }
}
