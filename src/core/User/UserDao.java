package core.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;

public class UserDao {
    DBConnection connection = new DBConnection();
         
    public User addUser(String username, String password, UserType type) {
        if(isUsernameExistent(username)) {
            return null;
        }
        else {
            Connection conn = connection.createConnection();
            try {
                if(type.equals(UserType.ADMIN)){
                    String statement = "INSERT INTO Administrator(Username,Pass) VALUES(?,?)";
                    PreparedStatement prep = conn.prepareStatement(statement);
                    prep.setString(1, username);
                    prep.setString(2, password);
                    prep.executeUpdate();
                    prep.close();
                    connection.closeConnection(conn);
                    return new AdminUser(username, password);
                }
                else if(type.equals(UserType.EMPLOYEE)){
                    String statement = "INSERT INTO Employee(Username,Pass) VALUES(?,?)";
                    PreparedStatement prep = conn.prepareStatement(statement);
                    prep.setString(1, username);
                    prep.setString(2, password);
                    prep.executeUpdate();
                    prep.close();
                    connection.closeConnection(conn);
                    return new EmployeeUser(username, password);
                }
                else if(type.equals(UserType.MEMBER)){
                    String statement = "INSERT INTO Member(Username,Pass) VALUES(?,?)";
                    PreparedStatement prep = conn.prepareStatement(statement);
                    prep.setString(1, username);
                    prep.setString(2, password);
                    prep.executeUpdate();
                    prep.close();
                    connection.closeConnection(conn);
                    return new MemberUser(username, password);
                }
            }
            catch (SQLException e) {}
            connection.closeConnection(conn);
            return null;
        }
    }
    
    public boolean isUsernameExistent(String username) {
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT Username from Administrator union " +
            		"SELECT Username from Employee union SELECT Username from Member";
            PreparedStatement prep = conn.prepareStatement(statement);
//            Statement stmt = conn.createStatement();
            ResultSet rs = (ResultSet) prep.executeQuery();
            while(rs.next()){
                String s = rs.getString("Username");
                if(username.equals(s)){
                    prep.close();
                    connection.closeConnection(conn);
                    return true;
                }
            }
            prep.close();
            connection.closeConnection(conn);
            return false;
        }
        catch (SQLException e) {}
        connection.closeConnection(conn);
        return false;
    }
    
//    public static void main(String[] args) {
//        UserDao acc = new UserDao();
//        AdminUser admin1 = acc.addAdminUser("hvu", "1A");
//        AdminUser admin2 = acc.addAdminUser("rlobo", "pass123");
//        MemberUser mem1 = acc.addMemberUser("m1", "pass");
//        MemberUser mem2 = acc.addMemberUser("rlobo", "pass123");
//        EmployeeUser emp1 = acc.addEmployeeUser("Emp1", "pass");
//        EmployeeUser emp2 = acc.addEmployeeUser("rlobo", "pass123");
//        if(admin1 != null)
//        if(mem1 != null)
//        if(emp1 != null)
//            System.out.println("Added");
//        else
//            System.out.println("Failed");
//        
//        if(admin2 != null)
//        if(mem2 != null)
//        if(emp2 != null)
//            System.out.println("Added");
//        else
//            System.out.println("Failed");
//    }
} 