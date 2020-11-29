package creationalpatterns.users;

import creationalpatterns.database.Database;
import creationalpatterns.Task;

import java.util.List;

public class Admin implements User {

    private static Admin instance;
    private UserFactory userFactory;
    private int id;
    private String userName;
    private String password;
    private UserType userType;

    private static int idCounter;

    private Admin(String userName, String password, UserFactory userFactory) {
        this.id = idCounter++;
        this.userName = userName;
        this.password = password;
        this.userFactory=userFactory;
        this.userType = UserType.ADMIN;
    }

    public static Admin getInstance(String userName, String password, UserFactory userFactory){
        if (instance == null){
            instance = new Admin(userName, password, userFactory);
        }
        return instance;
    }

    @Override
    public List<Task> getAllTasks() {
        return null;
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
        Database.addUser(creator);
        return creator;
    }

    public User createActor(String userName, String password){
        User actor = userFactory.createUser(userName, password, UserType.ACTOR);
        Database.addUser(actor);
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
