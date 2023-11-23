/**
 * 
 */
package hangman_package;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.Scanner;

import linked_data_structures.SinglyLinkedList;

/**
 * @author Sabrina Tochkov
 */

public class Dictionary implements Serializable {

	private SinglyLinkedList<String> wordList;
	int index;

	public Dictionary(Context context) throws IOException {
		wordList = new SinglyLinkedList<String>();
		storeWordList(context);
	} // Dictionary()

	public void storeWordList(Context context) throws IOException {
		Scanner input;
		//new Scanner(new File("wordFile.txt")).useDelimiter("\r?\n");
		input = new Scanner(new InputStreamReader(context.getAssets().open("wordFile.txt")));
		while (input.hasNext()) {
			String line = input.nextLine();
			if (isValidWord(line)) {
				line = Normalizer.normalize(line, Normalizer.Form.NFD);
				line = line.replaceAll("[^\\p{ASCII}]", "");
				wordList.add(line);
			} // if valid word
		} // while (input.hasNext())
	} // storeWordList()

	public boolean isValidWord(String word) {
		if (word.length() < 5 || word.length() > 15)
			return false;
		if (!word.matches("(?i)^[a-zëèéêáà.,!-' ]+$"))
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
