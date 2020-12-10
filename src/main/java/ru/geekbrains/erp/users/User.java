package ru.geekbrains.erp.users;

import ru.geekbrains.erp.Task;

import java.util.List;

public interface User extends Cloneable{
    List<Task> getAllTasks();
    Long getId();
    String getUserName();
    String getPassword();
    UserType getUserType();
}
