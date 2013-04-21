package rentalcar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author Sahil Gupta This class displays the Personal Information of the user.
 */
public class MemberPInfoPanel extends JPanel {

	// See if you should make a query to the database and see that if the user
	// exits
	// then you should display their current information fetched from the
	// database
	// so the user can edit it and update their information based on the changes
	// they might make. This can be seen from the GUI given in project.

	private static final long serialVersionUID = 1L;
	
	private final int WIDTH = 400, HEIGHT = 1500;

	private JPanel memberPInfoPanel;
	JLabel PersonalInformation, GeneralInformation, MembershipInformation,
			PaymentInformation;
	JLabel FirstName, MiddleInitial, LastName, EmailAddress, PhoneNumber,
			Address;
	JTextField FirstNameField, MiddleInitialField, LastNameField,
			EmailAddressField, PhoneNumberField, AddressField;
	JLabel choosePlan;
	private JRadioButton OccasionalDriving, FrequentDriving, DailyDriving;
	private String membershipHeadingString = "Choose A Plan";
	private String OccasionalDrivingString = "Occasional Driving";
	private String FrequentDrivingString = "Frequent Driving";
	private String DailyDrivingString = "Daily Driving";

	JLabel NameCard, CardNumber, CVV, ExpiryDate, BillingAddress;
	JTextField NameCardField, CardNumberField, CVVField, ExpiryDateField,
			BillingAddressField;

	JButton ViewPlanDetail, Done;

	public MemberPInfoPanel() {
		PersonalInformation = new JLabel("Personal Information");
		PersonalInformation.setFont(new Font("Helvetica", Font.BOLD, 70));

		GeneralInformation = new JLabel("General Information");
		GeneralInformation.setFont(new Font("Helvetica", Font.BOLD, 40));

		FirstName = new JLabel("First Name");
		FirstNameField = new JTextField(20);

		MiddleInitial = new JLabel("Middle Initial");
		MiddleInitialField = new JTextField(20);

		LastName = new JLabel("Last Name");
		LastNameField = new JTextField(20);

		EmailAddress = new JLabel("Email Address");
		EmailAddressField = new JTextField(20);

		PhoneNumber = new JLabel("Phone Number");
		PhoneNumberField = new JTextField(20);

		Address = new JLabel("Address");
		AddressField = new JTextField(20);

		MembershipInformation = new JLabel("Membership Information");
		MembershipInformation.setFont(new Font("Helvetica", Font.BOLD, 40));

		choosePlan = new JLabel(membershipHeadingString);
		choosePlan.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		ViewPlanDetail = new JButton("View Plan Detail");
		ViewPlanDetail.addActionListener(new ViewPlanDetailsButtonListener());

		OccasionalDriving = new JRadioButton(OccasionalDrivingString);
		OccasionalDriving.setBackground(Color.green);
		FrequentDriving = new JRadioButton(FrequentDrivingString);
		FrequentDriving.setBackground(Color.green);
		DailyDriving = new JRadioButton(DailyDrivingString);
		DailyDriving.setBackground(Color.green);

		ButtonGroup group = new ButtonGroup();
		group.add(OccasionalDriving);
		group.add(FrequentDriving);
		group.add(DailyDriving);

		memberPInfoPanelListener listener = new memberPInfoPanelListener();
		OccasionalDriving.addActionListener(listener);
		FrequentDriving.addActionListener(listener);
		DailyDriving.addActionListener(listener);

		PaymentInformation = new JLabel("Payment Information");
		PaymentInformation.setFont(new Font("Helvetica", Font.BOLD, 40));

		NameCard = new JLabel("Name on the card");
		NameCardField = new JTextField(20);

		CardNumber = new JLabel("Card Number");
		CardNumberField = new JTextField(20);

		CVV = new JLabel("CVV");
		CVVField = new JTextField(20);

		ExpiryDate = new JLabel("Expiry Date");
		ExpiryDateField = new JTextField(20);

		BillingAddress = new JLabel("Billing Address");
		BillingAddressField = new JTextField(20);

		Done = new JButton("Done");
		Done.addActionListener(new DoneButtonListener());

		memberPInfoPanel = new JPanel();

		memberPInfoPanel.add(PersonalInformation);
		memberPInfoPanel.add(GeneralInformation);
		memberPInfoPanel.add(FirstName);
		memberPInfoPanel.add(FirstNameField);
		memberPInfoPanel.add(MiddleInitial);
		memberPInfoPanel.add(MiddleInitialField);
		memberPInfoPanel.add(LastName);
		memberPInfoPanel.add(LastNameField);
		memberPInfoPanel.add(EmailAddress);
		memberPInfoPanel.add(EmailAddressField);
		memberPInfoPanel.add(PhoneNumber);
		memberPInfoPanel.add(PhoneNumberField);
		memberPInfoPanel.add(Address);
		memberPInfoPanel.add(AddressField);
		memberPInfoPanel.add(MembershipInformation);
		memberPInfoPanel.add(choosePlan);
		memberPInfoPanel.add(ViewPlanDetail);
		memberPInfoPanel.add(OccasionalDriving);
		memberPInfoPanel.add(FrequentDriving);
		memberPInfoPanel.add(DailyDriving);
		memberPInfoPanel.add(PaymentInformation);
		memberPInfoPanel.add(NameCard);
		memberPInfoPanel.add(NameCardField);
		memberPInfoPanel.add(CardNumber);
		memberPInfoPanel.add(CardNumberField);
		memberPInfoPanel.add(CVV);
		memberPInfoPanel.add(CVVField);
		memberPInfoPanel.add(ExpiryDate);
		memberPInfoPanel.add(ExpiryDateField);
		memberPInfoPanel.add(BillingAddress);
		memberPInfoPanel.add(BillingAddressField);
		memberPInfoPanel.add(Done);
		
		memberPInfoPanel.setBackground(Color.green);
		memberPInfoPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

	}

	public JPanel getPanel() {
		return memberPInfoPanel;
	}

	private class memberPInfoPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if (source == OccasionalDriving) {

			} else if (source == FrequentDriving) {

			} else if (source == DailyDriving) {

			}
		}
	}

	private class ViewPlanDetailsButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
		}
	}
	
	private class DoneButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
		}
	}

}
