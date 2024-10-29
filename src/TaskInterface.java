public interface TaskInterface {
    String getTitle();
    String getDescription();
    String getDueDate();
    boolean isCompleted();

    void setTitle(String title);
    void setDescription(String description);
    void setDueDate(String dueDate);
    void markAsCompleted();
}
