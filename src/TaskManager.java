import java.util.ArrayList;
import java.util.List;

public class TaskManager implements Subject {
    private static TaskManager instance;
    private List<Task> tasks;
    private List<Observer> observers;

    private TaskManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void addTask(String title, String description, String dueDate) {
        Task newTask = new Task(title, description, dueDate);
        tasks.add(newTask);
        System.out.println("Task added successfully.");
        notifyObservers("New task added: " + title);
    }


    public void editTask(String title, String newTitle, String newDescription) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                System.out.println("Task updated successfully.");
                notifyObservers("Task updated: " + title + " to " + newTitle);
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
                notifyObservers("Task completed: " + title);
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
            System.out.println("Due Date: " + task.getDueDate());
            System.out.println("Status: " + (task.isCompleted() ? "Completed" : "Not Completed"));
            System.out.println("---------------");
        }
    }
}
