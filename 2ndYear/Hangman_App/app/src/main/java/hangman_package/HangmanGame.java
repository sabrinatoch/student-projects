/**
 * 
 */
package hangman_package;

import java.io.Serializable;
import java.util.Random;

import linked_data_structures.SinglyLinkedList;

/**
 * @author Sabrina Tochkov
 *
 */
public class HangmanGame implements Serializable {

	private Player player;
	private String word;
	private int numGuesses;

	private int numHintsUsed;
	private SinglyLinkedList<Character> letters;
	private SinglyLinkedList<Character> guessedLetters;

	private char hintChar;

	private HangmanGame() {
		// this is not allowed. cannot have a game without a player
	} // HangmanGame()

	public HangmanGame(Player pl) throws NoWordsLeftException {
		// player & word
		player = pl;
		word = player.getNextWord();
		if (word.equals("n/a"))
			throw new NoWordsLeftException();
		// lists
		letters = new SinglyLinkedList<Character>();
		guessedLetters = new SinglyLinkedList<Character>();
		parseWord(word);
		numGuesses = 6;
	} // HangmanGame()
	
	public void parseWord(String w) {
		for (int i = 0; i < w.length(); i++) {
			String ch = String.valueOf(w.charAt(i));
			if ((letters.find(ch.charAt(0)) == null)) {
				if (ch.matches("^[.,!-' ]{1}$"))
					guessedLetters.add(w.charAt(i));
				else
					letters.add(Character.toLowerCase(w.charAt(i)));
			} // if the character is not already in the list
		} // for

	} // parseWord(String)

	public boolean guessLetter(char letter) {
		guessedLetters.add(letter);
		guessedLetters.add(Character.toUpperCase(letter));
		for (int i = 0; i < letters.getLength(); i++) {
			if (letters.getElementAt(i) == Character.toLowerCase(letter)) {
				letters.remove(i);
				return true;
			} // if found
		} // loop through letters
		--numGuesses;
		return false;
	} // guessLetter(char)

	public String displayWordState() {
		String state = "";
		for (int i = 0; i < word.length(); i++) {
			if (guessedLetters.find(word.charAt(i)) != null)
				state += word.charAt(i);
			else
				state += "-";
		} // for
		return state;
	} // displayString()

	public boolean isComplete() {
		if (numGuesses == 0 || letters.getLength() == 0) {
			player.removeWord();
			return true;
		} // if done
		return false;
	} // isComplete()

	public boolean hasWon() {
		if (numGuesses == 0)
			return false;
		return true;
	} // hasWon()

	public boolean displayHint() {
		if (numGuesses == 1 || letters.getLength() == 1 || numHintsUsed == 5)
			return false;
		hintChar = '?';
		int index;
		while (hintChar == '?') {
			Random random = new Random();
			index = random.nextInt(letters.getLength());
			hintChar = letters.getElementAt(index);
			if (guessedLetters.find(hintChar) == null)
				break;
			else
				hintChar = '?';
		} // while
		guessLetter(hintChar);
		--numGuesses;
		return true;
	} // displayHint()

	public char getHintChar() {
		return hintChar;
	} // getHintChar()

	public void setPlayer(Player pl) {
		player = pl;
	} // setPlayer(Player)

	public Player getPlayer() {
		return player;
	} // getPlayer()

	public void setWord(String wo) {
		word = wo;
	} // setWord(String)

	public String getWord() {
		return word;
	} // getWord()

	public void setNumGuesses(int num) {
		numGuesses = num;
	} // setNumGuesses(int)

	public int getNumGuesses() {
		return numGuesses;
	} // getNumGuesses()

	public SinglyLinkedList<Character> getGuessedLetters() {
		return guessedLetters;
	} // getGuessedLetters()

} // HangmanGame class
