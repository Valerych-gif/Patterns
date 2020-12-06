package ru.geekbrains.erp;

import ru.geekbrains.erp.DAO.TaskDAO;
import ru.geekbrains.erp.DAO.UserDAO;
import ru.geekbrains.erp.users.User;

import java.util.Date;

public class Task {

    public enum Status{
        NEW("new"),
        IN_PROGRESS("in progress"),
        FINISHED("finished"),
        APPROVED("approved");

        String statusName;

        Status(String statusName) {
            this.statusName = statusName;
        }

        public String getStatusName() {
            return statusName;
        }
    }

    private final long id;
    private final String title;
    private final String startDate;
    private final String endDate;
    private final User actor;
    private int completeness;
    private Status status;
    private long planId;

    private final TaskDAO taskDAO = new TaskDAO();

    public Task(String title, String startDate, String endDate, User actor) {
        this.id = -1;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actor = actor;
        this.completeness = 0;
        status = Status.NEW;
    }

    public Task(long id, String title, String startDate, String endDate, User actor) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actor = actor;
        this.completeness = 0;
        status = Status.NEW;
    }

    public Task(long id, String title, String startDate, String endDate, long actorId, int completeness, String status, long planId) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        UserDAO userDAO = new UserDAO();
        this.actor = userDAO.getUserById(actorId);
        this.completeness = completeness;
        for (Task.Status s : Task.Status.values()) {
            if (s.getStatusName().equals(status)){
                this.status = s;
                break;
            }
        }
        this.planId = planId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public User getActor() {
        return actor;
    }

    public int getCompleteness() {
        return completeness;
    }

    public void setCompleteness(int completeness) {
        this.completeness = Math.min(completeness, 100);
        if (completeness!=100) this.status = Status.IN_PROGRESS;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "\nTask{" +
                "\n\tid=" + id +
                ", \n\ttitle='" + title + '\'' +
                ", \n\tstartDate=" + startDate +
                ", \n\tendDate=" + endDate +
                ", \n\tactor=" + actor +
                ", \n\tcompleteness=" + completeness +
                ", \n\tstatus=" + status +
                ", \n\tplanId=" + planId +
                '}';
    }
}
