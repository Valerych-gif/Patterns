package ru.geekbrains.erp.users;

import ru.geekbrains.erp.Task;

import java.util.List;

public class SystemUser implements User{

    private long id;
    private String userName;
    private String password;

    public SystemUser(long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public List<Task> getAllTasks() {
        return null;
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
        return null;
    }
}
