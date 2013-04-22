package core.User;

public enum UserType {
    MEMBER("Georgia Tech Students/Faculty"),
    EMPLOYEE("GTCR Employee"),
    ADMIN("Administrator");
    
    private UserType(final String s) {
        this.s = s;
    }
    private final String s;
    
    @Override
    public String toString() {
        return s;
    }
}