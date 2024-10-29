import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {
    private static TaskManager instance;
    private List<Task> tasks;

    private TaskManager() {
        tasks = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        tasks.add(newTask);
        System.out.println("Task added successfully.");
    }

    public void editTask(String title, String newTitle, String newDescription) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                System.out.println("Task updated successfully.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void markTaskAsDone(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markAsCompleted();
                System.out.println("Task marked as completed.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void displayAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task task : tasks) {
            System.out.println("Title: " + task.getTitle());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Status: " + (task.isCompleted() ? "Completed" : "Not Completed"));
            System.out.println("Due Date: " + task.getDueDate()); // Due Date is assumed in Task class
            System.out.println("---------------");
        }
    }

    // Method to sort tasks using a given SortStrategy
    public void sortTasks(SortStrategy strategy) {
        Collections.sort(tasks, strategy);
        System.out.println("Tasks sorted successfully.");
    }
}
