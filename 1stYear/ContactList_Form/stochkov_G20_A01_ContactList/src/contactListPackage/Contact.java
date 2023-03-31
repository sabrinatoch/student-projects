/**
 * 
 */
package contactListPackage;

/**
 * @author Sabrina Tochkov
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Contact {

	public static int nextContactNumber;
	private static String contactNumberFilename;
	protected String contactIdentifier;
	protected String lastName;
	protected String firstName;
	protected int birthDay;
	protected int birthMonth;
	protected int birthYear;
	protected String streetName;
	protected String cityName;
	protected String province;
	protected String postalCode;
	protected String email;
	protected String cellphoneNumber;

	public Contact() {
		lastName = "n/a";
		firstName = "n/a";
		birthDay = 0;
		birthMonth = 0;
		birthYear = 0;
		streetName = "n/a";
		cityName = "n/a";
		province = "n/a";
		postalCode = "n/a";
		email = "n/a";
		cellphoneNumber = "n/a";
	} // Contact()

	public Contact(String last, String first, String mail) {
		lastName = last;
		firstName = first;
		email = mail;
		birthDay = 0;
		birthMonth = 0;
		birthYear = 0;
		streetName = "n/a";
		cityName = "n/a";
		province = "n/a";
		postalCode = "n/a";
		cellphoneNumber = "n/a";
	} // Contact(String, String, String)

	public Contact(String last, String first, int day, int month, int year, String street, String city, String prov,
			String post, String mail, String cell) {
		lastName = last;
		firstName = first;
		birthDay = day;
		birthMonth = month;
		birthYear = year;
		streetName = street;
		cityName = city;
		province = prov;
		postalCode = post;
		email = mail;
		cellphoneNumber = cell;
	} // Contact(String, String, int, int, int, String, String, String, String,
		// String, String)

	public abstract void setContactIdentifier();

	public String getContactIdentifier() {
		return contactIdentifier;
	} // getContactIdentifier()

	public static void initializeNextContactNumber() {
		File numberFile = new File(contactNumberFilename);
		Scanner in = null;
		try {
			in = new Scanner(numberFile);
		} // try
		catch (FileNotFoundException e) {
			System.out.println("ERROR: " + contactNumberFilename
					+ " was not found. The next contact number cannot be initialized.");
			System.exit(-1);
		} // catch (FileNotFoundException)
		catch (IOException e) {
			System.out.println("ERROR opening " + contactNumberFilename + ": " + e.getMessage());
			System.exit(-2);
		} // catch (IOException)
		nextContactNumber = in.nextInt();
	} // initializeNextContactNumber()

	public static void openNumberFile() {
		contactNumberFilename = "contactNumber.txt";
		initializeNextContactNumber();
	} // openNumberFile()

	public static void closeNumberFile() {
		File numberFile = new File(contactNumberFilename);
		try {
			FileWriter numberOut = new FileWriter(numberFile);
			numberOut.write(String.valueOf(nextContactNumber));
			numberOut.close();
		} // try
		catch (IOException e) {
			System.out.println("ERROR: Could not rewrite " + numberFile + " " + e.getMessage());
		} // catch (IOException)
	} // closeNumberFile()

	// Accessors and Mutators

	public void setLastName(String last) {
		lastName = last;
	} // setLastName(String)

	public void setFirstName(String first) {
		firstName = first;
	} // setFirstName(String)

	public void setBirthDay(int day) {
		birthDay = day;
	} // setBirthDay(int)

	public void setBirthMonth(int month) {
		birthMonth = month;
	} // setBirthMonth(int)

	public void setBirthYear(int year) {
		birthYear = year;
	} // setBirthYear(int)

	public void setStreet(String street) {
		streetName = street;
	} // setStreet(String)

	public void setCity(String city) {
		cityName = city;
	} // setCity(String)

	public void setProvince(String prov) {
		province = prov;
	} // setProvince(String)

	public void setPostalCode(String post) {
		postalCode = post;
	} // setPostalCode(String)

	public void setEmail(String mail) {
		email = mail;
	} // setEmail(String)

	public void setCellphoneNumber(String cell) {
		cellphoneNumber = cell;
	} // setCellphoneNumber(String)

	public String getLastName() {
		return lastName;
	} // getLastName()

	public String getFirstName() {
		return firstName;
	} // getFirstName()

	public int getBirthDay() {
		return birthDay;
	} // getBirthDay()

	public int getBirthMonth() {
		return birthMonth;
	} // getBirthMonth()

	public int getBirthYear() {
		return birthYear;
	} // getBirthYear()

	public String getStreet() {
		return streetName;
	} // getStreet()

	public String getCity() {
		return cityName;
	} // getCity()

	public String getProvince() {
		return province;
	} // getProvince()

	public String getPostalCode() {
		return postalCode;
	} // getPostalCode()

	public String getEmail() {
		return email;
	} // getEmail()

	public String getCellphoneNumber() {
		return cellphoneNumber;
	} // getCellphoneNumber()

} // Contact class
