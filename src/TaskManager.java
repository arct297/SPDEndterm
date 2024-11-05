import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskManager{
    private static TaskManager instance;
    private List<Task> tasks;
    private FileFacade fileFacade;

    private TaskSorterContext taskSorterClient;

    private TaskManager() {
        tasks = new ArrayList<>();
        fileFacade = new FileFacade("tasks.txt");
        loadTasks();
        this.taskSorterClient = new TaskSorterContext();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addTask(String title, String description, String dueDate) {
        if (!isUniqueTask(title)) {
            System.out.println("Task with such title already exists!");
            return;
        }
        Task newTask = new Task(title, description, dueDate);
        tasks.add(newTask);
        saveTasks();
        System.out.println("Task added successfully.");
    }

    public void saveTasks() {
        fileFacade.saveTasks(tasks);
    }

    public void loadTasks() {
        List<Task> loadedTasks = fileFacade.loadTasks();
        tasks = new ArrayList<>(loadedTasks);
    }


    public void editTask(String title, String newTitle, String newDescription, String dueDate) {
        if (isUniqueTask(title)) {
            System.out.println("Task with such title does not exist!");
            return;
        }
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                task.setDueDate(dueDate);
                System.out.println("Task updated successfully.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void markTaskAsDone(String title) {
        if (isUniqueTask(title)) {
            System.out.println("Task with such title does not exist!");
            return;
        }
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
            System.out.println("Due Date: " + task.getDueDate());
            System.out.println("Status: " + (task.isCompleted() ? "Completed" : "Not Completed"));
            System.out.println("---------------");
        }
    }

    public void sortTasks(String sortingWay) {
        if (Objects.equals(sortingWay, "date")) {
            taskSorterClient.setTaskSorter(new DateTaskSorter());
        } else if (Objects.equals(sortingWay, "completing")) {
            taskSorterClient.setTaskSorter(new CompletionStatusTaskSorter());
        } else if (Objects.equals(sortingWay, "title")) {
            taskSorterClient.setTaskSorter(new TitleTaskSorter());
        } else {
            System.out.println("Wrong task sorting way! Try again!");
            return;
        }

        taskSorterClient.sortTasks(this.tasks);
        System.out.println("Tasks sorted!");
    }

    public void deleteTask(String title) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                tasks.remove(task);
                found = true;
                System.out.println("Task deleted successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Task not found.");
        }
    }

    public boolean isUniqueTask(String title) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return false;
            }
        }
        return true;
    }
}
