import java.time.LocalDate;
import java.time.LocalTime;

public class WeeklyTask extends Task {

    public WeeklyTask(String title, Type type, LocalDate date, LocalTime time, String description, Repeatability repeatability) {
        super(title, type, date, time, description, repeatability);
    }

    @Override
    public boolean appearsIn(String date) {
        LocalDate date1 = TaskService.convertDate(date);
        return this.getDate().equals(date1) || (this.getDate().isBefore(date1) && this.getDate().getDayOfWeek().equals(date1.getDayOfWeek()));
    }
}
