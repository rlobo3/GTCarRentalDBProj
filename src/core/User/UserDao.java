package core.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSet;

public class UserDao {
    String connStr, user, password, driverName;
    
    public UserDao() {
        connStr = "jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Group_24";
        user = "cs4400_Group_24";
        password = "JZpQT_7j";
        driverName = "com.mysql.jdbc.Driver";
    }
    
    public Connection createConnection() {
        
        Connection conn = null; 
        try { 
            Class.forName(driverName).newInstance(); 
            conn = DriverManager.getConnection(connStr, user, password); 
            if(!conn.isClosed()) {
                System.out.println("Successfully connected to " + 
                "MySQL server using TCP/IP..."); 
                return conn;
            }
        } catch(Exception e) { 
            System.err.println("Exception: " + e.getMessage()); 
        }
        return null;
    }
    
    public boolean closeConnection(Connection conn) {     
        try { 
            if(conn != null) { 
                conn.close();
                return true;
            }
            return false;
        }
        catch(SQLException e) {}
        return false; 
    }
    
    public AdminUser addAdminUser(String username, String password) {
        if(isUsernameExistent(username)) {
            return null;
        }
        else {
            Connection conn = createConnection();
            try {
                String statement = "INSERT INTO Administrator(Username,Pass) VALUES(?,?)";
                PreparedStatement prep = conn.prepareStatement(statement);
                prep.setString(1, username);
                prep.setString(2, password);
                prep.executeUpdate();
                prep.close();
                closeConnection(conn);
                return new AdminUser(username, password);
            }
            catch (SQLException e) {}
            closeConnection(conn);
            return null;
        }
    }
    
    public MemberUser addMemberUser(String username, String password) {
        if(isUsernameExistent(username)) {
            return null;
        }
        else {
            Connection conn = createConnection();
            try {
                String statement = "INSERT INTO Administrator(Username,Pass) VALUES(?,?)";
                PreparedStatement prep = conn.prepareStatement(statement);
                prep.setString(1, username);
                prep.setString(2, password);
                prep.executeUpdate();
                prep.close();
                closeConnection(conn);
                return new MemberUser(username, password);
            }
            catch (SQLException e) {}
            closeConnection(conn);
            return null;
        }
    }
    
    public EmployeeUser addEmployeeUser(String username, String password) {
        if(isUsernameExistent(username)) {
            return null;
        }
        else {
            Connection conn = createConnection();
            try {
                String statement = "INSERT INTO Administrator(Username,Pass) VALUES(?,?)";
                PreparedStatement prep = conn.prepareStatement(statement);
                prep.setString(1, username);
                prep.setString(2, password);
                prep.executeUpdate();
                prep.close();
                closeConnection(conn);
                return new EmployeeUser(username, password);
            }
            catch (SQLException e) {}
            closeConnection(conn);
            return null;
        }
    }
    
    public boolean isUsernameExistent(String username) {
        Connection conn = createConnection();
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
                    closeConnection(conn);
                    return true;
                }
            }
            prep.close();
            closeConnection(conn);
            return false;
        }
        catch (SQLException e) {}
        closeConnection(conn);
        return false;
    }
    
    public static void main(String[] args) {
        UserDao acc = new UserDao();
        AdminUser admin1 = acc.addAdminUser("hvu", "1A");
        AdminUser admin2 = acc.addAdminUser("rlobo", "pass123");
        if(admin1 != null)
            System.out.println("Added");
        else
            System.out.println("Failed");
        
        if(admin2 != null)
            System.out.println("Added");
        else
            System.out.println("Failed");
    }
} 