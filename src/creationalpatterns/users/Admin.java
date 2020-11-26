package creationalpatterns.users;

import creationalpatterns.Database;
import creationalpatterns.Task;

import java.util.List;

public class Admin extends User {

    private static Admin instance;
    UserFactory userFactory;

    private Admin(String userName, String password, UserFactory userFactory) {
        super(userName, password);
        this.userFactory=userFactory;
        role = "ADMIN";
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
