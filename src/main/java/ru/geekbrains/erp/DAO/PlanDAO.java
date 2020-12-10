package ru.geekbrains.erp.DAO;

import ru.geekbrains.erp.Plan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlanDAO extends EntityDAO{

    private static final String PLANS_TABLE_NAME = "plans";

    private static final String ID_COLUMN_NAME = "id";
    private static final String TITLE_COLUMN_NAME = "title";
    private static final String STATUS_COLUMN_NAME = "status";

    public PlanDAO() {
        super();
    }

    public void insertPlan(Plan plan){
        if (plan == null || plan.getTitle()== null) {
            return;
        }
        String sql = "INSERT INTO `" + PLANS_TABLE_NAME + "` " +
                "(`" + TITLE_COLUMN_NAME + "`, `" + STATUS_COLUMN_NAME + "`)" +
                " VALUES " +
                "('" + plan.getTitle() + "', '" + Plan.Status.NEW.getStatusName() + "')";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Plan getPlanById(long id) {
        return null;
    }

    public Plan getPlanByTitle(String title) {
        Plan plan = null;
        String sql = "SELECT * FROM `" + PLANS_TABLE_NAME + "` WHERE `" + TITLE_COLUMN_NAME + "`='" + title + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                plan = new Plan(
                        rs.getLong(ID_COLUMN_NAME),
                        rs.getString(TITLE_COLUMN_NAME));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return plan;
    }

    public void updatePlan(Plan plan) {

    }

    public void deletePlan(int id) {

    }
}
