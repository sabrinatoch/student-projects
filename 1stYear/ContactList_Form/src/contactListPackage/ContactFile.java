/**
 * 
 */
package contactListPackage;

/**
 * @author Sabrina Tochkov
 *
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class ContactFile {

	protected String contactFilename;
	protected FileWriter contactWriter;

	public ContactFile(String filename) {
		open(filename);
	} // ContactFile(String)

	public void open(String filename) {
		contactFilename = filename;
		File contactFile = new File(contactFilename);
		try {
			contactWriter = new FileWriter(contactFile, true);
		} // try
		catch (IOException e) {
			System.out.println("ERROR: File " + contactFilename + " could not be opened: " + e.getMessage());
		} // catch (IOException)
	} // open(String)

	public void close() {
		try {
			contactWriter.close();
		} // try
		catch (IOException e) {
			System.out.println("ERROR: File " + contactFilename + " could not be closed: " + e.getMessage());
		} // catch (IOException)
	} // close()

	public void flush() {
		try {
			contactWriter.flush();
		} catch (IOException e) {
			System.out.println("ERROR: File " + contactFilename + " could not be flushed: " + e.getMessage());
		}
	}

} // ContactFile class
