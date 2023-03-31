/**
 * 
 */
package contactListPackage;

/**
 * @author Sabrina Tochkov
 *
 */
public class BusinessContact extends Contact {
	private String businessNumber;
	private String companyName;
	private String department;
	private String jobTitle;

	public BusinessContact() {
		super();
		businessNumber = "n/a";
		companyName = "n/a";
		department = "n/a";
		jobTitle = "n/a";
		setContactIdentifier();
	} // BusinessContact()

	public BusinessContact(String last, String first, String mail, String company) {
		super(last, first, mail);
		businessNumber = "n/a";
		companyName = company;
		department = "n/a";
		jobTitle = "n/a";
		setContactIdentifier();
	} // BusinessContact(String, String, String, String)

	public BusinessContact(String last, String first, int day, int month, int year, String street, String city,
			String prov, String post, String mail, String cell, String businessNum, String company, String depart,
			String job) {
		super(last, first, day, month, year, street, city, prov, post, mail, cell);
		businessNumber = businessNum;
		companyName = company;
		department = depart;
		jobTitle = job;
		setContactIdentifier();
	} // BusinessContact(String, String, int, int, int, String, String, String,
		// String, String, String, String, String, String, String)

	public void setContactIdentifier() {
		contactIdentifier = String.valueOf(nextContactNumber);
		contactIdentifier = "B" + contactIdentifier;
		++nextContactNumber;
	} // setContactIdentifier()

	public void setBusinessNumber(String businessNum) {
		businessNumber = businessNum;
	} // setBusinessNumber(String)

	public void setCompanyName(String company) {
		companyName = company;
	} // setCompanyName(String)

	public void setDepartment(String depart) {
		department = depart;
	} // setDepartment(String)

	public void setJobTitle(String job) {
		jobTitle = job;
	} // setJobTitle(String)

	public String getBusinessNumber() {
		return businessNumber;
	} // getBusinessNumber()

	public String getCompanyName() {
		return companyName;
	} // getCompanyName()

	public String getDepartment() {
		return department;
	} // getDepartment()

	public String getJobTitle() {
		return jobTitle;
	} // getJobTitle()

} // BusinessContact class
