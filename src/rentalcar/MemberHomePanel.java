package rentalcar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.User.MemberUser;

/**
 * @author Sahil Gupta This class is the home page for students and Georgia Tech
 *         staff/faculty.
 */

public class MemberHomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private MemberUser member;
	private JFrame mainFrame;

	private final int WIDTH = 400, HEIGHT = 500;
	private JPanel memberHomePanel;
	private JLabel heading;
	private JRadioButton carRental, enterViewPI, ViewRentalInfo;
	private String headingString = "Homepage";
	private String carRentalString = "Rent a car";
	private String enterViewPIString = "Enter/View Personal Information";
	private String ViewRentalInfoString = "View Rental Information";

	/**
	 * Sets up a panel with a label and a set of radio buttons that control its
	 * text.
	 */
	public MemberHomePanel(MemberUser member) {

		this.mainFrame = MainFrame.getMain();
		this.member = member;

		heading = new JLabel(headingString);
		heading.setFont(new Font("Helvetica", Font.BOLD, 40));

		carRental = new JRadioButton(carRentalString);
		carRental.setBackground(Color.green);
		enterViewPI = new JRadioButton(enterViewPIString);
		enterViewPI.setBackground(Color.green);
		ViewRentalInfo = new JRadioButton(ViewRentalInfoString);
		ViewRentalInfo.setBackground(Color.green);

		ButtonGroup group = new ButtonGroup();
		group.add(carRental);
		group.add(enterViewPI);
		group.add(ViewRentalInfo);
		memberHomePanelListener listener = new memberHomePanelListener();
		carRental.addActionListener(listener);
		enterViewPI.addActionListener(listener);
		ViewRentalInfo.addActionListener(listener);
		memberHomePanel = new JPanel();

		memberHomePanel.add(heading);
		memberHomePanel.add(carRental);
		memberHomePanel.add(enterViewPI);
		memberHomePanel.add(ViewRentalInfo);
		memberHomePanel.setBackground(Color.green);
		memberHomePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public JPanel getPanel() {
		return memberHomePanel;
	}

	private class memberHomePanelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if (source == carRental) {
				mainFrame.setContentPane(new RentCarPanel(member));
	            mainFrame.setBounds(mainFrame.getContentPane().getBounds());
	            mainFrame.setVisible(true);
	            mainFrame.repaint();
			} else if (source == enterViewPI) {
				mainFrame.setContentPane(new MemberPInfoPanel(member));
	            mainFrame.setBounds(mainFrame.getContentPane().getBounds());
	            mainFrame.setVisible(true);
	            mainFrame.repaint();
			} else if (source == ViewRentalInfo) {
				mainFrame.setContentPane(new RentInfoPanel(member));
	            mainFrame.setBounds(mainFrame.getContentPane().getBounds());
	            mainFrame.setVisible(true);
	            mainFrame.repaint();
			}
		}
	}
}
