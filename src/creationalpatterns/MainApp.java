package creationalpatterns;

import creationalpatterns.users.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainApp {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        UserFactory userFactory = new UserFactoryImpl();
        Admin admin = (Admin) userFactory.createUser("admin", "1234", UserType.ADMIN);
        Admin secondAdmin = (Admin) userFactory.createUser("admin2", "12345", UserType.ADMIN);
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
        System.out.println(new UserWithStatistic(dad).getAllTasks());
        dad.setCompleteness(kitchenCleaning.getId(), 70);
        new UserWithStatistic(dad).showStatistic();
        dad.sendTaskToApprove(kitchenCleaning.getId());

        mom.approveTask(kitchenCleaning.getId());
        System.out.println(dad.getAllTasks());
        new UserWithStatistic(dad).showStatistic();
        new UserWithStatistic(mom).showStatistic();
    }
}
