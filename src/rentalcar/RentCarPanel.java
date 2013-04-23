package rentalcar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import core.User.MemberUser;

/**
 * @author Sahil Gupta This class is page for renting the car for members.
 */

public class RentCarPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MemberUser member;
	private JFrame mainFrame;

	private final int WIDTH = 400, HEIGHT = 500;

	private JPanel rentCarPanel;

	JLabel RentACar;
	JLabel PickUpTime, ReturnTime, Location, Cars;

	@SuppressWarnings("rawtypes")
	JComboBox PickUpDateCombo, PickUpTimeCombo, ReturnDateCombo,
			ReturnTimeCombo, LocationCombo, CarsCombo, CarTypesCombo;

	private String[] pickUpDateStrings = { "1/17/2013", "1/17/2013",
			"1/17/2013", "1/17/2013", "1/17/2013" };// GARBAGE VALUES!!! PLEASE
													// CHANGE!!!
	private String[] pickUpTimeStrings = { "1:00", "2:00", "3:00", "4:00",
			"5:00" };// GARBAGE VALUES!!! PLEASE CHANGE!!!
	private String[] returnDateStrings = { "1/17/2013", "1/17/2013",
			"1/17/2013", "1/17/2013", "1/17/2013" };// GARBAGE VALUES!!! PLEASE
													// CHANGE!!!
	private String[] returnTimeStrings = { "1:00", "2:00", "3:00", "4:00",
			"5:00" };// GARBAGE VALUES!!! PLEASE CHANGE!!!
	private String[] locationStrings = { "t-square", "culc", "klaus",
			"north ave", "student center" };// GARBAGE VALUES!!! PLEASE
											// CHANGE!!!
	private String[] carTypesStrings = { "SUV", "sedan", "coupe", "4x4",
			"private jet" };// GARBAGE VALUES!!! PLEASE CHANGE!!!
	private String[] carsStrings = { "SUV", "sedan", "coupe", "4x4",
			"private jet" };// GARBAGE VALUES!!! PLEASE CHANGE!!!

	JButton Search;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RentCarPanel(MemberUser member) {
		this.mainFrame = MainFrame.getMain();
		this.member = member;

		RentACar = new JLabel("Rent A Car");
		RentACar.setFont(new Font("Helvetica", Font.BOLD, 70));

		PickUpTime = new JLabel("Pick Up Time:");
		PickUpDateCombo = new JComboBox(pickUpDateStrings);
		PickUpTimeCombo = new JComboBox(pickUpTimeStrings);

		ReturnTime = new JLabel("Return Time:");
		ReturnDateCombo = new JComboBox(returnDateStrings);
		ReturnTimeCombo = new JComboBox(returnTimeStrings);

		Location = new JLabel("Location:");
		LocationCombo = new JComboBox(locationStrings);

		Cars = new JLabel("Cars:");
		CarTypesCombo = new JComboBox(carTypesStrings);
		CarsCombo = new JComboBox(carsStrings);

		Search = new JButton("Search");
		Search.addActionListener(new SearchButtonListener());

		rentCarPanel = new JPanel();

		rentCarPanel.add(RentACar);
		rentCarPanel.add(PickUpTime);
		rentCarPanel.add(PickUpTimeCombo);
		rentCarPanel.add(ReturnTime);
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