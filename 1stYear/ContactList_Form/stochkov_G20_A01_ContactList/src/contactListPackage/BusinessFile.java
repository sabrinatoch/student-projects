/**
 * 
 */
package contactListPackage;

import java.io.IOException;

/**
 * @author Sabrina Tochkov
 *
 */
public class BusinessFile extends ContactFile {

	public BusinessFile(String filename) {
		super(filename);
	} // BusinessFile(String)

	public boolean write(BusinessContact contact) {
		try {
			contactWriter.write(contact.getContactIdentifier() + "~" + contact.getLastName() + "~"
					+ contact.getFirstName() + "~" + contact.getBirthDay() + "~" + contact.getBirthMonth() + "~"
					+ contact.getBirthYear() + "~" + contact.getStreet() + "~" + contact.getCity() + "~"
					+ contact.getProvince() + "~" + contact.getPostalCode() + "~" + contact.getEmail() + "~"
					+ contact.getCellphoneNumber() + "~" + contact.getBusinessNumber() + "~" + contact.getCompanyName()
					+ "~" + contact.getDepartment() + "~" + contact.getJobTitle());
			contactWriter.write("\n");
		} // try
		catch (IOException e) {
			System.out.println("ERROR: Contact " + contact.getContactIdentifier() + " could not be written to file "
					+ contactFilename + ": " + e.getMessage());
			return false;
		} // catch (IOException)
		return true;
	} // write(BusinessContact)

} // BusinessFile class
