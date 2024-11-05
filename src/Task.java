import java.time.LocalDate;

public class Task implements TaskInterface {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = LocalDate.parse(dueDate);
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
        return dueDate.toString();
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
        this.dueDate = LocalDate.parse(dueDate);
    }

    @Override
    public void markAsCompleted() {
        this.completed = true;
    }
}
