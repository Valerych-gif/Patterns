package creationalpatterns.database;

import creationalpatterns.Plan;
import creationalpatterns.Task;
import creationalpatterns.users.User;
import creationalpatterns.users.UserFactory;
import creationalpatterns.users.UserFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class DatabaseState {
    private final List<User> usersStorage;
    private final List<Plan> plansStorage;
    private final List<Task> tasksStorage;

    public DatabaseState(List<User> users, List<Plan> plans, List<Task> tasks) {
        UserFactory userFactory = new UserFactoryImpl();
        this.usersStorage = new ArrayList<>();
        this.plansStorage = new ArrayList<>();
        this.tasksStorage = new ArrayList<>();
        for (User user : users) {
            User userToAdd = userFactory.createUser(user.getUserName(), user.getPassword(), user.getUserType());
            usersStorage.add(userToAdd);
        }

        for (Plan plan : plans) {
            plansStorage.add(new Plan(plan.getTitle()));
        }

        for (Task task : tasks) {
            tasksStorage.add(new Task(task.getTitle(), task.getStartDate(), task.getEndDate(), task.getActor()));
        }
    }

    public DatabaseState getState(){
        return this;
    }

    @Override
    public String toString() {
        return "DatabaseState{" +
                "users=" + usersStorage +
                ", plans=" + plansStorage +
                ", tasks=" + tasksStorage +
                '}';
    }
}
