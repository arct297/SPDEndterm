import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;
    private TaskManager taskManager;

    public MainMenu() {
        scanner = new Scanner(System.in);
        taskManager = TaskManager.getInstance();
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Display All Tasks");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    markTaskAsDone();
                    break;
                case 4:
                    taskManager.displayAllTasks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task due date (yyyy-MM-dd): ");
        String dueDate = scanner.nextLine();
        taskManager.addTask(title, description, dueDate);
    }


    private void editTask() {
        System.out.print("Enter title of task to edit: ");
        String title = scanner.nextLine();
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();
        taskManager.editTask(title, newTitle, newDescription);
    }

    private void markTaskAsDone() {
        System.out.print("Enter title of task to mark as done: ");
        String title = scanner.nextLine();
        taskManager.markTaskAsDone(title);
    }
}
