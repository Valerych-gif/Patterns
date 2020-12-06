package ru.geekbrains.erp.users;

import ru.geekbrains.erp.DAO.UserDAO;
import ru.geekbrains.erp.Task;

import java.util.List;

public class Admin implements User {

    private static Admin instance;
    private final UserFactory userFactory;
    private final long id;
    private final String userName;
    private final String password;
    private final UserType userType;

    private final UserDAO userDAO;

    private Admin(User user) {
        this.userDAO = new UserDAO();
        this.userType = UserType.ADMIN;
        this.userFactory = new UserFactoryImpl();

        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }

    public static Admin getInstance(){
        if (instance == null){
            instance = (Admin) new UserDAO().getAllUsersByUserType(UserType.ADMIN).get(0);
        }
        return instance;
    }

    public static Admin getInstance(User user) {
        if (instance == null){
            instance = new Admin(user);
        }
        return instance;
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
        return userType;
    }

    public User createCreator(String userName, String password){
        User creator = userFactory.createUser(userName, password, UserType.CREATOR);
        userDAO.insertUser(creator);
        return creator;
    }

    public User createActor(String userName, String password){
        User actor = userFactory.createUser(userName, password, UserType.ACTOR);
        userDAO.insertUser(actor);
        return actor;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
