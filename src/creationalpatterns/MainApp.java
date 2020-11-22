package creationalpatterns;

import creationalpatterns.users.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        UserFactory userFactory = new UserFactoryImpl();
        Admin admin = (Admin) userFactory.createUser("admin", "1234", "admin");
        Admin secondAdmin = (Admin) userFactory.createUser("admin2", "12345", "admin");
        System.out.println(admin.equals(secondAdmin));
        Creator mom = (Creator) admin.createCreator("Mom", "234");
        Actor dad = (Actor) admin.createActor("Dad", "123");
        Actor son = (Actor) secondAdmin.createActor("Son", "000");

        Plan roomCleaning = mom.createPlan("Rooms cleaning");

        Task kitchenCleaning = mom.createTask("Kitchen cleaning", formatter.parse("2020-11-22"), formatter.parse("2020-11-23"), dad);
        kitchenCleaning.setPlanId(roomCleaning.getId());
        Task livingRoomCleaning = mom.createTask("Living room cleaning", formatter.parse("2020-11-22"), formatter.parse("2020-11-24"), son);
        livingRoomCleaning.setPlanId(roomCleaning.getId());

        System.out.println(roomCleaning);
        System.out.println(Database.getTasks());
        System.out.println("=========================");
        System.out.println(dad.getAllTasks());

        dad.sendTaskToApprove(kitchenCleaning.getId());
        System.out.println(dad.getAllTasks());

        mom.approveTask(kitchenCleaning.getId());
        System.out.println(dad.getAllTasks());
    }
}
