package creationalpatterns.users;

public class UserFactoryImpl implements UserFactory{
    @Override
    public User createUser(String userName, String password, String userType) {
        return switch (userType.toLowerCase()) {
            case "admin" -> Admin.getInstance(userName, password);
            case "creator" -> new Creator(userName, password);
            case "actor" -> new Actor(userName, password);
            default -> throw new IllegalArgumentException("Illegal type of user - " + userType);
        };
    }
}
