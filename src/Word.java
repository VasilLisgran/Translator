import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Word {
    //слово

    //переменные
    private final int id;
    private final String original;
    private final String translation;
    private final LocalDateTime addedDate;

    //конструктор
    public Word(int id, String original, String translation) {
        this.id = id;
        this.original = original;
        this.translation = translation;
        this.addedDate = LocalDateTime.now();
    }

    //методы
    public String getFormattedDate() {
        return addedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public int getId() {
        return id;
    }

    public String getOriginal() {
        return original;
    }

    public String getTranslation() {
        return translation;
    }

}
