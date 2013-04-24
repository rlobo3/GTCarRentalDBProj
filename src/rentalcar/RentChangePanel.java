package rentalcar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.EmployeeUser;

/**
 * @author Sahil Gupta This class is page for submitting maintenance requests.
 */

public class RentChangePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    protected EmployeeUser employee;
    static DBConnection connection = new DBConnection();

    JLabel pageHeader;
    JLabel Location, Cars, ProblemDescLabel;

    @SuppressWarnings("rawtypes")
    JComboBox LocationCombo, CarsCombo, CarTypesCombo;

    private String[] locationStrings;
    private String[] carsModelStrings;
    
    JTextArea ProblemDesc;

    String locationString, carModelString;
    
    JButton submitReqButton;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public RentChangePanel(EmployeeUser employee) {
        this.setBackground(Color.green);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(300, 150, 450, 400);
        this.employee = employee;

        pageHeader = new JLabel("Maintenance Requests");
        pageHeader.setFont(new Font("Helvetica", Font.BOLD, 70));

        int i;
        Connection conn = connection.createConnection();
        try {
            String statement1 = "SELECT DISTINCT Model_Name FROM Car";
            PreparedStatement prep1 = conn.prepareStatement(statement1);
            ResultSet rs1 = (ResultSet) prep1.executeQuery();

            int rowcount = 0;
            if (rs1.last()) {
                rowcount = rs1.getRow();
                rs1.beforeFirst();
            }
            carsModelStrings = new String[rowcount];

            i = 0;
            while (rs1.next()) {
                carsModelStrings[i++] = rs1.getString("Model_Name");
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
            while (rs2.next()) {
                locationStrings[i++] = rs2.getString("Location_Name");
            }
            prep2.close();

            connection.closeConnection(conn);
        } catch (SQLException e) {
            connection.closeConnection(conn);
        }

        this.add(pageHeader);
        Location = new JLabel("Choose Location:");
        this.add(Location);
        LocationCombo = new JComboBox(locationStrings);
        this.add(LocationCombo);

        Cars = new JLabel("Choose car:");
        this.add(Cars);
        CarsCombo = new JComboBox(carsModelStrings);
        this.add(CarsCombo);

        ProblemDescLabel = new JLabel("Brief Description of the Problem: ");        
        this.add(ProblemDescLabel);
        
        ProblemDesc = new JTextArea();
        DefaultCaret caret = (DefaultCaret)ProblemDesc.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        ProblemDesc.setSize(100, 200);
        
        submitReqButton = new JButton("Submit Request");
        submitReqButton.addActionListener(new submitReqButtonListener());
        this.add(submitReqButton);
    }

    public JPanel getPanel() {
        return this;
    }

    private class submitReqButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
//        	INSERT INTO Maintenance_Request VALUES ($Vehicle_Sno, $DATETIME, $UNAME)
//
//        	INSERT INTO Problems VALUES ($Vehicle_Sno,$DATETIME, $Problem)

        }
    }
}