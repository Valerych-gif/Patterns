package ru.geekbrains.erp.DAO;

import ru.geekbrains.erp.users.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends EntityDAO {

    private static final String USER_TABLE_NAME = "users";

    private static final String ID_COLUMN_NAME = "id";
    private static final String USERNAME_COLUMN_NAME = "username";
    private static final String PASSWORD_COLUMN_NAME = "password";
    private static final String USER_TYPE_COLUMN_NAME = "user_type";

    public UserDAO() {
        super();
    }

    public void insertUser(User user) {
        if (user == null || getUserByUsername(user.getUserName()) != null) {
            return;
        }
        String sql = "INSERT INTO `" + USER_TABLE_NAME + "` " +
                "(`" + USERNAME_COLUMN_NAME + "`, `" + PASSWORD_COLUMN_NAME + "`, `" + USER_TYPE_COLUMN_NAME + "`)" +
                " VALUES " +
                "('" + user.getUserName() + "', '" + user.getPassword() + "', '" + user.getUserType().getUserTypeName() + "')";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM `" + USER_TABLE_NAME + "` WHERE `" + USERNAME_COLUMN_NAME + "`='" + username + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            user = getUserFromRS(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User getUserFromRS(ResultSet rs) throws SQLException {
        User user = null;
        UserFactory userFactory = new UserFactoryImpl();
        if (rs.next()) {
            String userTypeName = rs.getString(USER_TYPE_COLUMN_NAME);
            user = switch (userTypeName) {
                case "admin" -> Admin.getInstance();
                case "creator" -> userFactory.getUser(
                        rs.getLong(ID_COLUMN_NAME),
                        rs.getString(USERNAME_COLUMN_NAME),
                        rs.getString(PASSWORD_COLUMN_NAME),
                        UserType.CREATOR);
                case "actor" -> userFactory.getUser(
                        rs.getLong(ID_COLUMN_NAME),
                        rs.getString(USERNAME_COLUMN_NAME),
                        rs.getString(PASSWORD_COLUMN_NAME),
                        UserType.ACTOR
                );
                default -> throw new TypeNotPresentException(userTypeName, new Throwable());
            };
        }
        return user;
    }

    public List<User> getAllUsersByUserType(UserType userType) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM `" + USER_TABLE_NAME + "` WHERE `" + USER_TYPE_COLUMN_NAME + "`='" + userType.getUserTypeName() + "';";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            if (userType.equals(UserType.ADMIN)&&rs.next()) {
                User admin = Admin.getInstance(new SystemUser(
                        rs.getLong(ID_COLUMN_NAME),
                        rs.getString(USERNAME_COLUMN_NAME),
                        rs.getString(PASSWORD_COLUMN_NAME)
                ));
                users.add(admin);
                return users;
            }
            UserFactory userFactory = new UserFactoryImpl();
            while (rs.next()) {
                if (rs.getString(USER_TYPE_COLUMN_NAME).equals(userType.getUserTypeName())) {
                    users.add(userFactory.getUser(
                            rs.getLong(ID_COLUMN_NAME),
                            rs.getString(USERNAME_COLUMN_NAME),
                            rs.getString(PASSWORD_COLUMN_NAME),
                            userType
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(long id) {
        User user = null;
        String sql = "SELECT * FROM `" + USER_TABLE_NAME + "` WHERE `" + ID_COLUMN_NAME + "`='" + id + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            user = getUserFromRS(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
