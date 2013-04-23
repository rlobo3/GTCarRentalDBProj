package core.User;

public class EmployeeUser extends User {
    String username;
    UserType type;

    public EmployeeUser(String username, String password) {
        super(username, password, UserType.EMPLOYEE);
        this.username = username;
        this.type = UserType.MEMBER;
    }

}
