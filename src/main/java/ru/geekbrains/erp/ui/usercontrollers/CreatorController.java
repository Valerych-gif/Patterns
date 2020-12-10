package ru.geekbrains.erp.ui.usercontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import ru.geekbrains.erp.ui.Controller;
import ru.geekbrains.erp.users.User;

public class CreatorController extends Controller {

    @FXML
    private TextArea data;

    public CreatorController() {
        super();
    }

    @Override
    public void initUsersPanel(User user) {
        super.initUsersPanel(user);
        data.setText(tasksTextList.toString());
    }
}
