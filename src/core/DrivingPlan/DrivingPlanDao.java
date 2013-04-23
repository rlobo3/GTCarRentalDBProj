package core.DrivingPlan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.MemberUser;

public class DrivingPlanDao {
    DBConnection connection = new DBConnection();
    String drivingPlan;
    Float discout;
    Integer monthlyPayment, annualFees;
    ArrayList<DrivingPlan> tableElement = new ArrayList<DrivingPlan>();
    
    public ArrayList<DrivingPlan> getDrivingPlans(MemberUser member){
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT * FROM Driving_Plan";

            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            while(rs.next()){
//                drivingPlan = rs.getString("Plan_Type");
//                monthlyPayment = rs.getFloat("Monthly_Payment");
//                discout = rs.getString("Discount");
//                annualFees = rs.getString("Annual_Fees");
                DrivingPlan plan = new DrivingPlan(rs.getString("Plan_Type"),
                        Integer.parseInt(rs.getString("Annual_Fees")),
                        Integer.parseInt(rs.getString("Monthly_Payment")),
                        Float.parseFloat(rs.getString("Discount")));
                tableElement.add(plan);
            }
            prep.close();
            connection.closeConnection(conn);
            return tableElement;
        } catch (SQLException e) {
            connection.closeConnection(conn);
        }
        return null;
    }
}
