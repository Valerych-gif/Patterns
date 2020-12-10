package ru.geekbrains.erp.users;

import ru.geekbrains.erp.Task;

import java.util.List;
import java.util.stream.Collectors;

public class UserWithStatistic extends ImprovedUser {

    public UserWithStatistic(User user) {
        super(user);
    }

    @Override
    public List<Task> getAllTasks() {
        return user.getAllTasks();
    }

    public void showStatistic() {
        List<Task> tasks = getAllTasks();
        System.out.printf("%nStatistic for %s%nNew tasks: %d%nTasks in progress: %d%nFinished tasks: %d%nApproved tasks: %d%nTotal: %d%n",
                user.getUserName(),
                getTasks(tasks, Task.Status.NEW).size(),
                getTasks(tasks, Task.Status.IN_PROGRESS).size(),
                getTasks(tasks, Task.Status.FINISHED).size(),
                getTasks(tasks, Task.Status.APPROVED).size(),
                tasks.size()
        );
    }

    private List<Task> getTasks(List<Task> tasks, Task.Status status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public String getUserName() {
        return user.getUserName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public UserType getUserType() {
        return user.getUserType();
    }
}
