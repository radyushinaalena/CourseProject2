import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Task {
    private int idGenerator;
    private String title;
    private Type type;
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private static int count = 1;
    private Repeatability repeatability;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Hmm");


    enum Type {
        PERSONAL("Личная"),
        WORK("Рабочая");
        private final String type;

        Type(String type) {
            this.type = type;
        }
    }

    enum Repeatability {
        ONE_TIME("Однократно"),
        DAILY("Ежедневно"),
        WEEKLY("Еженедельно"),
        MONTHLY("Ежемесячно"),
        YEARLY("Ежегодно");

        private final String repeatability;

        Repeatability(String repeatability) {
            this.repeatability = repeatability;
        }
    }

    public Task(String title, Type type, LocalDate date, LocalTime time, String description, Repeatability repeatability) {
        this.id = count++;
        this.title = title;
        this.type = type;
        this.date = date;
        this.time = time;
        this.description = description;
        this.repeatability = repeatability;
    }

    public abstract boolean appearsIn(String date);

    @Override
    public String toString() {
        return "Задача № " + id + " " + title + " " + type.type + " " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && title.equals(task.title) && type == task.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id);
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public static String checkTitle(String title) {
        if (title.isEmpty() || title.isBlank()) {
            return "Задача № " + count;
        } else {
            return title;
        }
    }

    public static String checkDescription(String description) {
        if (description.isEmpty() || description.isBlank()) {
            return "Описание отсутствует ";
        } else {
            return description;
        }
    }

    public static LocalTime checkTime(String time) {
        time = time.replaceAll("\\p{Punct}|\\s", "");
        return LocalTime.parse(time, getFormatter());
    }

    public static Type checkType(int type) {
        if (type > 0 && type <= Type.values().length) {
            return Type.values()[type - 1];
        } else {
            throw new RuntimeException();
        }
    }

    public static Repeatability checkRepeatability(int repeat) {
        if (repeat > 0 && repeat <= Repeatability.values().length) {
            return Repeatability.values()[repeat - 1];
        } else {
            throw new RuntimeException();
        }
    }


}
