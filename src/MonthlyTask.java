import java.time.LocalDate;
import java.time.LocalTime;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, Type type, LocalDate date, LocalTime time, String description, Repeatability repeatability) {
        super(title, type, date, time, description, repeatability);
    }

    @Override
    public boolean appearsIn(String date) {
        LocalDate date1 = TaskService.convertDate(date);
        return this.getDate().equals(date1) || (this.getDate().isBefore(date1) && this.getDate().getDayOfMonth() == (date1.getDayOfMonth()));
    }
}
