package rentalcar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.DBConnection;
import core.User.EmployeeUser;

public class ManageCarsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeUser employee;
	private JFrame mainFrame;
	DBConnection connection = new DBConnection();
	
	private final int WIDTH = 400, HEIGHT = 1000;
	final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	JLabel ManageCars, AddACar, ChangeCarLocation, BriefDescription;
	
	JLabel VehicleSno, CarModel, CarType, Location, Color,
	HourlyRate, DailyRate, SeatingCapacity, TransmissionType,
	BluetoothConnectivity, AuxilliaryCable;
	JTextField VehicleSnoTextField, CarModelTextField, ColorTextField,
	HourlyRateTextField, DailyRateTextField, SeatingCapacityTextField;
	
	JComboBox CarTypeCombo, LocationCombo, TransmissionTypeCombo,
	
	BluetoothConnectivityCombo, AuxilliaryCableCombo;
	
	JButton add;
	
	private String[] carTypeStrings = { "Hybrid"};//ADD VALUES!!! PLEASE CHANGE!!!
	private String[] locationStrings = { "Student Center"};//ADD VALUES!!! PLEASE CHANGE!!!
	private String[] transmissionTypeStrings = { "Automatic", "Manual"};
	private String[] bluetoothConnectivityStrings = { "No", "Yes" };
	private String[] auxilliaryCableStrings = { "Yes", "No"};
	
	JLabel CurrentLocation, Car, CarType2, Color2,
	SeatingCapacity2, TransmissionType2, NewLocation;
	JTextField CarType2TextField, Color2TextField,SeatingCapacity2TextField,
	TransmissionType2TextField;
	JComboBox CurrentLocationCombo, CarCombo, NewLocationCombo;
	
	private String[] currentLocationTypeStrings = {"Klaus"};//ADD VALUES!!! PLEASE CHANGE!!!
	private String[] carStrings = {"Honda Civic"};//ADD VALUES!!! PLEASE CHANGE!!!
	private String[] newLocationStrings = {"Graduate Living Center"};
	
	JButton submitChanges;
	
	public ManageCarsPanel(EmployeeUser employee) {
		this.mainFrame = MainFrame.getMain();
		this.employee = employee;
		
		this.setBackground(java.awt.Color.green);
		    this.setLayout(new BorderLayout());
		    setBounds(screenSize.width/2, screenSize.height, 
		                200, 0);
		
		ManageCars = new JLabel("Manage Cars");
		ManageCars.setFont(new Font("Helvetica", Font.BOLD, 70));
		
		this.add(ManageCars, BorderLayout.NORTH);

		JPanel TwoPanel = new JPanel();
		TwoPanel.setLayout(new GridLayout(1,2));
		
		JPanel AddCar = new JPanel();
		
		AddACar = new JLabel("Add A Car");
		AddACar.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		VehicleSno = new JLabel("Vehicle Sno:");
		CarModel = new JLabel("Car Model:");
		CarType = new JLabel("Car Type:");
		Location = new JLabel("Location:");
		Color = new JLabel("Color:");
		HourlyRate = new JLabel("Hourly Rate:");
		DailyRate = new JLabel("Daily Rate:");
		SeatingCapacity = new JLabel("Seating Capacity:");
		TransmissionType = new JLabel("Transmission Type:");
		BluetoothConnectivity = new JLabel("Bluetooth Connectivity:");
		AuxilliaryCable = new JLabel("Auxilliary Cable:");

		VehicleSnoTextField = new JTextField(20);//SET THE LENGTH OF THE TEXT FIELDS ACCORDINGLY
		CarModelTextField = new JTextField(20);//SET THE LENGTH OF THE TEXT FIELDS ACCORDINGLY
		ColorTextField = new JTextField(20);//SET THE LENGTH OF THE TEXT FIELDS ACCORDINGLY		
		HourlyRateTextField = new JTextField(20);//SET THE LENGTH OF THE TEXT FIELDS ACCORDINGLY
		DailyRateTextField = new JTextField(20);//SET THE LENGTH OF THE TEXT FIELDS ACCORDINGLY
		SeatingCapacityTextField = new JTextField(20);//SET THE LENGTH OF THE TEXT FIELDS ACCORDINGLY
		
		CarTypeCombo = new JComboBox(carTypeStrings);
		LocationCombo = new JComboBox(locationStrings);
		TransmissionTypeCombo = new JComboBox(transmissionTypeStrings);
		BluetoothConnectivityCombo = new JComboBox(bluetoothConnectivityStrings);
		AuxilliaryCableCombo = new JComboBox(auxilliaryCableStrings);
		
		add = new JButton("Add");
		add.addActionListener(new AddButtonListener());
		
		AddCar.add(AddACar);
		AddCar.add(VehicleSno);
		AddCar.add(VehicleSnoTextField);
		AddCar.add(CarModel);
		AddCar.add(CarModelTextField);
		AddCar.add(CarType);
		AddCar.add(CarTypeCombo);
		AddCar.add(Location);
		AddCar.add(LocationCombo);
		AddCar.add(Color);
		AddCar.add(ColorTextField);
		AddCar.add(HourlyRate);
		AddCar.add(HourlyRateTextField);
		AddCar.add(DailyRate);
		AddCar.add(DailyRateTextField);
		AddCar.add(SeatingCapacity);
		AddCar.add(SeatingCapacityTextField);
		AddCar.add(TransmissionType);
		AddCar.add(TransmissionTypeCombo);
		AddCar.add(BluetoothConnectivity);
		AddCar.add(BluetoothConnectivityCombo);
		AddCar.add(AuxilliaryCable);
		AddCar.add(AuxilliaryCableCombo);
		AddCar.add(add);
		
		JPanel ChangeCar = new JPanel();
		
		ChangeCarLocation = new JLabel("Change Car Location");
		ChangeCarLocation.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		CurrentLocation = new JLabel("Choose Current Location:");
		CurrentLocationCombo = new JComboBox(currentLocationTypeStrings);		
		Car = new JLabel("Choose Car:");
		CarCombo = new JComboBox(carStrings);
		BriefDescription = new JLabel("Brief Description");
		CarType2 = new JLabel("Car Type:");
		CarType2TextField = new JTextField(20);
		Color2 = new JLabel("Color:");
		Color2TextField = new JTextField(20);
		SeatingCapacity2 = new JLabel("Seating Capacity:");
		SeatingCapacity2TextField = new JTextField(20);
		TransmissionType2 = new JLabel("Transmission Type:");
		TransmissionType2TextField = new JTextField(20);
		NewLocation = new JLabel("Choose new location:");
		NewLocationCombo = new JComboBox(newLocationStrings);
		
		submitChanges = new JButton("Submit Changes");
		submitChanges.addActionListener(new SubmitButtonListener());
		
		ChangeCar.add(ChangeCarLocation);
		ChangeCar.add(CurrentLocation);
		ChangeCar.add(CurrentLocationCombo);
		ChangeCar.add(Car);
		ChangeCar.add(CarCombo);
		ChangeCar.add(BriefDescription);
		ChangeCar.add(CarType2);
		ChangeCar.add(CarType2TextField);
		ChangeCar.add(Color2);
		ChangeCar.add(Color2TextField);
		ChangeCar.add(SeatingCapacity2);
		ChangeCar.add(SeatingCapacity2TextField);
		ChangeCar.add(TransmissionType2);
		ChangeCar.add(TransmissionType2TextField);
		ChangeCar.add(NewLocation);
		ChangeCar.add(NewLocationCombo);
		ChangeCar.add(submitChanges);
		
		
		TwoPanel.add(AddCar);//HOPEFULLY CORRECT SYNTAX
		TwoPanel.add(ChangeCar);//HOPEFULLY CORRECT SYNTAX
		this.add(TwoPanel, BorderLayout.CENTER);
	}
	
	private class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
		}
	}
	
	private class SubmitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
		}
	}
}
