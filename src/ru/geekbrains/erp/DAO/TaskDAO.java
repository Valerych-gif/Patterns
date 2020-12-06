package ru.geekbrains.erp.DAO;

import ru.geekbrains.erp.Plan;
import ru.geekbrains.erp.Task;
import ru.geekbrains.erp.users.User;
import ru.geekbrains.erp.users.UserFactory;
import ru.geekbrains.erp.users.UserFactoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO extends EntityDAO {

    private static final String TASK_TABLE_NAME = "tasks";

    private static final String ID_COLUMN_NAME = "id";
    private static final String TITLE_COLUMN_NAME = "title";
    private static final String START_DATE_COLUMN_NAME = "start_date";
    private static final String END_DATE_COLUMN_NAME = "end_date";
    private static final String ACTOR_ID_COLUMN_NAME = "actor_id";
    private static final String COMPLETENESS_COLUMN_NAME = "completeness";
    private static final String STATUS_COLUMN_NAME = "status";
    private static final String PLAN_ID_COLUMN_NAME = "plan_id";


    public TaskDAO() {
        super();
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM `" + TASK_TABLE_NAME +"`";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getLong(ID_COLUMN_NAME),
                        rs.getString(TITLE_COLUMN_NAME),
                        rs.getString(START_DATE_COLUMN_NAME),
                        rs.getString(END_DATE_COLUMN_NAME),
                        rs.getLong(ACTOR_ID_COLUMN_NAME),
                        rs.getInt(COMPLETENESS_COLUMN_NAME),
                        rs.getString(STATUS_COLUMN_NAME),
                        rs.getLong(PLAN_ID_COLUMN_NAME)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tasks;
    }

    public Task getTaskById(long id) {
        Task task = null;
        String sql = "SELECT * FROM `" + TASK_TABLE_NAME + "` WHERE `" + ID_COLUMN_NAME + "`='" + id + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                task = new Task(
                        rs.getLong(ID_COLUMN_NAME),
                        rs.getString(TITLE_COLUMN_NAME),
                        rs.getString(START_DATE_COLUMN_NAME),
                        rs.getString(END_DATE_COLUMN_NAME),
                        rs.getLong(ACTOR_ID_COLUMN_NAME),
                        rs.getInt(COMPLETENESS_COLUMN_NAME),
                        rs.getString(STATUS_COLUMN_NAME),
                        rs.getLong(PLAN_ID_COLUMN_NAME));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return task;
    }

    public void insertTask(Task task) {
        if (task == null||getTaskByTaskTitle(task.getTitle()) != null) {
            return;
        }
        String sql = "INSERT INTO `" + TASK_TABLE_NAME + "` " +
                "(`" +
                TITLE_COLUMN_NAME + "`, `" +
                START_DATE_COLUMN_NAME + "`, `" +
                END_DATE_COLUMN_NAME + "`, `" +
                ACTOR_ID_COLUMN_NAME + "`, `" +
                COMPLETENESS_COLUMN_NAME + "`, `" +
                STATUS_COLUMN_NAME + "`)" +
                " VALUES " +
                "('" +
                task.getTitle() + "', '" +
                task.getStartDate() + "', '" +
                task.getEndDate() + "', '" +
                task.getActor().getId() + "', '" +
                0 + "', '" +
                Task.Status.NEW.getStatusName() + "')";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task getTaskByTaskTitle(String title) {
        Task task = null;
        String sql = "SELECT * FROM `" + TASK_TABLE_NAME + "` WHERE `" + TITLE_COLUMN_NAME + "`='" + title + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                task = new Task(
                        rs.getLong(ID_COLUMN_NAME),
                        rs.getString(TITLE_COLUMN_NAME),
                        rs.getString(START_DATE_COLUMN_NAME),
                        rs.getString(END_DATE_COLUMN_NAME),
                        rs.getLong(ACTOR_ID_COLUMN_NAME),
                        rs.getInt(COMPLETENESS_COLUMN_NAME),
                        rs.getString(STATUS_COLUMN_NAME),
                        rs.getLong(PLAN_ID_COLUMN_NAME));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return task;
    }

    public void updateTask(Task task) {
        if (task == null) {
            return;
        }
        String sql = "UPDATE `" + TASK_TABLE_NAME + "` " +
                "SET `" +
                TITLE_COLUMN_NAME + "` = '" + task.getTitle() + "', `" +
                START_DATE_COLUMN_NAME + "` = '" + task.getStartDate() + "', `" +
                END_DATE_COLUMN_NAME + "` = '" + task.getEndDate() + "', `" +
                ACTOR_ID_COLUMN_NAME + "` = '" + task.getActor().getId() + "', `" +
                COMPLETENESS_COLUMN_NAME + "` = '" + task.getCompleteness() + "', `" +
                STATUS_COLUMN_NAME + "` = '" + task.getStatus().getStatusName() + "', `" +
                PLAN_ID_COLUMN_NAME + "` = '" + task.getPlanId() + "'" +
                " WHERE " +
                "`" + ID_COLUMN_NAME + "`='" + task.getId() +"'";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTaskById(long id) {

    }
}
