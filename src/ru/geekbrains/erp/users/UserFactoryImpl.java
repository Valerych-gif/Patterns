package ru.geekbrains.erp.users;

import ru.geekbrains.erp.DAO.UserDAO;

public class UserFactoryImpl implements UserFactory {

    UserDAO userDAO = new UserDAO();

    @Override
    public User createUser(String userName, String password, UserType userType) {
        User user = null;
        switch (userType) {
            case CREATOR:
                user = new Creator(userName, password);
                break;
            case ACTOR:
                user = new Actor(userName, password);
                break;
        }
        userDAO.insertUser(user);
        user = userDAO.getUserByUsername(userName);
        return user;
    }

    @Override
    public User getUser(Long id, String userName, String password, UserType userType) {
        User user = null;
        switch (userType) {
            case CREATOR:
                user = new Creator(id, userName, password);
                break;
            case ACTOR:
                user = new Actor(id, userName, password);
                break;
        }
        return user;
    }
}
