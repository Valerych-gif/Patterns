package creationalpatterns.users;

public interface UserFactory {
    User createUser(String userName, String password, UserType userType);
}