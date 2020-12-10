package ru.geekbrains.erp.ui;

import ru.geekbrains.erp.DAO.UserDAO;
import ru.geekbrains.erp.Task;
import ru.geekbrains.erp.users.User;

public abstract class Controller {
    protected User user;
    protected UserDAO userDAO;

    protected StringBuilder tasksTextList;

    public Controller() {
        this.userDAO = new UserDAO();
    }


    public void initUsersPanel(User user) {
        this.user = user;
        tasksTextList = new StringBuilder().append(user.getUserName());
        for (Task task : user.getAllTasks()) {
            tasksTextList
                    .append("\n\nTitle: ")
                    .append(task.getTitle())
                    .append("\nStart date: ")
                    .append(task.getStartDate())
                    .append("\nEnd date: ")
                    .append(task.getEndDate())
                    .append("\nCompleteness: ")
                    .append(task.getCompleteness())
                    .append("\nStatus: ")
                    .append(task.getStatus().getStatusName().toUpperCase());

        }
    }
}
