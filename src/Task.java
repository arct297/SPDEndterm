import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task implements TaskInterface {
    private String title;
    private String description;
    private String dueDate;
    private boolean completed;

    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getDueDate() {
        return this.dueDate;
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
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
        this.dueDate = dueDate;
    }

    @Override
    public void markAsCompleted() {
        this.completed = true;
    }
}
