package rentalcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.MemberUser;

/**
 * 
 * @author Sahil Gupta This page shows the plan details of the different driving
 *         plans.
 */

public class PlanDetailsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	DBConnection connection = new DBConnection();
	Object[] tableElement = new Object[4];
	ArrayList<Object[]> rowDataArr = new ArrayList<Object[]>();
	Object[][] rowData;
	Object[] columnNames = { "Plan_Type", "Monthly_Payment", "Discount",
			"Annual_Fees" };
	JTable table;

	JLabel pageHeading;
	String drivingPlan, discout, annualFees;
	Float monthlyPayment;

	JPanel drivingPlanPanel;

	public PlanDetailsPanel(MemberUser member) {
		this.mainFrame = MainFrame.getMain();
		pageHeading = new JLabel("Driving Plans");
		pageHeading.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		Connection conn = connection.createConnection();
		try {
			String statement = 
					"SELECT * FROM Driving_Plan";
			
			PreparedStatement prep = conn.prepareStatement(statement);
			prep.setString(1, member.getUsername());
			ResultSet rs = (ResultSet) prep.executeQuery();
            while(rs.next()){
                drivingPlan = rs.getString("Plan_Type");
                monthlyPayment = rs.getFloat("Monthly_Payment");
                discout = rs.getString("Discount");
                annualFees = rs.getString("Annual_Fees");
                tableElement[0] = drivingPlan;
                tableElement[1] = monthlyPayment;
                tableElement[2] = discout;
                tableElement[3] = annualFees;
                rowDataArr.add(tableElement);
            }
            prep.close();
			connection.closeConnection(conn);
		} catch (SQLException e) {
			connection.closeConnection(conn);
		}
		
        rowData = new Object[rowDataArr.size()][tableElement.length];
        for(int i = 0; i < rowDataArr.size(); i++){
        	rowData[i] = rowDataArr.get(i);
        }
        table = new JTable(rowData, columnNames);
        
		drivingPlanPanel = new JPanel();
		drivingPlanPanel.setLayout(new BorderLayout());
		drivingPlanPanel.add(pageHeading, BorderLayout.NORTH);
		drivingPlanPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		drivingPlanPanel.setBackground(Color.green);
		drivingPlanPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainFrame.setVisible(true);
		
	}
}
