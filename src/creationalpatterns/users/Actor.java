package creationalpatterns.users;

import creationalpatterns.Database;
import creationalpatterns.Task;

import java.util.List;
import java.util.stream.Collectors;

public class Actor implements User {

    private int id;
    private String userName;
    private String password;
    private String role;

    private static int idCounter;

    public Actor(String userName, String password) {
        this.id = idCounter++;
        this.userName = userName;
        this.password = password;
        role = "ACTOR";
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = Database.getTasks();
        return tasks.stream()
                .filter(task -> task.getActor().equals(this))
                .collect(Collectors.toList());

    }

    @Override
    public String getRole() {
        return role;
    }

    public void sendTaskToApprove(int id){
        List<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            if (task.getId()== id){
                task.setCompleteness(100);
                task.setStatus(Task.Status.FINISHED);
                return;
            }
        }
    }

    public void setCompleteness(int id, int completeness){
        List<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            if (task.getId() == id){
                task.setCompleteness(completeness);
                task.setStatus(Task.Status.IN_PROGRESS);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
