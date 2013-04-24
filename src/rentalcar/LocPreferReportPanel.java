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

public class LocPreferReportPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	DBConnection connection = new DBConnection();

	EmployeeUser employee;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;

	Object[] tableElement;
	Object[] columnNames = { "Month" , "Location", "No of Reservations", "Total no of hours"};
	Object[][] rowData;

	JTable table;
	JLabel pageHeading;

	public LocPreferReportPanel(EmployeeUser employee) {
		this.employee = employee;
		this.setBounds(screenSize.width / 3, screenSize.height / 3,
				DIALOGWIDTH, DIALOGHEIGHT);

		pageHeading = new JLabel("Location Preference Report");
		pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));

		Connection conn = connection.createConnection();
		try {
			String statement = "SELECT YEAR( Pick_Up_Date_Time ), MONTH( Pick_Up_Date_Time ), Location_Name, COUNT( * ) " +
					"AS NumOfReservation, SUM( TIMEDIFF( Return_Date_Time, Pick_Up_Date_Time ) + Late_By ) AS TotalNoOfHours " +
					"FROM Reservation NATURAL JOIN Car WHERE (DATEDIFF( CURRENT_DATE, Pick_Up_Date_Time ) /30) >=0 AND " +
					"(DATEDIFF( CURRENT_DATE, Pick_Up_Date_Time ) /30) <=3 AND Pick_Up_Date_Time < CURRENT_DATE " +
					"GROUP BY Location_Name, MONTH( Pick_Up_Date_Time ) , YEAR( Pick_Up_Date_Time ) " +
					"ORDER BY YEAR( Pick_Up_Date_Time ) , MONTH( Pick_Up_Date_Time ) DESC ";

			PreparedStatement prep = conn.prepareStatement(statement);
			ResultSet rs = (ResultSet) prep.executeQuery();
			int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            rowData = new Object[rowcount][4];
            for(int i = 0; rs.next(); i++){
				rowData[i][0] = rs.getString("YEAR");
				rowData[i][1] = rs.getString("MONTH");
				rowData[i][2] = rs.getString("NumOfReservation");
				rowData[i][3] = rs.getString("TotalNoOfHours");
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
		this.setBounds(400, 300, 500, 200);
	}
}
