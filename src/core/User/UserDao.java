package core.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.CreditCard.CreditCard;
import core.DrivingPlan.DrivingPlan;
import core.DrivingPlan.PlanType;

public class UserDao {
    static DBConnection connection = new DBConnection();
    String username, password, firstName, middleInit, lastName, email,
    phone, address, plan_Type, nameOnCard, billingAddress;
    Date expiryDate;
    Integer cardNo, cvv;

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
                    prep.execute();
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

    public User login(String username, String password){
        Connection conn = null;
        if(isUsernameExistent(username)) {
            try {
                conn = connection.createConnection();
                String statement = "SELECT Username,Pass from Member";
                PreparedStatement prep = conn.prepareStatement(statement);
                ResultSet rs = (ResultSet) prep.executeQuery();
                while(rs.next()) {
                    String s = rs.getString("Username");
                    if(s.equals(username)){
                        if(rs.getString("Pass").equals(password)){
                            prep.close();
                            MemberUser memUser = new MemberUser(username, password);
                            statement = "SELECT First_Name, Middle_Initial, Last_Name, Email_Address, "
                                + "Phone, Address, Plan_Type, Card_No, Name_on_card, CVV, Expiry_Date, Billing_Address "
                                + "FROM Member NATURAL JOIN Credit_Card "
                                + "WHERE Username = ?";

                            prep = conn.prepareStatement(statement);
                            prep.setString(1, username);
                            rs = (ResultSet) prep.executeQuery();
                            while (rs.next()) {
                                firstName = rs.getString("First_Name");
                                middleInit = rs.getString("Middle_Initial");
                                lastName = rs.getString("Last_Name");
                                email = rs.getString("Email_Address");
                                phone = rs.getString("Phone");
                                address = rs.getString("Address");
                                plan_Type = rs.getString("Plan_Type");
                                DrivingPlan drivingPlan = new DrivingPlan(PlanType.OCCASIONAL);
                                if(PlanType.OCCASIONAL.toString().equals(plan_Type)) 
                                    drivingPlan = new DrivingPlan(PlanType.OCCASIONAL);
                                else if(PlanType.FREQUENT.toString().equals(plan_Type))
                                    drivingPlan = new DrivingPlan(PlanType.FREQUENT);
                                else if(PlanType.DAILY.toString().equals(plan_Type))
                                    drivingPlan = new DrivingPlan(PlanType.DAILY);
                                cardNo = rs.getInt("Card_No");
                                cvv = rs.getInt("CVV");
                                expiryDate = rs.getDate("Expiry_Date");
                                nameOnCard = rs.getString("Name_on_card");
                                billingAddress = rs.getString("Billing_Address");
                                CreditCard creditCard = new CreditCard(cardNo, nameOnCard,
                                        cvv, expiryDate, billingAddress);
                                memUser.setAllMemFields(firstName, middleInit, lastName, email, phone,
                                        address, creditCard, drivingPlan);
                                return memUser;
                            }
                            prep.close();
                            connection.closeConnection(conn);
                        }
                    }
                }
                statement = "SELECT Username,Pass from Employee";
                prep = conn.prepareStatement(statement);
                rs = (ResultSet) prep.executeQuery();
                while(rs.next()) {
                    String s = rs.getString("Username");
                    if(s.equals(username)){
                        if(rs.getString("Pass").equals(password)){
                            prep.close();
                            return new EmployeeUser(username, password);
                        }
                    }
                }
                statement = "SELECT Username,Pass from Administrator";
                prep = conn.prepareStatement(statement);
                rs = (ResultSet) prep.executeQuery();
                while(rs.next()) {
                    String s = rs.getString("Username");
                    if(s.equals(username)){
                        if(rs.getString("Pass").equals(password)){
                            prep.close();
                            return new AdminUser(username, password);
                        }
                    }
                }
                prep.close();
            }
            catch (Exception e) {}

            connection.closeConnection(conn);
            return null;
        }
        return null;
    }

    //    public static void main(String[] args) {
    //        UserDao acc = new UserDao();       
    //        AdminUser admin1 = (AdminUser) acc.login("Bohr", "electron1");
    //        AdminUser admin2 = (AdminUser) acc.login("Newton", "secret");
    //        AdminUser admin3 = (AdminUser) acc.login("rlobo3", "pass123");
    //        MemberUser mem1 = (MemberUser) acc.login("mem1", "M1");
    //        MemberUser mem2 = (MemberUser) acc.login("mem2", "M3");
    //        MemberUser mem3 = (MemberUser) acc.login("rlobo", "pass123");
    //        EmployeeUser emp1 = (EmployeeUser) acc.login("emp1", "pass1");
    //        EmployeeUser emp2 = (EmployeeUser) acc.login("emp2", "pass3");
    //        EmployeeUser emp3 = (EmployeeUser) acc.login("rlobo", "pass123");
    //        AdminUser admin1 = (AdminUser) acc.addUser("hvu", "1A", UserType.ADMIN);
    //        AdminUser admin2 = (AdminUser) acc.addUser("rlobo", "pass123", UserType.ADMIN);
    //        MemberUser mem1 = (MemberUser) acc.addUser("m1", "pass", UserType.MEMBER);
    //        MemberUser mem2 = (MemberUser) acc.addUser("rlobo", "pass123", UserType.MEMBER);
    //        EmployeeUser emp1 = acc.addUser("Emp1", "pass");
    //        EmployeeUser emp2 = acc.addUser("rlobo", "pass123");
    //        if(admin1 != null)
    //        if(mem1 != null)
    //        if(emp1 != null)
    //            System.out.println("Found");
    //        else
    //            System.out.println("Failed");
    //        
    //        if(admin2 != null)
    //        if(mem2 != null)
    //        if(emp2 != null)
    //            System.out.println("Found");
    //        else
    //            System.out.println("Failed");
    //          
    //        if(admin3 != null)
    //          if(mem3 != null)
    //          if(emp3 != null)
    //            System.out.println("Added");
    //        else
    //            System.out.println("Failed");
    //    }
} 