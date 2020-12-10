package ru.geekbrains.erp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class EntityDAO {

    private static final String DB_NAME = "erp";
    private static final String DB_USER = "erp";
    private static final String DB_PASSWORD = "12345";

    protected Connection connection;

    public EntityDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASSWORD + "&serverTimezone=UTC");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
