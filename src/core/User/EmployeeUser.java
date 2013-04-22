package core.User;

public class EmployeeUser extends User {

    public EmployeeUser(String username, String password) {
        super(username, password, UserType.EMPLOYEE);
    }

}
