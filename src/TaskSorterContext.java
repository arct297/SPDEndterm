import java.util.List;

public class TaskSorterContext {
    private TaskSorter taskSorter;

    public void setTaskSorter(TaskSorter taskSorter) {
        this.taskSorter = taskSorter;
    }

    public void sortTasks(List<Task> tasks) {
        if (taskSorter != null) {
            taskSorter.sort(tasks);
        } else {
            System.out.println("System warning: sorting strategy not set.");
        }
    }
}
