package core.User;

public class MemberUser extends User{
    String username, firstName, lastName, email, phone, address;
    Character middleInitial;
    UserType type;
    
    public MemberUser(String username, String password) {
        super(username, password, UserType.MEMBER);
        this.username = username;
        this.type = UserType.MEMBER;
    }

}
