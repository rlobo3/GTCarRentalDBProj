package rentalcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import core.User.MemberUser;

public class CarAvailPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;

	Object[] tableElement = new Object[4];
	ArrayList<Object[]> rowDataArr = new ArrayList<Object[]>();
	Object[][] rowData;

	Object[] columnNames = { "Model_Name", "Car_Type", "Location_Name",
			"Color", "Hourly_Rate", "Daily_Rate", "Seating_Cap",
			"Transmission_Type", "Bluetooth", "Auxiliary_Cable",
			"Estimated_Cost", "Annual_Fees" };
	JTable table;
	ButtonGroup group;

	JLabel pageHeading;
	JButton reserveButton;
	String ReservationTime;
	JRadioButton Selected;
	
	int numRows;

	public CarAvailPanel(MemberUser member, Object[][] rowData, int numRows) {
		this.numRows = numRows;
		this.setBounds(screenSize.width / 3, screenSize.height / 3,
				DIALOGWIDTH, DIALOGHEIGHT);

		pageHeading = new JLabel("Car Availability");
		pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));

		table = new JTable(rowData, columnNames);
		
		for(int i = 0; i < numRows; i++){
			group.add((JRadioButton) rowData[i][14]);
		}

		reserveButton = new JButton("Reserve");
		reserveButton.addActionListener(new ReserveButtonListener());

		
		this.setLayout(new BorderLayout());
		this.add(pageHeading, BorderLayout.NORTH);
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		this.setBackground(Color.green);
		this.setBounds(400, 300, 500, 200);		
	}
	
    private class ReserveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	for(int i = 0; i < numRows; i++){
        		Selected = (JRadioButton) rowData[i][14];
        		if(Selected.isSelected()){
        			
        		}
        	}
        }
    }

}