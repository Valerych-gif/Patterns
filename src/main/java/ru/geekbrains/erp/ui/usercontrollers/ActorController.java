package ru.geekbrains.erp.ui.usercontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import ru.geekbrains.erp.Task;
import ru.geekbrains.erp.ui.Controller;
import ru.geekbrains.erp.users.User;

public class ActorController extends Controller {

    @FXML
    private TextArea data;

    public ActorController() {
        super();
    }

    @Override
    public void initUsersPanel(User user) {
        super.initUsersPanel(user);
        data.setText(tasksTextList.toString());
    }
}
