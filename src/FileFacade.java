import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileFacade {
    private final FileManager fileManager;

    public FileFacade(String fileName) {
        this.fileManager = new FileManager(fileName);
    }

    public void saveToFile(List<Task> tasks) {
        String text = tasks.stream()
                .map(task -> String.join("|",
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        String.valueOf(task.isCompleted())))
                .collect(Collectors.joining("\n"));
        fileManager.writeData(text);
    }

    public List<Task> loadFromFile() {
        String data = fileManager.readData();
        List<Task> tasks = Arrays.stream(data.split("\n"))
                .map(this::parseTask)
                .collect(Collectors.toList());
        return tasks;
    }

    private Task parseTask(String taskData) {
        String[] parts = taskData.split("\\|");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid task data: " + taskData);
        }
        String title = parts[0];
        String description = parts[1];
        String dueDate = parts[2];
        boolean isCompleted = Boolean.parseBoolean(parts[3]);
        Task task = new Task(title, description, dueDate);
        if (isCompleted) {
            task.markAsCompleted();
        }

        return task;
    }
}
