package contactListPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import javax.swing.UIManager;

public class AddContactFrame extends JFrame implements WindowListener {

	private JPanel contentPane;
	private JTextField fldLastName;
	private JTextField fldFirstName;
	private JTextField fldStreet;
	private JTextField fldCity;
	private JTextField fldPostalCode;
	private JTextField fldEmail;
	private JTextField fldCellNumber;
	private JLabel lblGeneralInfo;
	private JLabel lblHomeNumber;
	private JLabel lblTwitter;
	private JLabel lblInstagram;
	private JLabel lblRelationship;
	private JTextField fldHomeNumber;
	private JTextField fldTwitter;
	private JTextField fldInstagram;
	private JButton btnAdd;
	private JLabel lblBusinessNumber;
	private JLabel lblCompany;
	private JLabel lblDepartment;
	private JLabel lblJobTitle;
	private JLabel lblLastName;
	private JLabel lblFirstName;
	private JLabel lblBirthDate;
	private JLabel lblStreet;
	private JLabel lblEmail;
	private JLabel lblCellNumber;
	private JTextField fldBusinessNumber;
	private JTextField fldCompany;
	private JTextField fldDepartment;
	private JTextField fldJobTitle;
	private JButton btnClear;
	private JButton btnAddPersonal;
	private JButton btnAddBusiness;
	private JLabel lblCity;
	private JLabel lblProvince;
	private JLabel lblPostalCode;
	private JLabel lblRequired;
	private JComboBox cmbxRelationship;
	private JComboBox cmbxProvince;

