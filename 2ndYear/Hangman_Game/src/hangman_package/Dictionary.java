/**
 * 
 */
package hangman_package;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import linked_data_structures.SinglyLinkedList;

/**
 * @author Sabrina Tochkov
 *
 */
public class Dictionary {

	private SinglyLinkedList<String> wordList;

	public Dictionary() throws IOException {
		wordList = new SinglyLinkedList<String>();
		storeWordList();
	} // Dictionary()

	public void storeWordList() throws IOException {
		Scanner input = new Scanner(new File("wordFile.txt")).useDelimiter("\r?\n");
		while (input.hasNext()) {
			String line = input.nextLine();
			if (isValidWord(line))
				wordList.add(line);
		} // while (input.hasNext())
	} // storeWordList()

	public boolean isValidWord(String word) {
		if (word.length() < 5 || word.length() > 20)
			return false;
		if (!word.matches("^[a-zA-Z]+$"))
			return false;
		return true;
	} // isValidWord(String)

	public String generateRandomWord() {
		if (wordList.getLength() > 0) {
			String word = wordList.getElementAt(0);
			wordList.remove(0); // delete word
			return word;
		}
		return "n/a";
	} // generateRandomWord()

} // Dictionary class
