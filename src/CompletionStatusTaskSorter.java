import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompletionStatusTaskSorter implements TaskSorter {
    @Override
    public void sort(List<Task> tasks) {
        Collections.sort(tasks, Comparator.comparing(Task::isCompleted));
    }
}
