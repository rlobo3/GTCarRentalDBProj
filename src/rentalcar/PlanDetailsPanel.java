package rentalcar;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.MemberUser;

public class PlanDetailsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MemberUser member;
	private JFrame mainFrame;
	DBConnection connection = new DBConnection();
	
	JLabel pageHeading, DrivingPlan, MonthlyPayment, Discout, AnnualFees;

	public PlanDetailsPanel(MemberUser member) {
		this.mainFrame = MainFrame.getMain();
		this.member = member;
		pageHeading = new JLabel("Driving Plans");
		pageHeading.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		Connection conn = connection.createConnection();
		try {
			String statement = 
					"SELECT * FROM Driving_Plan";
			
			PreparedStatement prep = conn.prepareStatement(statement);
			prep.setString(1, member.getUsername());
			ResultSet rs = (ResultSet) prep.executeQuery();
			prep.close();

			connection.closeConnection(conn);
		} catch (SQLException e) {
			connection.closeConnection(conn);
		}

		
		JTable table;
		
		DrivingPlan = new JLabel("Driving Plan");
		MonthlyPayment = new JLabel("Monthly Payment");
		Discout = new JLabel("Discout");
		AnnualFees = new JLabel("Annual Fees");
		

	}
}
