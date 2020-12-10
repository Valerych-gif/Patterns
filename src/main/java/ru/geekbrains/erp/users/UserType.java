package ru.geekbrains.erp.users;

public enum UserType {
    ADMIN("admin"),
    CREATOR("creator"),
    ACTOR("actor");

    private final String userTypeName;

    UserType(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }
}