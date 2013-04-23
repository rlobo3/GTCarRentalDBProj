package rentalcar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import org.jdesktop.swingx.JXDatePicker;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.MemberUser;

/**
 * @author Sahil Gupta This class is page for renting the car for members.
 */

public class RentCarPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MemberUser member;
	private JFrame mainFrame;
    static DBConnection connection = new DBConnection();

	private final int WIDTH = 400, HEIGHT = 500;

	private JPanel rentCarPanel;

	JLabel RentACar;
	JLabel PickUpTime, ReturnTime, Location, Cars;

	@SuppressWarnings("rawtypes")
	JComboBox PickUpTimeCombo, ReturnTimeCombo, LocationCombo, CarsCombo, CarTypesCombo;
	
	JXDatePicker PickUpDateCombo, ReturnDateCombo;

	//Had to hard code it because there was no way out.
	private String[] pickUpTimeStrings = { "12:00 AM", "12:30 AM", "1:00 AM", "1:30 AM", "2:00 AM","02:30 AM","03:00 AM","3:30 AM",
			"04:00 AM","04:30 AM","05:00 AM","05:30 AM", "06:00 AM", "06:30 AM", "07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM", "09:00 AM", 
			"09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM","02:30 PM","03:00 PM","3:30 PM",
			"04:00 PM","04:30 PM","05:00 PM","05:30 PM", "06:00 PM", "06:30 PM", "07:00 PM", "07:30 PM", "08:00 PM", "08:30 PM", "09:00 PM", 
			"09:30 PM", "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM"};
	
	private String[] returnTimeStrings = { "12:00 AM", "12:30 AM", "1:00 AM", "1:30 AM", "2:00 AM","02:30 AM","03:00 AM","3:30 AM",
			"04:00 AM","04:30 AM","05:00 AM","05:30 AM", "06:00 AM", "06:30 AM", "07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM", "09:00 AM", 
			"09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM","02:30 PM","03:00 PM","3:30 PM",
			"04:00 PM","04:30 PM","05:00 PM","05:30 PM", "06:00 PM", "06:30 PM", "07:00 PM", "07:30 PM", "08:00 PM", "08:30 PM", "09:00 PM", 
			"09:30 PM", "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM"};
	
	private String[] locationStrings;
	private String[] carTypesStrings;
	private String[] carsModelStrings;

	JButton Search;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RentCarPanel(MemberUser member) {
		this.mainFrame = MainFrame.getMain();
		this.member = member;

		RentACar = new JLabel("Rent A Car");
		RentACar.setFont(new Font("Helvetica", Font.BOLD, 70));

		PickUpTime = new JLabel("Pick Up Time:");
		java.util.Date pickdate = new java.util.Date();
		PickUpDateCombo = new JXDatePicker(pickdate);
		PickUpTimeCombo = new JComboBox(pickUpTimeStrings);

		ReturnTime = new JLabel("Return Time:");
		java.util.Date returndate = new java.util.Date();
		PickUpDateCombo = new JXDatePicker(returndate);
		ReturnTimeCombo = new JComboBox(returnTimeStrings);
		
        Connection conn = connection.createConnection();
        try {
        	
            String statement = "SELECT distinct Car_Type FROM Car";
            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            
            int rowcount = 0;
            if (rs.last()) {
              rowcount = rs.getRow();
              rs.beforeFirst();
            }
            carTypesStrings = new String[rowcount];
            
            int i = 0;
            while(rs.next()){
            	carTypesStrings[i++] = rs.getString("Car_Type");
            }
            prep.close();
            
            String statement1 = "SELECT distinct Model FROM Car";
            PreparedStatement prep1 = conn.prepareStatement(statement1);
            ResultSet rs1 = (ResultSet) prep1.executeQuery();
            
            rowcount = 0;
            if (rs1.last()) {
              rowcount = rs1.getRow();
              rs1.beforeFirst();
            }
            carsModelStrings = new String[rowcount];
            
            i = 0;
            while(rs1.next()){
            	carsModelStrings[i++] = rs1.getString("Car_Type");
            }
            prep1.close();

            String statement2 = "SELECT distinct Location_Name FROM Car";
            PreparedStatement prep2 = conn.prepareStatement(statement2);
            ResultSet rs2 = (ResultSet) prep2.executeQuery();
            
            rowcount = 0;
            if (rs2.last()) {
              rowcount = rs2.getRow();
              rs2.beforeFirst();
            }
            locationStrings = new String[rowcount];
            
            i = 0;
            while(rs2.next()){
            	locationStrings[i++] = rs2.getString("Car_Type");
            }
            prep2.close();

            connection.closeConnection(conn);
        }
        catch (SQLException e) {
        connection.closeConnection(conn);
        }

		Location = new JLabel("Location:");
		if(carsModelStrings != null){
			LocationCombo = new JComboBox(carsModelStrings);
		}

		Cars = new JLabel("Cars:");
		if(carTypesStrings != null){
			CarTypesCombo = new JComboBox(carTypesStrings);
		}
		if(carsModelStrings != null){
			CarsCombo = new JComboBox(carsModelStrings);
		}

		Search = new JButton("Search");
		Search.addActionListener(new SearchButtonListener());

		rentCarPanel = new JPanel();

		rentCarPanel.add(RentACar);
		
		rentCarPanel.add(PickUpTime);
		rentCarPanel.add(PickUpDateCombo);
		rentCarPanel.add(PickUpTimeCombo);
		
		rentCarPanel.add(ReturnTime);
		rentCarPanel.add(PickUpDateCombo);
		rentCarPanel.add(ReturnTimeCombo);
		
		rentCarPanel.add(Location);
		rentCarPanel.add(LocationCombo);
		rentCarPanel.add(Cars);
		rentCarPanel.add(CarsCombo);

		rentCarPanel.add(Search);

		rentCarPanel.setBackground(Color.green);
		rentCarPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

	}

	public JPanel getPanel() {
		return rentCarPanel;
	}

	private class SearchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}
}