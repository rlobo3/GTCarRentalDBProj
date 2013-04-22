package rentalcar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.mysql.jdbc.ResultSet;

public class SQLAccess {
    String connStr, user, password, driverName;
    
    public SQLAccess() {
        connStr = "jdbc:mysql://academicmysql.cc.gatech.edu/cs4400_Group_24";
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
    
    public boolean addUser(String username, String password) {
        if(isUsernameExistent(username)) {
            return false;
        }
        else {
            return true;
//            Connection conn = createConnection();
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
                if(username.equals(rs.getString("Username"))){
                    return true;
                }
                return false;
            }
            
        }
        catch (SQLException e) {}
        return false;
    }
    
    public static void main(String[] args) {
        SQLAccess acc = new SQLAccess();
        if(acc.addUser("hvu", "1A"))
            System.out.println("true");
        else
            System.out.println("false");
    }
} 