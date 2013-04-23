package core.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.MemberUser;

public class CarDao {
    DBConnection connection = new DBConnection();
    
    public ArrayList<Car> getCars(MemberUser member, ArrayList<String> carTypes,
            ArrayList<String> models, ArrayList<String> locNames) {
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT Car_Type,Model,Location_Name FROM Car";

            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            while(rs.next()){
                carTypes.add(rs.getString("Car_Type"));
                models.add(rs.getString("Model"));
                locNames.add(rs.getString("Location_Name"));
            }
            prep.close();
            connection.closeConnection(conn);
//            return tableElement;
        } catch (SQLException e) {
            connection.closeConnection(conn);
        }
        return null;
    }
}
