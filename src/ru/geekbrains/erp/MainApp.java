package ru.geekbrains.erp;

import ru.geekbrains.erp.users.Actor;
import ru.geekbrains.erp.users.Admin;
import ru.geekbrains.erp.users.Creator;
import ru.geekbrains.erp.users.UserWithStatistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainApp {


    public static void main(String[] args) {

        Admin admin = Admin.getInstance();
        Creator mom = (Creator) admin.createCreator("Mom", "234");
        Actor dad = (Actor) admin.createActor("Dad", "123");
        Actor son = (Actor) admin.createActor("Son", "000");

        Plan roomCleaning = mom.createPlan("Rooms cleaning");

        Task kitchenCleaning = mom.createTask("Kitchen cleaning", "2020-11-22", "2020-11-23", dad);
        kitchenCleaning.setPlanId(roomCleaning.getId());
        Task livingRoomCleaning = mom.createTask("Living room cleaning", "2020-11-22", "2020-11-24", son);
        livingRoomCleaning.setPlanId(roomCleaning.getId());

        System.out.println("=========================");
        System.out.println(dad.getAllTasks());
        dad.setCompleteness(kitchenCleaning.getId(), 70);
        new UserWithStatistic(dad).showStatistic();
        dad.sendTaskToApprove(kitchenCleaning.getId());

        mom.approveTask(kitchenCleaning.getId());
        System.out.println(dad.getAllTasks());

    }
}
