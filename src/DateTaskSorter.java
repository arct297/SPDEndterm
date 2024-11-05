import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DateTaskSorter implements TaskSorter {
    @Override
    public void sort(List<Task> tasks) {
        Collections.sort(tasks, Comparator.comparing(Task::getDueDate));
    }
}
