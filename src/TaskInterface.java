public interface TaskInterface {
    String getTitle();
    String getDescription();
    boolean isCompleted();
    void markAsCompleted();
    void setTitle(String title);
    void setDescription(String description);
}


