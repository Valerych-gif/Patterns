package creationalpatterns.users;

public class UserFactoryImpl implements UserFactory{
    @Override
    public User createUser(String userName, String password, UserType userType) {
        return switch (userType) {
            case ADMIN -> Admin.getInstance(userName, password, this);
            case CREATOR -> new Creator(userName, password);
            case ACTOR -> new Actor(userName, password);
        };
    }
}
