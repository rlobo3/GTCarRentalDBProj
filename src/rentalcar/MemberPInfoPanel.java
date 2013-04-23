package rentalcar;

import java.awt.BorderLayout;
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

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import core.DBConnection;
import core.User.MemberUser;

/**
 * @author Sahil Gupta This class displays the Personal Information of the user.
 */
public class MemberPInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private MemberUser member;
	DBConnection connection = new DBConnection();

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
	String username, password;

	public MemberPInfoPanel(MemberUser member) {
		this.member = member;
                
		username = member.getUsername();
		password = member.getPassword();
		
		this.setBackground(Color.green);
	        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	        setBounds(100, 0, screenSize.width/2, screenSize.height-40);

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

		if(member.getFirstName() != null)		
                    FirstNameField.setText(member.getFirstName());
                if (member.getMiddleInit() != null)
                    MiddleInitialField.setText(member.getMiddleInit());
                if(member.getLastName() != null)
                    LastNameField.setText(member.getLastName());
                if(member.getEmail() != null)
                    EmailAddressField.setText(member.getEmail());
                if(member.getPhone() != null)
                    PhoneNumberField.setText(member.getPhone());
                if(member.getAddress() != null)
                    AddressField.setText(member.getAddress());
                if(member.getDrivingPlan() != null){
                    if (member.getDrivingPlan().getPlanType().equals("Occasional Driving")) {
                        OccasionalDriving.setSelected(true);
                    } else if (member.getDrivingPlan().getPlanType().equals("Frequent Driving")) {
                        FrequentDriving.setSelected(true);
                    } else if (member.getDrivingPlan().getPlanType().equals("Daily Driving")) {
                        DailyDriving.setSelected(true);
                    }
                }
                if(member.getCreditCard().getNameOnCard() != null)
                    NameCardField.setText(member.getCreditCard().getNameOnCard());
                if(""+member.getCreditCard().getCardNumber() != "")
                    CardNumberField.setText(""+member.getCreditCard().getCardNumber());
                if(""+member.getCreditCard().getCvv() != "")
                    CVVField.setText(""+member.getCreditCard().getCvv());
                if(member.getCreditCard().getExpiryDate().toString() != null)
                    ExpiryDateField.setText(member.getCreditCard().getExpiryDate().toString());
                if(member.getCreditCard().getBillingAddress() != null)
                    BillingAddressField.setText(member.getCreditCard().getBillingAddress());

		this.add(PersonalInformation);
		this.add(GeneralInformation);
		this.add(FirstName);
		this.add(FirstNameField);
		this.add(MiddleInitial);
		this.add(MiddleInitialField);
		this.add(LastName);
		this.add(LastNameField);
		this.add(EmailAddress);
		this.add(EmailAddressField);
		this.add(PhoneNumber);
		this.add(PhoneNumberField);
		this.add(Address);
		this.add(AddressField);
		this.add(MembershipInformation);
		this.add(choosePlan);
		this.add(ViewPlanDetail);
		this.add(OccasionalDriving);
		this.add(FrequentDriving);
		this.add(DailyDriving);
		this.add(PaymentInformation);
		this.add(NameCard);
		this.add(NameCardField);
		this.add(CardNumber);
		this.add(CardNumberField);
		this.add(CVV);
		this.add(CVVField);
		this.add(ExpiryDate);
		this.add(ExpiryDateField);
		this.add(BillingAddress);
		this.add(BillingAddressField);
		this.add(Done);
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
			Integer mm = Integer.parseInt(ExpiryDateField.getText().substring(
					0, 1));
			Integer yyyy = Integer.parseInt(ExpiryDateField.getText()
					.substring(3, 6));
			expiryDateN = new Date(yyyy, mm, 01);
			cardNoN = Integer.parseInt(CardNumberField.getText());
			cvvN = Integer.parseInt(CVVField.getText());
			// Update operations if anything changes
			Connection conn = connection.createConnection();
			try {
				String statement = "UPDATE Member SET First_Name = ?, Middle_Initial = ?, "
						+ "Last_Name = ?,Email_Address = ?, Phone= ?,"
						+ "Address= ?, Plan_Type= ?, Card_No=? "
						+ "WHERE Username= ?";

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

				String statement2 = "UPDATE Credit_Card SET Name_on_card= ?, "
						+ "CVV= ?, Expiry_Date= ?, Billing_Address= ? "
						+ "WHERE Card_No = ( SELECT Card_No FROM Member WHERE Username= ?)";
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