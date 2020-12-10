package ru.geekbrains.erp.users;

public abstract class ImprovedUser implements User{
    User user;

    public ImprovedUser(User user) {
        this.user = user;
    }
}
