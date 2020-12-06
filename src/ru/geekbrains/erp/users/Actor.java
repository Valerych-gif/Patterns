package ru.geekbrains.erp.users;

import ru.geekbrains.erp.DAO.TaskDAO;
import ru.geekbrains.erp.Task;

import java.util.List;
import java.util.stream.Collectors;

public class Actor implements User {

    private long id;
    private String userName;
    private String password;
    private UserType userType;

    private TaskDAO taskDAO;

    public Actor(String userName, String password) {
        this.id = -1;
        this.userName = userName;
        this.password = password;
        this.userType = UserType.ACTOR;
        this.taskDAO = new TaskDAO();
    }

    public Actor(long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = UserType.ACTOR;
        this.taskDAO = new TaskDAO();
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = taskDAO.getAllTasks();
        return tasks.stream()
                .filter(task -> task.getActor().getId().equals(this.getId()))
                .collect(Collectors.toList());

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

    public void sendTaskToApprove(long id){
        Task task = taskDAO.getTaskById(id);
        task.setStatus(Task.Status.FINISHED);
        task.setCompleteness(100);
        System.out.println("Update: " + task);
        taskDAO.updateTask(task);
    }

    public void setCompleteness(long id, int completeness){
        Task task = taskDAO.getTaskById(id);
        task.setCompleteness(completeness);
        taskDAO.updateTask(task);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
