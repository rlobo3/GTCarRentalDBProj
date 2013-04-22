package core.User;

public class MemberUser extends User{

    public MemberUser(String username, String password) {
        super(username, password, UserType.MEMBER);
    }

}
