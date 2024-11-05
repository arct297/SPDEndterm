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
            System.out.println("\n");
            System.out.println("Choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Display All Tasks");
            System.out.println("5. Sort tasks");
            System.out.println("6. Save tasks");
            System.out.println("7. Delete task");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    sortTasks();
                    break;
                case 6:
                    taskManager.saveTasks();
                    System.out.println("Tasks saved successfully.");
                    break;
                case 7:
                    deleteTask();
                    break;
                case 8:
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
        boolean isCorrectDate = checkDueDateFormat(dueDate);
        if (!isCorrectDate) {
            System.out.println("You entered incorrect date! Correct format of date is YYYY-MM-DD");
            return;
        }
        taskManager.addTask(title, description, dueDate);
    }

    private boolean checkDueDateFormat(String dueDate) {
        if (dueDate.length() != 10) {
            return false;
        }

        if (dueDate.charAt(4) != '-' || dueDate.charAt(7) != '-') {
            return false;
        }

        try {
            int year = Integer.parseInt(dueDate.substring(0, 4));
            int month = Integer.parseInt(dueDate.substring(5, 7));
            int day = Integer.parseInt(dueDate.substring(8, 10));

            if (month < 1 || month > 12) {
                return false;
            }

            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            if (month == 2 && isLeapYear(year)) {
                return day >= 1 && day <= 29;
            } else {
                return day >= 1 && day <= daysInMonth[month - 1];
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private void editTask() {
        System.out.print("Enter title of task to edit: ");
        String title = scanner.nextLine();
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();
        System.out.print("Enter task new due date (yyyy-MM-dd): ");
        String dueDate = scanner.nextLine();
        boolean isCorrectDate = checkDueDateFormat(dueDate);
        if (!isCorrectDate) {
            System.out.println("You entered incorrect date! Correct format of date is YYYY-MM-DD");
            return;
        }
        taskManager.editTask(title, newTitle, newDescription, dueDate);
    }

    private void markTaskAsDone() {
        System.out.print("Enter title of task to mark as done: ");
        String title = scanner.nextLine();
        taskManager.markTaskAsDone(title);
    }

    private void sortTasks() {
        System.out.print("Enter the way of sorting ('date', 'title' or 'completing'): ");
        String sortingWay = scanner.nextLine();
        taskManager.sortTasks(sortingWay);
    }

    private void deleteTask() {
        System.out.print("Enter title of task to delete: ");
        String taskTitle = scanner.nextLine();
        taskManager.deleteTask(taskTitle);
    }
}
