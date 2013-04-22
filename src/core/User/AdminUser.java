package core.User;

public class AdminUser extends User {

    public AdminUser(String username, String password) {
        super(username, password, UserType.ADMIN);
    }

}
