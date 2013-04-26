package rentalcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.PreparedStatement;

import core.DBConnection;
import core.Reservation.Reservation;
import core.User.MemberUser;

public class CarAvailPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    MemberUser member;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;

    Object[][] rowData;

    Object[] columnNames = { "Model_Name", "Car_Type", "Location_Name",
            "Color", "Hourly_Rate", "Daily_Rate", "Seating_Cap",
            "Transmission_Type", "Bluetooth", "Auxiliary_Cable",
            "Estimated_Cost", "Annual_Fees" };

    Reservation reservation;
    JTable table;
    ButtonGroup group = new ButtonGroup();

    JLabel pageHeading;
    JButton reserveButton;
    String ReservationTime;
    JRadioButton Selected;
    DBConnection connection = new DBConnection();

    public CarAvailPanel(MemberUser member, Object[][] rowData, Reservation reservation) {
        this.reservation = reservation;
        this.member = member;
        this.rowData = rowData;
        this.setBounds(screenSize.width / 3, screenSize.height / 3,
                DIALOGWIDTH, DIALOGHEIGHT);

        pageHeading = new JLabel("Car Availability");
        pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));
        
        for (int i = 0; i < rowData.length; i++) {
            rowData[i][14] = new JRadioButton("Extend?");
            group.add((JRadioButton)rowData[i][14]);
        };

        table = new JTable(rowData, columnNames);
        if(rowData.length != 0){

            reserveButton = new JButton("Reserve");
            reserveButton.addActionListener(new ReserveButtonListener());

            this.setLayout(new BorderLayout());
            this.add(new JScrollPane(table), BorderLayout.CENTER);

        }
        this.setBackground(Color.green);
        this.setBounds(400, 300, 500, 200);
        this.add(pageHeading, BorderLayout.NORTH);
    }

    private class ReserveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int i = 0; i < rowData.length; i++) {
                Selected = (JRadioButton) rowData[i][14];
                if (Selected.isSelected()) {
                    Connection conn = connection.createConnection();
                    try {
                        String statement = "INSERT INTO Reservation VALUES( ? , ? , ? , ? , ? , 0 , 0 , 0 , 0 )";
                        PreparedStatement prep = conn.prepareStatement(statement);
                        prep.setString(1, (String) member.getUsername());
                        prep.setDate(2, (java.sql.Date) reservation.getPickupDateTime());
                        prep.setDate(3, (java.sql.Date) reservation.getRetDateTime());
                        prep.setString(4, (String)  reservation.getVehicleSNO());
                        prep.setString(5, (String) reservation.getLocName());
                        prep.close();
                        connection.closeConnection(conn);
                    } catch (SQLException e) {
                        connection.closeConnection(conn);
                    }

                }
            }
        }
    }
}