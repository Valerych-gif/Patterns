package creationalpatterns;

public class Plan {

    public enum Status{
        NEW,
        IN_PROGRESS,
        FINISHED,
        APPROVED
    }

    private final int id;
    private static int idCounter;
    private final String title;
    private Status status;

    public Plan(String title) {
        this.title = title;
        this.id = idCounter++;
        status = Status.NEW;
    }

    public int getId() {
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
