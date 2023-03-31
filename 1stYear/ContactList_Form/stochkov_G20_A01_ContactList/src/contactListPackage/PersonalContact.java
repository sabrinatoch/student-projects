/**
 * 
 */
package contactListPackage;

/**
 * @author Sabrina Tochkov
 *
 */
public class PersonalContact extends Contact {

	private String homeNumber;
	private String twitterHandle;
	private String instagramUser;
	private int relationship;

	public PersonalContact() {
		super();
		homeNumber = "n/a";
		twitterHandle = "n/a";
		instagramUser = "n/a";
		relationship = 0;
		setContactIdentifier();
	} // PersonalContact()

	public PersonalContact(String last, String first, String mail, String relation) {
		super(last, first, mail);
		homeNumber = "n/a";
		twitterHandle = "n/a";
		instagramUser = "n/a";
		setRelationship(relation);
		setContactIdentifier();
	} // PersonalContact(String, String, String, int)

	public PersonalContact(String last, String first, int day, int month, int year, String street, String city,
			String prov, String post, String mail, String cell, String homeNum, String twitter, String insta,
			int relation) {
		super(last, first, day, month, year, street, city, prov, post, mail, cell);
		homeNumber = homeNum;
		twitterHandle = twitter;
		instagramUser = insta;
		relationship = relation;
		setContactIdentifier();
	} // PersonalContact(String, String, int, int, int, String, String, String,
		// String, String, String, String, String, String, int)

	public void setContactIdentifier() {
		contactIdentifier = String.valueOf(nextContactNumber);
		contactIdentifier = "P" + contactIdentifier;
		++nextContactNumber;
	} // setContactIdentifier()

	// Accessors and Mutators

	public void setHomeNumber(String homeNum) {
		homeNumber = homeNum;
	} // setHomeNumber(String)

	public void setTwitterHandle(String twitter) {
		twitterHandle = twitter;
	} // setTwitterHandle(String)

	public void setInstagramUser(String insta) {
		instagramUser = insta;
	} // setInstagramUser(String)

	public void setRelationship(String relation) {
		if (relation.equalsIgnoreCase("Unknown"))
			relationship = 0;
		else if (relation.equalsIgnoreCase("Spouse/Partner"))
			relationship = 1;
		else if (relation.equalsIgnoreCase("Family"))
			relationship = 2;
		else if (relation.equalsIgnoreCase("Friend"))
			relationship = 3;
		else if (relation.equalsIgnoreCase("Acquaintance"))
			relationship = 4;
		else if (relation.equalsIgnoreCase("Neighbour"))
			relationship = 5;
		else if (relation.equalsIgnoreCase("Other"))
			relationship = 99;
	} // setRelationship(int)

	public String getHomeNumber() {
		return homeNumber;
	} // getHomeNumber()

	public String getTwitterHandle() {
		return twitterHandle;
	} // getTwitterHandle()

	public String getInstagramUser() {
		return instagramUser;
	} // getInstagramUser()

	public int getRelationship() {
		return relationship;
	} // getRelationship()
} // PersonalContact class
