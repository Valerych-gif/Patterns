package ru.geekbrains.erp.users;

import ru.geekbrains.erp.DAO.PlanDAO;
import ru.geekbrains.erp.DAO.TaskDAO;
import ru.geekbrains.erp.Plan;
import ru.geekbrains.erp.Task;

import java.util.Date;
import java.util.List;

public class Creator implements User {

    private final long id;
    private final String userName;
    private final String password;
    private final UserType userType;

    private final TaskDAO taskDAO;
    private final PlanDAO planDAO;

    public Creator(String userName, String password) {
        this.id = -1;
        this.userName = userName;
        this.password = password;
        this.userType = UserType.CREATOR;
        this.taskDAO = new TaskDAO();
        this.planDAO = new PlanDAO();
    }

    public Creator(long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = UserType.CREATOR;
        this.taskDAO = new TaskDAO();
        this.planDAO = new PlanDAO();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
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



    public Task createTask(String title, String startDate, String endDate, User actor) {
        Task task = new Task(title, startDate, endDate, actor);
        taskDAO.insertTask(task);
        task = taskDAO.getTaskByTaskTitle(title);
        return task;
    }

    public void approveTask(long id) {
        Task task = taskDAO.getTaskById(id);
        task.setStatus(Task.Status.APPROVED);
        task.setCompleteness(100);
        taskDAO.updateTask(task);
    }

    public void deleteTask(long id){
        taskDAO.deleteTaskById(id);
    }

    public Plan createPlan(String title) {
        Plan plan = new Plan(title);
        planDAO.insertPlan(plan);
        plan = planDAO.getPlanByTitle(title);
        return plan;
    }

    public void approvePlan(long id) {
        Plan plan = planDAO.getPlanById(id);
        plan.setStatus(Plan.Status.APPROVED);
        planDAO.updatePlan(plan);
    }

    public void deletePlan(int id) {
        planDAO.deletePlan(id);
    }

    @Override
    public String toString() {
        return "Creator{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
