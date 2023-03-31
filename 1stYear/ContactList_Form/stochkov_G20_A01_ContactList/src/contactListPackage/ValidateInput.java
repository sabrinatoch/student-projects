/**
 * 
 */
package contactListPackage;

import javax.swing.JTextField;

/**
 * @author Sabrina Tochkov
 *
 */
public class ValidateInput {

	public boolean checkBirthDate(String bDay, String bMonth, String bYear) {
		boolean valid = true;
		int day = Integer.parseInt(bDay);
		int month = Integer.parseInt(bMonth);
		int year = Integer.parseInt(bYear);

		if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11))
			valid = false;
		else if (day == 30 && month == 2)
			valid = false;
		else if (day == 29 && month == 2 && year % 4 != 0)
			valid = false;

		return valid;
	} // checkBirthDate(String, String, String)

	public boolean checkBirthDate(String bDay, String bMonth) {
		boolean valid = true;
		int day = Integer.parseInt(bDay);
		int month = Integer.parseInt(bMonth);
		if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11))
			valid = false;
		else if (day == 30 && month == 2)
			valid = false;

		return valid;
	} // checkBirthDate(String, String)

	public boolean checkPostalCode(JTextField post) {
		boolean valid = true;
		String postalCode = post.getText();

		postalCode = postalCode.replaceAll("\\(", "");
		postalCode = postalCode.replaceAll("\\)", "");
		postalCode = postalCode.replaceAll("-", "");
		postalCode = postalCode.replaceAll(" ", "");

		if (postalCode.length() != 6)
			valid = false;
		else if (!Character.isAlphabetic(postalCode.charAt(0)) || !Character.isAlphabetic(postalCode.charAt(2))
				|| !Character.isAlphabetic(postalCode.charAt(4)))
			valid = false;
		else if (!Character.isDigit(postalCode.charAt(1)) || !Character.isDigit(postalCode.charAt(3))
				|| !Character.isDigit(postalCode.charAt(5)))
			valid = false;

		return valid;
	} // checkPostalCode(JTextField)

	public boolean checkEmail(JTextField mail) {
		boolean valid = true;
		String email = mail.getText();

		email = email.replaceAll(" ", "");

		if (!email.contains("@"))
			valid = false;
		else if (!email.substring(email.indexOf("@")).contains("."))
			valid = false;

		return valid;
	} // checkEmail(JTextField)

	public boolean checkPhoneNumber(JTextField phone) {
		boolean valid = true;
		String number = phone.getText();

		number = number.replaceAll("\\(", "");
		number = number.replaceAll("\\)", "");
		number = number.replaceAll("-", "");
		number = number.replaceAll(" ", "");

		for (int i = 0; i < number.length(); ++i) {
			if (!Character.isDigit(number.charAt(i)))
				valid = false;
		} // for loop
		if (number.length() != 10)
			valid = false;

		return valid;
	} // checkPhoneNumber(JTextField)

} // ValidateInput class
