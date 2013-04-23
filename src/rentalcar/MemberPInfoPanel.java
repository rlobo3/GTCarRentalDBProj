package rentalcar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.MemberUser;

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
	private MemberUser member;
	private JFrame mainFrame;
	DBConnection connection = new DBConnection();

	private final int WIDTH = 400, HEIGHT = 1000;

	private JPanel memberPInfoPanel;
	JLabel PersonalInformation, GeneralInformation, MembershipInformation,
			PaymentInformation;
	JLabel FirstName, MiddleInitial, LastName, EmailAddress, PhoneNumber,
			Address;
	JTextField FirstNameField, MiddleInitialField, LastNameField,
			EmailAddressField, PhoneNumberField, AddressField;
	JLabel choosePlan;
	private JRadioButton OccasionalDriving, FrequentDriving, DailyDriving;
	ButtonGroup group = new ButtonGroup();
	private String membershipHeadingString = "Choose A Plan";
	private String OccasionalDrivingString = "Occasional Driving";
	private String FrequentDrivingString = "Frequent Driving";
	private String DailyDrivingString = "Daily Driving";

	JLabel NameCard, CardNumber, CVV, ExpiryDate, BillingAddress;
	JTextField NameCardField, CardNumberField, CVVField, ExpiryDateField,
			BillingAddressField;

	JButton ViewPlanDetail, Done;

	String username, password, firstName, middleInitial, lastName,
			emailAddress, Phone, address, Plan_Type;
	Date expiryDate;
	Integer cardNo, cvv;
	String nameOnCard, billingAddress;

	public MemberPInfoPanel(MemberUser member) {
		this.mainFrame = MainFrame.getMain();
		this.member = member;
		username = member.getUsername();
		password = member.getPassword();

		PersonalInformation = new JLabel("Personal Information");
		PersonalInformation.setFont(new Font("Helvetica", Font.BOLD, 70));

		GeneralInformation = new JLabel("General Information");
		GeneralInformation.setFont(new Font("Helvetica", Font.BOLD, 40));

		FirstName = new JLabel("First Name");
		FirstNameField = new JTextField(30);

		MiddleInitial = new JLabel("Middle Initial");
		MiddleInitialField = new JTextField(1);

		LastName = new JLabel("Last Name");
		LastNameField = new JTextField(30);

		EmailAddress = new JLabel("Email Address");
		EmailAddressField = new JTextField(50);

		PhoneNumber = new JLabel("Phone Number");
		PhoneNumberField = new JTextField(15);

		Address = new JLabel("Address");
		AddressField = new JTextField(100);

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

		group.add(OccasionalDriving);
		group.add(FrequentDriving);
		group.add(DailyDriving);

		PaymentInformation = new JLabel("Payment Information");
		PaymentInformation.setFont(new Font("Helvetica", Font.BOLD, 40));

		NameCard = new JLabel("Name on the card");
		NameCardField = new JTextField(50);

		CardNumber = new JLabel("Card Number");
		CardNumberField = new JTextField(20);

		CVV = new JLabel("CVV");
		CVVField = new JTextField(3);

		ExpiryDate = new JLabel("Expiry Date");
		ExpiryDateField = new JTextField(20);

		BillingAddress = new JLabel("Billing Address");
		BillingAddressField = new JTextField(100);

		Done = new JButton("Done");
		Done.addActionListener(new DoneButtonListener());

		Connection conn = connection.createConnection();
		try {
			String statement = 
					"SELECT First_Name, Middle_Initial, Last_Name, Email_Address, "
					+ "Phone, Address, Plan_Type, Card_No, Name_on_card, CVV, Expiry_Date, Billing_Address "
					+ "FROM Member NATURAL JOIN Credit_Card "
					+ "WHERE Username = ?";
			
			PreparedStatement prep = conn.prepareStatement(statement);
			prep.setString(1, member.getUsername());
			ResultSet rs = (ResultSet) prep.executeQuery();
				firstName = rs.getString("First_Name");
				middleInitial = rs.getString("Middle_Initial");
				lastName = rs.getString("Last_Name");
				emailAddress = rs.getString("Email_Address");
				Phone = rs.getString("Phone");
				address = rs.getString("Address");
				Plan_Type = rs.getString("Plan_Type");
				cardNo = rs.getInt("Card_No");
				cvv = rs.getInt("CVV");
				expiryDate = rs.getDate("Expiry_Date");
				nameOnCard = rs.getString("Name_on_card");
				billingAddress = rs.getString("Billing_Address");
			prep.close();

			connection.closeConnection(conn);
		} catch (SQLException e) {
			connection.closeConnection(conn);
		}

		memberPInfoPanel = new JPanel();

		memberPInfoPanel.add(PersonalInformation);
		memberPInfoPanel.add(GeneralInformation);
		memberPInfoPanel.add(FirstName);
		memberPInfoPanel.add(FirstNameField);
		FirstNameField.setText(firstName);
		memberPInfoPanel.add(MiddleInitial);
		memberPInfoPanel.add(MiddleInitialField);
		if (middleInitial != null) {
			MiddleInitialField.setText(middleInitial);
		}
		memberPInfoPanel.add(LastName);
		memberPInfoPanel.add(LastNameField);
		LastNameField.setText(lastName);
		memberPInfoPanel.add(EmailAddress);
		memberPInfoPanel.add(EmailAddressField);
		EmailAddressField.setText(emailAddress);
		memberPInfoPanel.add(PhoneNumber);
		memberPInfoPanel.add(PhoneNumberField);
		PhoneNumberField.setText(Phone);
		memberPInfoPanel.add(Address);
		memberPInfoPanel.add(AddressField);
		AddressField.setText(address);
		memberPInfoPanel.add(MembershipInformation);
		memberPInfoPanel.add(choosePlan);
		memberPInfoPanel.add(ViewPlanDetail);
		memberPInfoPanel.add(OccasionalDriving);
		memberPInfoPanel.add(FrequentDriving);
		memberPInfoPanel.add(DailyDriving);
		if (Plan_Type.equals("Occasional Driving")) {
			OccasionalDriving.setSelected(true);
		} else if (Plan_Type.equals("Frequent Driving")) {
			FrequentDriving.setSelected(true);
		} else if (Plan_Type.equals("Daily Driving")) {
			DailyDriving.setSelected(true);
		}
		memberPInfoPanel.add(PaymentInformation);
		memberPInfoPanel.add(NameCard);
		memberPInfoPanel.add(NameCardField);
		NameCardField.setText(nameOnCard);
		memberPInfoPanel.add(CardNumber);
		memberPInfoPanel.add(CardNumberField);
		CardNumberField.setText(cardNo.toString());
		memberPInfoPanel.add(CVV);
		memberPInfoPanel.add(CVVField);
		CVVField.setText(cvv.toString());
		memberPInfoPanel.add(ExpiryDate);
		memberPInfoPanel.add(ExpiryDateField);
		ExpiryDateField.setText(expiryDate.toString());
		memberPInfoPanel.add(BillingAddress);
		memberPInfoPanel.add(BillingAddressField);
		BillingAddressField.setText(billingAddress);
		memberPInfoPanel.add(Done);

		memberPInfoPanel.setBackground(Color.green);
		memberPInfoPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public JPanel getPanel() {
		return memberPInfoPanel;
	}

	private class ViewPlanDetailsButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;
			JDialog regDialog = new JDialog(new JFrame(), "Register");
			regDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			regDialog.setBounds(screenSize.width / 3, screenSize.height / 3,
					DIALOGWIDTH, DIALOGHEIGHT);
			regDialog.add(new PlanDetailsPanel(member));
			regDialog.setVisible(true);
		}
	}

	private class DoneButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// New values which will be fetched from the textFields
			String firstNameN, middleInitialN, lastNameN, emailAddressN, PhoneN, addressN, Plan_TypeN = "";
			Date expiryDateN;
			Integer cardNoN, cvvN;
			String nameOnCardN, billingAddressN;
			firstNameN = FirstNameField.getText();
			middleInitialN = MiddleInitialField.getText();
			lastNameN = LastNameField.getText();
			emailAddressN = EmailAddressField.getText();
			PhoneN = PhoneNumberField.getText();
			addressN = AddressField.getText();

			if (OccasionalDriving.isSelected()) {
				Plan_TypeN = "Occasional Driving";
			} else if (FrequentDriving.isSelected()) {
				Plan_TypeN = "Frequent Driving";
			} else if (DailyDriving.isSelected()) {
				Plan_TypeN = "Daily Driving";
			}
			nameOnCardN = NameCardField.getText();
			billingAddressN = BillingAddressField.getText();
			Integer mm = Integer.parseInt(ExpiryDateField.getText().substring(0, 1));
			Integer yyyy =  Integer.parseInt(ExpiryDateField.getText().substring(3, 6));
			expiryDateN = new Date(yyyy, mm, 01);
			cardNoN = Integer.parseInt(CardNumberField.getText());
			cvvN = Integer.parseInt(CVVField.getText());
			//Update operations if anything changes
			Connection conn = connection.createConnection();
			try {
				String statement = 
						"UPDATE Member SET First_Name = ?, Middle_Initial = ?, " +
						"Last_Name = ?,Email_Address = ?, Phone= ?," +
						"Address= ?, Plan_Type= ?, Card_No=? " +
						"WHERE Username= ?";
				
				PreparedStatement prep = conn.prepareStatement(statement);
				prep.setString(1, firstNameN);
				prep.setString(2, middleInitialN);
				prep.setString(3, lastNameN);
				prep.setString(4, emailAddressN);
				prep.setString(5, PhoneN);
				prep.setString(6, addressN);
				prep.setString(7, Plan_TypeN);
				prep.setInt(8, cardNoN);
				prep.setString(9, member.getUsername());
                prep.executeUpdate();
				prep.close();
				
				String statement1 = "INSERT INTO Credit_Card(Card_No) VALUES ?";
				PreparedStatement prep1 = conn.prepareStatement(statement1);
				prep1.setString(1, firstNameN);
                prep1.executeUpdate();
				prep1.close();

				String statement2 = 
						"UPDATE Credit_Card SET Name_on_card= ?, " +
						"CVV= ?, Expiry_Date= ?, Billing_Address= ? " +
						"WHERE Card_No = ( SELECT Card_No FROM Member WHERE Username= ?)";
				PreparedStatement prep2 = conn.prepareStatement(statement2);
				prep2.setString(1, nameOnCardN);
				prep2.setInt(2, cvvN);
				prep2.setDate(3, expiryDateN);
				prep2.setString(4, billingAddressN);
				prep2.setString(5, member.getUsername());
				connection.closeConnection(conn);
			} catch (SQLException e) {
				connection.closeConnection(conn);
			}
			
		}
	}
}