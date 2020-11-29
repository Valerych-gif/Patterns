package creationalpatterns.database;

import creationalpatterns.Plan;
import creationalpatterns.Task;
import creationalpatterns.users.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final List<User> users  = new ArrayList<>();
    private static final List<Plan> plans = new ArrayList<>();
    private static final List<Task> tasks = new ArrayList<>();

    public static void addPlan(Plan plan){
        plans.add(plan);
    }

    public static void deletePlan(Plan plan){
        plans.remove(plan);
    }

    public static void addTask(Task task){
        tasks.add(task);
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Plan> getPlans() {
        return plans;
    }

    public static List<Task> getTasks() {
        return tasks;
    }
}
