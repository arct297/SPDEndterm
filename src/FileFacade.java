import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class FileFacade {
    private final FileManager fileManager;

    public FileFacade(String fileName) {
        this.fileManager = new FileManager(fileName);
    }

    public void saveTasks(List<Task> tasks) {
        String data = tasks.stream()
                .map(task -> String.join("|",
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        String.valueOf(task.isCompleted())))
                .collect(Collectors.joining("\n"));
        fileManager.writeData(data);
    }

    public List<Task> loadTasks() {
        String data = fileManager.readData();
        if (data.isEmpty()) {
            return List.of();
        }

        return Arrays.stream(data.split("\n"))
                .map(this::parseTask)
                .collect(Collectors.toList());
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
