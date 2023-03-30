import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import static java.time.LocalDate.now;


public class TaskService {
    private static final Map<Integer, Task> tasks = new HashMap<>();
    private static String date = DateTimeFormatter.ofPattern("ddMMyyyy").format(now());


    public static Map<Integer, Task> getTasks() {
        return tasks;
    }

    public static String getDate() {
        return date;
    }

    public static LocalDate convertDate(String date) {
        date = date.replaceAll("\\p{Punct}|\\s", "");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return LocalDate.parse(date, formatter);
    }

    public static void add(Task task) {
        tasks.put(task.getId(), task);
    }

    public static Collection<Task> getAllByDate(String date) {
        List<Task> taskList = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.appearsIn(date)) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    public static void remove(int id) {
        tasks.remove(id);
    }

    public static void printMenu() {
        System.out.println(
                "   0. Выход\n" +
                        "   1. Добавить задачу\n" +
                        "   2. Удалить задачу\n" +
                        "   3. Все задачи на сегодня\n"
        );
    }

    public static void addTask(Scanner scanner) {
        System.out.println("Введите название задачи ");
        String title = Task.checkTitle(scanner.nextLine());
        System.out.println("Введите описание задачи ");
        String description = Task.checkDescription(scanner.nextLine());

        LocalDate date;
        LocalTime time;
        Task.Type type;
        Task.Repeatability repeatable;

        while (true) {
            System.out.println("Введите дату выполнения в формате dd.MM.yyyy ");
            try {
                date = TaskService.convertDate(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Неправильный формат даты. Необходимый формат: dd.MM.yyyy");
            }
        }
        while (true) {
            System.out.println("Введите время выполнения в формате HH:mm ");
            try {
                time = Task.checkTime(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Неправильный формат ремени. Необходимый формат: HH:mm");
            }
        }

        while (true) {
            System.out.println("Выберите тип задачи: 1 - личная, 2 - рабочая");
            int taskType = scanner.nextInt();
            try {
                type = Task.checkType(taskType);
                break;
            } catch (Exception e) {
                System.out.println("Некорректный тип");
            }
        }
        while (true) {
            System.out.println("Выберите повторяемость задачи: \n 1 - однократно \n 2 - ежедневно \n 3 - еженедельно \n 4 - ежемесячно \n 5 - ежегодно");
            int taskRepeat = scanner.nextInt();
            try {
                repeatable = Task.checkRepeatability(taskRepeat);
                break;
            } catch (Exception e) {
                System.out.println("Некорректные данные");
            }

        }
        switch (repeatable) {
            case ONE_TIME:
                OneTimeTask oneTimeTask = new OneTimeTask(title, type, date, time, description, repeatable);
                TaskService.add(oneTimeTask);
                break;
            case DAILY:
                DailyTask dailyTask = new DailyTask(title, type, date, time, description, repeatable);
                TaskService.add(dailyTask);
                break;
            case WEEKLY:
                WeeklyTask weeklyTask = new WeeklyTask(title, type, date, time, description, repeatable);
                TaskService.add(weeklyTask);
                break;
            case MONTHLY:
                MonthlyTask monthlyTask = new MonthlyTask(title, type, date, time, description, repeatable);
                TaskService.add(monthlyTask);
                break;
            case YEARLY:
                YearlyTask yearlyTask = new YearlyTask(title, type, date, time, description, repeatable);
                TaskService.add(yearlyTask);
                break;
        }

    }

    public static void printTask() {
        if (getAllByDate(getDate()).isEmpty()) {
            System.out.println("Задач нет");
        } else
            for (Task task : getAllByDate(getDate())) {
                System.out.println(task);
            }

    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Введите ID задачи");
        int idTask = scanner.nextInt();
        try {
            remove(idTask);
            System.out.println("Задача № " + idTask + " удалена");
        } catch (Exception e) {
            System.out.println("Задача с ID " + idTask + " не найдена");
        }
    }

}

