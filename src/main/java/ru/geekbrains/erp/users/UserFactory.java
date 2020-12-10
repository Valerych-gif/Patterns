package ru.geekbrains.erp.users;

public interface UserFactory {
    User createUser(String userName, String password, UserType userType);
    User getUser(Long id, String userName, String password, UserType userType);
}