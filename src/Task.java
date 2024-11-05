import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task implements TaskInterface {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.setDueDate(dueDate); // Use the setter to ensure date format
        this.completed = false;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDueDate() {
        return dueDate.format(DATE_FORMAT); // Format the date as "YYYY-MM-DD"
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setDueDate(String dueDate) {
        this.dueDate = LocalDate.parse(dueDate); // Ensure the date is parsed correctly
    }

    @Override
    public void markAsCompleted() {
        this.completed = true;
    }
}
