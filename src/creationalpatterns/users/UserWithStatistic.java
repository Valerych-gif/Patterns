package creationalpatterns.users;

import creationalpatterns.Task;

import java.util.List;
import java.util.stream.Collectors;

public class UserWithStatistic implements User {

    User user;

    public UserWithStatistic(User user) {
        this.user = user;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = user.getAllTasks();
        showStatistic(tasks);
        return tasks;
    }

    private void showStatistic(List<Task> tasks) {
        System.out.printf("Quantity of new tasks: %d%nQuantity of tasks in progress: %d%nQuantity of finished tasks %d%nTotal %d%n",
                getTasks(tasks, Task.Status.NEW).size(),
                getTasks(tasks, Task.Status.IN_PROGRESS).size(),
                getTasks(tasks, Task.Status.FINISHED).size(),
                tasks.size()
        );
    }

    private List<Task> getTasks(List<Task> tasks, Task.Status status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public String getRole() {
        return null;
    }
}
