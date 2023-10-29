/**
 * 
 */
package hangman_package;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import linked_data_structures.SinglyLinkedList;

/**
 * @author Sabrina Tochkov
 */

public class Dictionary implements Serializable {

	private SinglyLinkedList<String> wordList;
	int index;

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
		int min = 0;
		int max = wordList.getLength() - 1;
		index = min + (int) (Math.random() * ((max - min) + 1));

		if (wordList.getLength() > 0) {
			String word = wordList.getElementAt(index);
			return word;
		} // if the list is not empty
		return "n/a";
	} // generateRandomWord()

	public void removeWord() {
		wordList.remove(index);
	} // removeWord()

	public int getNumberOfWords() {
		return wordList.getLength();
	} // getNumberOfWords()
} // Dictionary class
