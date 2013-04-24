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
import core.User.EmployeeUser;

public class FreqUserReportPanel extends JPanel {
	private static final long serialVersionUID = 1L;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	DBConnection connection = new DBConnection();

	EmployeeUser employee;
	int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;

	Object[] tableElement;
	Object[] columnNames = { "Username" , "Driving plan", "No of Reservations per month"};
	Object[][] rowData;

	JTable table;
	JLabel pageHeading;

	public FreqUserReportPanel(EmployeeUser employee) {
		this.employee = employee;
		this.setBounds(screenSize.width / 3, screenSize.height / 3,
				DIALOGWIDTH, DIALOGHEIGHT);

		pageHeading = new JLabel("Frequent User Report");
		pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));

		Connection conn = connection.createConnection();
		try {
			String statement = "SELECT Username, Plan_Type , COUNT(*)/3 AS NoOfReservationsPerMonth " +
					"FROM Reservation NATURAL JOIN Member " +
					"WHERE (DATEDIFF( CURRENT_DATE, Pick_Up_Date_Time ) /30 ) >=0 " +
					"AND (DATEDIFF( CURRENT_DATE, Pick_Up_Date_Time ) /30) <=3 GROUP BY Username ORDER BY COUNT(*) DESC";

			PreparedStatement prep = conn.prepareStatement(statement);
			ResultSet rs = (ResultSet) prep.executeQuery();
			int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            rowData = new Object[rowcount][3];
            for(int i = 0; rs.next(); i++){
				rowData[i][0] = rs.getString("Username");
				rowData[i][1] = rs.getString("Plan_Type");
				rowData[i][2] = rs.getString("NoOfReservationsPerMonth");
            }
			prep.close();
			connection.closeConnection(conn);
		} catch (SQLException e) {
			connection.closeConnection(conn);
		}

		table = new JTable(rowData, columnNames);

		this.setLayout(new BorderLayout());
		this.add(pageHeading, BorderLayout.NORTH);
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		this.setBackground(Color.green);
		this.setBounds(400, 300, screenSize.width, screenSize.height);
	}
}
