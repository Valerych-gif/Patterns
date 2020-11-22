package creationalpatterns.users;

public interface UserFactory {
    User createUser(String userName, String password, String userType);
}