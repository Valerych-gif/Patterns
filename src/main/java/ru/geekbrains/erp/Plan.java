package ru.geekbrains.erp;

public class Plan {

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

    private long id;
    private final String title;
    private Status status;

    public Plan(String title) {
        this.title = title;
        status = Status.NEW;
    }

    public Plan(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
