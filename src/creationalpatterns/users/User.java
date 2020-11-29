package creationalpatterns.users;

import creationalpatterns.Task;

import java.util.List;

public interface User extends Cloneable{
    List<Task> getAllTasks();
    String getUserName();
    String getPassword();
    UserType getUserType();
}
