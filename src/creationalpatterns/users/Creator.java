package creationalpatterns.users;

import creationalpatterns.Database;
import creationalpatterns.Plan;
import creationalpatterns.Task;

import java.util.Date;
import java.util.List;

public class Creator extends User {

    public Creator(String userName, String password) {
        super(userName, password);
        role = "CREATOR";
    }

    @Override
    public List<Task> getAllTasks() {
        return Database.getTasks();
    }

    public Plan createPlan(String title){
        Plan plan = new Plan(title);
        Database.addPlan(plan);
        return plan;
    }

    public Task createTask(String title, Date startDate, Date endDate, User actor){
        Task task = new Task(title, startDate, endDate, actor);
        Database.addTask(task);
        return task;
    }

    public void approvePlan(int id){
        List<Plan> plans = Database.getPlans();
        for (Plan plan : plans) {
            if (plan.getId() == id){
                plan.setStatus(Plan.Status.APPROVED);
                return;
            }
        }
    }

    public void approveTask(int id){
        List<Task> tasks = Database.getTasks();
        for (Task task : tasks) {
            if (task.getId() == id){
                task.setStatus(Task.Status.APPROVED);
                return;
            }
        }
    }

    public void deletePlan(int id){
        List<Plan> plans = Database.getPlans();
        for (Plan plan : plans) {
            if (plan.getId() == id){
                Database.deletePlan(plan);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Creator{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
