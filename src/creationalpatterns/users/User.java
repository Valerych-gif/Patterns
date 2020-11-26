package creationalpatterns.users;

import creationalpatterns.Task;

import java.util.List;

abstract public class User {
    protected int id;
    protected String userName;
    protected String password;
    protected String role;

    protected static int idCounter;

    public User(String userName, String password) {
        this.id = idCounter++;
        this.userName = userName;
        this.password = password;
    }

    public abstract List<Task> getAllTasks();

    public String getRole(){
        return role;
    }
}