	private Contact contact;
	private ContactFile contactFile;
	private ValidateInput validator;
	private char contactType;
	private JComboBox cmbxDay;
	private JComboBox cmbxMonth;
	private JComboBox cmbxYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContactFrame frame = new AddContactFrame();
					frame.setVisible(true);
				} // try
				catch (Exception e) {
					e.printStackTrace();
				} // catch
			} // run
		});
	} // main()

	public AddContactFrame() {
		Contact.openNumberFile();
		contact = null;
		contactFile = null;
		validator = new ValidateInput();
		this.addWindowListener(this);

		setTitle("Add Contact");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 455);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLastName = new JLabel("*Last Name:");
		lblLastName.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblLastName.setBounds(16, 50, 98, 14);
		lblLastName.setForeground(Color.PINK);
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblLastName);

		lblFirstName = new JLabel("*First Name:");
		lblFirstName.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblFirstName.setBounds(266, 50, 107, 14);
		lblFirstName.setForeground(Color.PINK);
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFirstName);

		lblBirthDate = new JLabel("Birth Date:");
		lblBirthDate.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblBirthDate.setBounds(16, 79, 98, 14);
		lblBirthDate.setForeground(Color.WHITE);
		lblBirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblBirthDate);

		lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblStreet.setBounds(266, 78, 107, 14);
		lblStreet.setForeground(Color.WHITE);
		lblStreet.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblStreet);

		lblEmail = new JLabel("*Email:");
		lblEmail.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblEmail.setBounds(266, 138, 107, 14);
		lblEmail.setForeground(Color.PINK);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblEmail);

		lblCellNumber = new JLabel("Cell number:");
		lblCellNumber.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblCellNumber.setBounds(16, 169, 98, 14);
		lblCellNumber.setForeground(Color.WHITE);
		lblCellNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCellNumber);

		fldLastName = new JTextField();
		fldLastName.setBounds(125, 47, 155, 20);
		contentPane.add(fldLastName);
		fldLastName.setColumns(10);

		fldFirstName = new JTextField();
		fldFirstName.setBounds(384, 47, 155, 20);
		contentPane.add(fldFirstName);
		fldFirstName.setColumns(10);

		fldStreet = new JTextField();
		fldStreet.setBounds(384, 75, 155, 20);
		fldStreet.setForeground(Color.BLACK);
		fldStreet.setToolTipText("");
		contentPane.add(fldStreet);
		fldStreet.setColumns(10);

		fldCity = new JTextField();
		fldCity.setBounds(125, 106, 155, 20);
		contentPane.add(fldCity);
		fldCity.setColumns(10);

		fldPostalCode = new JTextField();
		fldPostalCode.setBounds(125, 135, 155, 20);
		contentPane.add(fldPostalCode);
		fldPostalCode.setColumns(10);

		fldEmail = new JTextField();
		fldEmail.setBounds(384, 135, 155, 20);
		contentPane.add(fldEmail);
		fldEmail.setColumns(10);

		fldCellNumber = new JTextField();
		fldCellNumber.setBounds(125, 166, 155, 20);
		contentPane.add(fldCellNumber);
		fldCellNumber.setColumns(10);

		lblGeneralInfo = new JLabel("General Contact Info");
		lblGeneralInfo.setOpaque(true);
		lblGeneralInfo.setBackground(SystemColor.windowBorder);
		lblGeneralInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneralInfo.setBounds(-3, 0, 552, 39);
		lblGeneralInfo.setForeground(Color.WHITE);
		lblGeneralInfo.setFont(new Font("Rockwell", Font.PLAIN, 16));
		contentPane.add(lblGeneralInfo);

		btnAddPersonal = new JButton("Personal Contact");
		btnAddPersonal.setForeground(Color.WHITE);
		btnAddPersonal.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btnAddPersonal.setBounds(126, 224, 154, 23);
		btnAddPersonal.setBackground(SystemColor.windowBorder);
		contentPane.add(btnAddPersonal);
		btnAddPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddPersonalContact();
			} // actionPerformed(ActionEvent) for btnAddPersonal
		}); // ActionListener for btnAddPersonal

		btnAddBusiness = new JButton("Business Contact");
		btnAddBusiness.setForeground(Color.WHITE);
		btnAddBusiness.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btnAddBusiness.setBounds(385, 224, 154, 23);
		btnAddBusiness.setBackground(SystemColor.windowBorder);
		contentPane.add(btnAddBusiness);
		btnAddBusiness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddBusinessContact();
			} // actionPerformed(ActionEvent) for btnAddPersonal
		}); // ActionListener for btnAddPersonal

		lblHomeNumber = new JLabel("Home #:");
		lblHomeNumber.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblHomeNumber.setBounds(-13, 258, 131, 14);
		lblHomeNumber.setEnabled(false);
		lblHomeNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHomeNumber.setForeground(Color.WHITE);
		contentPane.add(lblHomeNumber);

		lblTwitter = new JLabel("Twitter handle:");
		lblTwitter.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblTwitter.setBounds(-13, 282, 131, 14);
		lblTwitter.setEnabled(false);
		lblTwitter.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTwitter.setForeground(Color.WHITE);
		contentPane.add(lblTwitter);

		lblInstagram = new JLabel("Instagram user:");
		lblInstagram.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblInstagram.setBounds(-13, 307, 131, 14);
		lblInstagram.setEnabled(false);
		lblInstagram.setForeground(Color.WHITE);
		lblInstagram.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblInstagram);

		lblRelationship = new JLabel("*Relationship:");
		lblRelationship.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblRelationship.setBounds(-3, 331, 121, 14);
		lblRelationship.setEnabled(false);
		lblRelationship.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRelationship.setForeground(Color.PINK);
		contentPane.add(lblRelationship);

		fldHomeNumber = new JTextField();
		fldHomeNumber.setBounds(125, 255, 155, 20);
		contentPane.add(fldHomeNumber);
		fldHomeNumber.setColumns(10);

		fldTwitter = new JTextField();
		fldTwitter.setBounds(125, 279, 155, 20);
		contentPane.add(fldTwitter);
		fldTwitter.setColumns(10);

		fldInstagram = new JTextField();
		fldInstagram.setBounds(125, 304, 155, 20);
		contentPane.add(fldInstagram);
		fldInstagram.setColumns(10);

		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btnAdd.setBounds(351, 367, 89, 34);
		btnAdd.setForeground(SystemColor.window);
		btnAdd.setBackground(SystemColor.windowBorder);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddContact();
			} // actionPerformed(ActionEvent) for btnAdd
		}); // ActionListener for btnAdd

		lblBusinessNumber = new JLabel("Business #:");
		lblBusinessNumber.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblBusinessNumber.setBounds(228, 258, 145, 14);
		lblBusinessNumber.setEnabled(false);
		lblBusinessNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBusinessNumber.setForeground(Color.WHITE);
		contentPane.add(lblBusinessNumber);

		lblCompany = new JLabel("*Company:");
		lblCompany.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblCompany.setBounds(228, 282, 145, 14);
		lblCompany.setEnabled(false);
		lblCompany.setForeground(Color.PINK);
		lblCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCompany);

		lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblDepartment.setBounds(228, 307, 145, 14);
		lblDepartment.setEnabled(false);
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setForeground(Color.WHITE);
		contentPane.add(lblDepartment);

		lblJobTitle = new JLabel("Job Title:");
		lblJobTitle.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblJobTitle.setBounds(228, 328, 145, 14);
		lblJobTitle.setEnabled(false);
		lblJobTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJobTitle.setForeground(Color.WHITE);
		contentPane.add(lblJobTitle);

		fldBusinessNumber = new JTextField();
		fldBusinessNumber.setBounds(384, 255, 155, 20);
		contentPane.add(fldBusinessNumber);
		fldBusinessNumber.setColumns(10);

		fldCompany = new JTextField();
		fldCompany.setBounds(384, 279, 155, 20);
		contentPane.add(fldCompany);
		fldCompany.setColumns(10);

		fldDepartment = new JTextField();
		fldDepartment.setBounds(384, 302, 155, 20);
		contentPane.add(fldDepartment);
		fldDepartment.setColumns(10);

		fldJobTitle = new JTextField();
		fldJobTitle.setBounds(384, 325, 155, 20);
		contentPane.add(fldJobTitle);
		fldJobTitle.setColumns(10);

		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btnClear.setBounds(450, 367, 89, 34);
		btnClear.setForeground(SystemColor.window);
		btnClear.setBackground(SystemColor.windowBorder);
		contentPane.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllFields();
			} // actionPerformed(ActionEvent) for btnClear
		}); // ActionListener for btnClear

		lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblCity.setBounds(16, 108, 98, 14);
		lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCity.setForeground(Color.WHITE);
		contentPane.add(lblCity);

		lblProvince = new JLabel("Province:");
		lblProvince.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblProvince.setBounds(266, 107, 107, 14);
		lblProvince.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProvince.setForeground(Color.WHITE);
		contentPane.add(lblProvince);

		lblPostalCode = new JLabel("Postal Code:");
		lblPostalCode.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblPostalCode.setBounds(16, 138, 98, 14);
		lblPostalCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPostalCode.setForeground(Color.WHITE);
		contentPane.add(lblPostalCode);

		cmbxRelationship = new JComboBox();
		cmbxRelationship.setFont(new Font("Rockwell", Font.PLAIN, 13));
		cmbxRelationship.setBounds(125, 328, 155, 22);
		cmbxRelationship.setModel(new DefaultComboBoxModel(new String[] { "Unknown", "Spouse/Partner", "Family",
				"Friend", "Acquaintance", "Neighbour", "Other" }));
		cmbxRelationship.setToolTipText("");
		contentPane.add(cmbxRelationship);

		lblRequired = new JLabel("*Required");
		lblRequired.setHorizontalAlignment(SwingConstants.LEFT);
		lblRequired.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblRequired.setBounds(16, 377, 105, 14);
		lblRequired.setForeground(Color.PINK);
		contentPane.add(lblRequired);

		cmbxProvince = new JComboBox();
		cmbxProvince.setBounds(384, 103, 155, 22);
		cmbxProvince.setModel(new DefaultComboBoxModel(new String[] { "-", "Alberta", "British Columbia", "Manitoba",
				"New Brunswick", "Newfoundland & Labrador", "Northwest Territories", "Nova Scotia", "Nunavut",
				"Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon" }));
		contentPane.add(cmbxProvince);

		cmbxDay = new JComboBox();
		cmbxDay.setBounds(124, 75, 42, 22);
		cmbxDay.setModel(new DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31" }));
		contentPane.add(cmbxDay);

		cmbxMonth = new JComboBox();
		cmbxMonth.setBounds(166, 75, 42, 22);
		cmbxMonth.setModel(new DefaultComboBoxModel(
				new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		contentPane.add(cmbxMonth);

		cmbxYear = new JComboBox();
		cmbxYear.setBounds(206, 75, 74, 22);
		cmbxYear.setModel(new DefaultComboBoxModel(new String[] { "-", "2021", "2020", "2019", "2018", "2017", "2016",
				"2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003",
				"2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990",
				"1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977",
				"1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964",
				"1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951",
				"1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938",
				"1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925",
				"1924", "1923", "1922", "1921" }));
		contentPane.add(cmbxYear);

		enablePersonalFields(false);
		enableBusinessFields(false);
	} // AddContactFrame()

	public void clearAllFields() {
		fldHomeNumber.setText("");
		fldTwitter.setText("");
		fldInstagram.setText("");
		cmbxRelationship.setSelectedItem("Unknown");
		fldBusinessNumber.setText("");
		fldCompany.setText("");
		fldDepartment.setText("");
		fldJobTitle.setText("");

		enablePersonalFields(false);
		enableBusinessFields(false);

		fldLastName.setText("");
		fldFirstName.setText("");
		cmbxDay.setSelectedItem("-");
		cmbxMonth.setSelectedItem("-");
		cmbxYear.setSelectedItem("-");
		cmbxProvince.setSelectedItem("-");
		fldStreet.setText("");
		fldCity.setText("");
		fldPostalCode.setText("");
		fldEmail.setText("");
		fldCellNumber.setText("");
	} // clearAllFields()

	private void disableCommon(boolean enabled) {
		btnAddPersonal.setEnabled(!enabled);
		btnAddBusiness.setEnabled(!enabled);
		fldLastName.setEnabled(!enabled);
		fldFirstName.setEnabled(!enabled);
		fldStreet.setEnabled(!enabled);
		fldCity.setEnabled(!enabled);
		fldPostalCode.setEnabled(!enabled);
		fldEmail.setEnabled(!enabled);
		fldCellNumber.setEnabled(!enabled);
		cmbxDay.setEnabled(!enabled);
		cmbxMonth.setEnabled(!enabled);
		cmbxYear.setEnabled(!enabled);

		lblFirstName.setEnabled(!enabled);
		lblLastName.setEnabled(!enabled);
		lblBirthDate.setEnabled(!enabled);
		lblStreet.setEnabled(!enabled);
		lblCity.setEnabled(!enabled);
		lblProvince.setEnabled(!enabled);
		lblPostalCode.setEnabled(!enabled);
		lblEmail.setEnabled(!enabled);
		lblCellNumber.setEnabled(!enabled);

	} // disableCommon(boolean)

	private void enablePersonalFields(boolean enabled) {
		disableCommon(enabled);

		btnAdd.setEnabled(enabled);
		fldHomeNumber.setEnabled(enabled);
		fldTwitter.setEnabled(enabled);
		fldInstagram.setEnabled(enabled);
		cmbxRelationship.setEnabled(enabled);

		lblHomeNumber.setEnabled(enabled);
		lblTwitter.setEnabled(enabled);
		lblInstagram.setEnabled(enabled);
		lblRelationship.setEnabled(enabled);

	} // enablePersonalFields()

	private void enableBusinessFields(boolean enabled) {
		disableCommon(enabled);

		btnAdd.setEnabled(enabled);
		fldBusinessNumber.setEnabled(enabled);
		fldCompany.setEnabled(enabled);
		fldDepartment.setEnabled(enabled);
		fldJobTitle.setEnabled(enabled);

		lblBusinessNumber.setEnabled(enabled);
		lblCompany.setEnabled(enabled);
		lblDepartment.setEnabled(enabled);
		lblJobTitle.setEnabled(enabled);

	} // enableBusinessFields()

	public void checkCommonFields(char type) {
		String day = cmbxDay.getSelectedItem().toString();
		String month = cmbxMonth.getSelectedItem().toString();
		String year = cmbxYear.getSelectedItem().toString();

		if (fldLastName.getText().length() == 0)
			JOptionPane.showMessageDialog(this, "You must enter a last name.", "Last name missing",
					JOptionPane.ERROR_MESSAGE); // check last name
		else if (fldFirstName.getText().length() == 0)
			JOptionPane.showMessageDialog(this, "You must enter a first name.", "First name missing",
					JOptionPane.ERROR_MESSAGE); // check first name
		else if (fldEmail.getText().length() == 0)
			JOptionPane.showMessageDialog(this, "You must enter an email.", "Email missing", JOptionPane.ERROR_MESSAGE); // check
																															// email
		else if (!validator.checkEmail(fldEmail)) {
			JOptionPane.showMessageDialog(this, "The email is invalid. The format is johnsmith@email.com.",
					"Invalid Email", JOptionPane.ERROR_MESSAGE);
			fldEmail.setText("");
		} // else if email is invalid
		else if ((day != "-" && month != "-" && year != "-") && !validator.checkBirthDate(day, month, year)) {
			JOptionPane.showMessageDialog(this, "The birth date is invalid.", "Invalid Birth Date",
					JOptionPane.ERROR_MESSAGE);
			cmbxDay.setSelectedItem("-");
			cmbxMonth.setSelectedItem("-");
			cmbxYear.setSelectedItem("-");
		} // else if birth date is invalid
		else if ((day != "-" && month != "-" && year == "-") && !validator.checkBirthDate(day, month)) {
			JOptionPane.showMessageDialog(this, "The birth date is invalid.", "Invalid Birth Date",
					JOptionPane.ERROR_MESSAGE);
			cmbxDay.setSelectedItem("-");
			cmbxMonth.setSelectedItem("-");
			cmbxYear.setSelectedItem("-");
		} // else if birth date is invalid
		else if (!fldPostalCode.getText().isEmpty() && !validator.checkPostalCode(fldPostalCode)) {
			JOptionPane.showMessageDialog(this, "The postal code is invalid. The format is A1B 2C3.",
					"Invalid Postal Code", JOptionPane.ERROR_MESSAGE);
			fldPostalCode.setText("");
		} // else if postal code invalid
		else if (!fldCellNumber.getText().isEmpty() && !validator.checkPhoneNumber(fldCellNumber)) {
			JOptionPane.showMessageDialog(this, "The cellphone number is invalid. The format is 888-888-8888.",
					"Invalid Cellphone Number", JOptionPane.ERROR_MESSAGE);
			fldCellNumber.setText("");
		} // else if cell is invalid
		else {
			contactType = type;

			if (contactType == 'P')
				enablePersonalFields(true);
			else if (contactType == 'B')
				enableBusinessFields(true);
		} // else
	} // checkCommonFields

	public void btnAddPersonalContact() {
		checkCommonFields('P');
	} // btnAddPersonalContact()

	public void btnAddBusinessContact() {
		checkCommonFields('B');
	} // btnAddBusinessContact()

	public void setCommonFields() {
		String bDay = cmbxDay.getSelectedItem().toString();
		String bMonth = cmbxMonth.getSelectedItem().toString();
		String bYear = cmbxYear.getSelectedItem().toString();
		String province = cmbxProvince.getSelectedItem().toString();

		if (bDay == "-")
			contact.setBirthDay(0);
		else
			contact.setBirthDay(Integer.parseInt(bDay));

		if (bMonth == "-")
			contact.setBirthMonth(0);
		else
			contact.setBirthMonth(Integer.parseInt(bMonth));
		if (bYear == "-")
			contact.setBirthYear(0);
		else
			contact.setBirthYear(Integer.parseInt(bYear));

		if (!fldStreet.getText().isEmpty())
			contact.setStreet(fldStreet.getText());
		if (!fldCity.getText().isEmpty())
			contact.setCity(fldCity.getText());
		if (!fldPostalCode.getText().isEmpty())
			contact.setPostalCode(fldPostalCode.getText());
		if (!fldCellNumber.getText().isEmpty())
			contact.setCellphoneNumber(fldCellNumber.getText());

		if (province == "-")
			contact.setProvince("n/a");
		else
			contact.setProvince(province);
	} // setCommonFields()

	public void btnAddContact() {
		if (contactType == 'P') {
			if (!fldHomeNumber.getText().isEmpty() && !validator.checkPhoneNumber(fldHomeNumber)) {
				JOptionPane.showMessageDialog(this, "The homephone number is invalid. The format is 888-888-8888.",
						"Invalid Homephone Number", JOptionPane.ERROR_MESSAGE);
				fldHomeNumber.setText("");
			} // if home number is invalid
			else {
				contact = new PersonalContact(fldLastName.getText(), fldFirstName.getText(), fldEmail.getText(),
						cmbxRelationship.getSelectedItem().toString());
				contactFile = new PersonalFile("personalContacts.txt");
				setCommonFields();

				if (!fldHomeNumber.getText().isEmpty())
					((PersonalContact) contact).setHomeNumber(fldHomeNumber.getText());
				if (!fldTwitter.getText().isEmpty())
					((PersonalContact) contact).setTwitterHandle(fldTwitter.getText());
				if (!fldInstagram.getText().isEmpty())
					((PersonalContact) contact).setInstagramUser(fldInstagram.getText());

				if (((PersonalFile) contactFile).write((PersonalContact) contact)) {
					JOptionPane.showMessageDialog(this,
							"The personal contact was successfully added with contact ID "
									+ ((PersonalContact) contact).getContactIdentifier(),
							"Successfull Add", JOptionPane.INFORMATION_MESSAGE);
					clearAllFields();
					((PersonalFile) contactFile).flush();
				} // if written to the file
			} // else
		} // if (contactType == 'P')

		else if (contactType == 'B') {
			if (fldCompany.getText().length() == 0)
				JOptionPane.showMessageDialog(this, "You must enter a company name.", "Company name missing",
						JOptionPane.ERROR_MESSAGE);
			else if (!fldBusinessNumber.getText().isEmpty() && !validator.checkPhoneNumber(fldBusinessNumber)) {
				JOptionPane.showMessageDialog(this, "The business phone number is invalid. The format is 888-888-8888.",
						"Invalid Business Phone Number", JOptionPane.ERROR_MESSAGE);
				fldBusinessNumber.setText("");
			} // if business number is invalid
			else {
				contact = new BusinessContact(fldLastName.getText(), fldFirstName.getText(), fldEmail.getText(),
						fldCompany.getText());
				contactFile = new BusinessFile("businessContacts.txt");
				setCommonFields();

				if (!fldBusinessNumber.getText().isEmpty())
					((BusinessContact) contact).setBusinessNumber(fldBusinessNumber.getText());
				if (!fldDepartment.getText().isEmpty())
					((BusinessContact) contact).setDepartment(fldDepartment.getText());
				if (!fldJobTitle.getText().isEmpty())
					((BusinessContact) contact).setJobTitle(fldJobTitle.getText());
				((BusinessContact) contact).setCompanyName(fldJobTitle.getText());

				if (((BusinessFile) contactFile).write((BusinessContact) contact)) {
					JOptionPane.showMessageDialog(this,
							"The business contact was successfully added with contact ID "
									+ ((BusinessContact) contact).getContactIdentifier(),
							"Successfull Add", JOptionPane.INFORMATION_MESSAGE);
					clearAllFields();
					((BusinessFile) contactFile).flush();
				} // if written to the file
			} // else
		} // else if (contactType == 'B')

	} // btnAddContact()

	@Override
	public void windowOpened(WindowEvent e) {
	} // windowOpened(WindowEvent)

	@Override
	public void windowClosing(WindowEvent e) {
		// if the contactFile is never instantiated (a contact was never added)
		if (this.contactFile != null) {
			contactFile.close();
		} // if
		Contact.closeNumberFile();
	} // windowClosing(WindowEvent)

	@Override
	public void windowClosed(WindowEvent e) {
	} // windowClosed(WindowEvent)

	@Override
	public void windowIconified(WindowEvent e) {
	} // windowIconified(WindowEvent)

	@Override
	public void windowDeiconified(WindowEvent e) {
	} // windowDeiconified(WindowEvent)

	@Override
	public void windowActivated(WindowEvent e) {
	} // windowActivated(WindowEvent)

	@Override
	public void windowDeactivated(WindowEvent e) {
	} // windowDeactivated(WindowEvent)

} // AddContactFrame class
