package creationalpatterns;

import creationalpatterns.users.User;

import java.util.Date;

public class Task {

    public enum Status{
        NEW,
        IN_PROGRESS,
        FINISHED,
        APPROVED
    }

    private final int id;
    private static int idCounter;
    private final String title;
    private final Date startDate;
    private final Date endDate;
    private final User actor;
    private int completeness;
    private Status status;
    private int planId;

    public Task(String title, Date startDate, Date endDate, User actor) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actor = actor;
        this.id = idCounter++;
        this.completeness = 0;
        status = Status.NEW;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public User getActor() {
        return actor;
    }

    public int getCompleteness() {
        return completeness;
    }

    public void setCompleteness(int completeness) {
        this.completeness = completeness;
        this.status = Status.IN_PROGRESS;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
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
