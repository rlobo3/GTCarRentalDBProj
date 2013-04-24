package rentalcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.AdminUser;

public class AdminRevenuePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	DBConnection connection = new DBConnection();

	AdminUser admin;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;

	Object[] tableElement;
	Object[] columnNames = { "Vehicle Sno", "Type", "Car Model",
			"Reservation revenue", "Late Fees Reveue" };
	Object[][] rowData;

	JTable table;
	JLabel pageHeading;

	public AdminRevenuePanel(AdminUser admin) {
		this.admin = admin;
		this.setBounds(screenSize.width / 3, screenSize.height / 3,
				DIALOGWIDTH, DIALOGHEIGHT);

		pageHeading = new JLabel("Revenue Generated");
		pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));

		Connection conn = connection.createConnection();
		try {
			String statement = "SELECT  Vehicle_Sno, Car_Type, Model_Name, SUM( Estimated_Cost )" +
					" AS Reservation_Revenue, SUM( Late_Fees ) FROM Car NATURAL JOIN Reservation " +
					"WHERE 0 <= DATEDIFF( CURRENT_DATE, Return_Date_Time ) /30 <=3 AND " +
					"Return_Date_Time < CURRENT_DATE + GROUP BY Vehicle_Sno";

			PreparedStatement prep = conn.prepareStatement(statement);
			ResultSet rs = (ResultSet) prep.executeQuery();
			while (rs.next()) {
			}
			prep.close();
			connection.closeConnection(conn);
			// return tableElement;
		} catch (SQLException e) {
			connection.closeConnection(conn);
		}

		table = new JTable(rowData, columnNames);

		this.setLayout(new BorderLayout());
		this.add(pageHeading, BorderLayout.NORTH);
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		this.setBackground(Color.green);
		this.setBounds(400, 300, 500, 200);
	}
}
