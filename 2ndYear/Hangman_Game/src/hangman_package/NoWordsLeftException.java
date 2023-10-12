/**
 * 
 */
package hangman_package;

/**
 * @author Sabrina Tochkov
 *
 */
public class NoWordsLeftException extends Exception {
	public NoWordsLeftException() {
		super("ERROR: There are no words left in the player's dictionary.");
	} // NoWordsLeftException()
} // NoWordsLeftException class