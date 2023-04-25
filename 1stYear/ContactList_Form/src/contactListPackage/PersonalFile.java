/**
 * 
 */
package contactListPackage;

import java.io.IOException;

/**
 * @author Sabrina Tochkov
 *
 */
public class PersonalFile extends ContactFile {

	public PersonalFile(String filename) {
		super(filename);
	} // PersonalFile(String)

	public boolean write(PersonalContact contact) {
		try {
			contactWriter.write(contact.getContactIdentifier() + "~" + contact.getLastName() + "~"
					+ contact.getFirstName() + "~" + contact.getBirthDay() + "~" + contact.getBirthMonth() + "~"
					+ contact.getBirthYear() + "~" + contact.getStreet() + "~" + contact.getCity() + "~"
					+ contact.getProvince() + "~" + contact.getPostalCode() + "~" + contact.getEmail() + "~"
					+ contact.getCellphoneNumber() + "~" + contact.getHomeNumber() + "~" + contact.getTwitterHandle()
					+ "~" + contact.getInstagramUser() + "~" + contact.getRelationship());
			contactWriter.write("\n");
		} // try
		catch (IOException e) {
			System.out.println("ERROR: Contact " + contact.getContactIdentifier() + " could not be written to file "
					+ contactFilename + ": " + e.getMessage());
			return false;
		} // catch (IOException)
		return true;
	} // write(PersonalContact)

} // PersonalFile class
