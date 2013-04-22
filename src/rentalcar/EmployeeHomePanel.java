package rentalcar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author Sahil Gupta
 *
 *This class is the home page for employees where can view, enter information and also access reports.
 */

public class EmployeeHomePanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private final int WIDTH = 400, HEIGHT = 500;
        private JPanel employeeHomePanel;
        private JLabel heading;
        private JButton Next;
        @SuppressWarnings({ "rawtypes" })
        private JComboBox locationPreferenceReport;
        
        private JRadioButton manageCars, maintainanceRequests, rentalChangeRequest, viewReports;
        private String headingString = "Homepage";
        private String manageCarsString = "Manage Cars";
        private String maintainanceRequestsString = "Maintainance requests";
        private String rentalChangeRequestString = "Rental Change Request";
        private String viewReportsString = "View Reports";
        
        private String[] locationPreferenceReportStrings = { "Location Preferance Report", "1/17/2013", "1/17/2013", "1/17/2013", "1/17/2013" };//GARBAGE VALUES!!! PLEASE CHANGE!!!
        
        
        /**
         * Sets up a panel with a label and a set of radio buttons that control its
         * text.
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public EmployeeHomePanel() {
                heading = new JLabel(headingString);
                heading.setFont(new Font("Helvetica", Font.BOLD, 40));

                manageCars = new JRadioButton(manageCarsString);
                manageCars.setBackground(Color.green);
                maintainanceRequests = new JRadioButton(maintainanceRequestsString);
                maintainanceRequests.setBackground(Color.green);
                rentalChangeRequest = new JRadioButton(rentalChangeRequestString);
                rentalChangeRequest.setBackground(Color.green);
                viewReports = new JRadioButton(viewReportsString);
                viewReports.setBackground(Color.green);

                ButtonGroup group = new ButtonGroup();
                group.add(manageCars);
                group.add(maintainanceRequests);
                group.add(rentalChangeRequest);
                group.add(viewReports);
                
                employeeHomePanelListener listener = new employeeHomePanelListener();
                
                manageCars.addActionListener(listener);
                maintainanceRequests.addActionListener(listener);
                rentalChangeRequest.addActionListener(listener);
                viewReports.addActionListener(listener);
                
                locationPreferenceReport = new JComboBox(locationPreferenceReportStrings);
                
                Next = new JButton("Next >>");
                Next.addActionListener(new NextButtonListener());
                
                employeeHomePanel = new JPanel();

                employeeHomePanel.add(heading);
                employeeHomePanel.add(manageCars);
                employeeHomePanel.add(maintainanceRequests);
                employeeHomePanel.add(rentalChangeRequest);
                employeeHomePanel.add(viewReports);
                employeeHomePanel.add(locationPreferenceReport);
                employeeHomePanel.add(Next);
                employeeHomePanel.setBackground(Color.green);
                employeeHomePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        public JPanel getPanel() {
                return employeeHomePanel;
        }
        private class employeeHomePanelListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                        Object source = event.getSource();
                        if (source == manageCars) {

                        } else if (source == maintainanceRequests) {

                        } else if (source == rentalChangeRequest) {

                        } else if (source == viewReports) {

                        }
                }
        }
        private class NextButtonListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                        
                }
        }
}
