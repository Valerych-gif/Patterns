package creationalpatterns.users;

import creationalpatterns.Task;

import java.util.List;

public interface User {
    List<Task> getAllTasks();
    String getRole();
}
