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
	private int numBadGuesses;
	private int numLettersLeft;
	private SinglyLinkedList<Character> letters;
	private SinglyLinkedList<Character> guessedLetters;

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
		for (int i = 0; i < word.length(); i++)
			letters.add(word.charAt(i));
		guessedLetters = new SinglyLinkedList<Character>();
		numBadGuesses = 0;
		numLettersLeft = letters.getLength();
	} // HangmanGame()

	public boolean guessLetter(char letter) {
		guessedLetters.add(letter);
		if (letters.find(letter) != null) {
			--numLettersLeft;
			return true;
		} // if
		++numBadGuesses;
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
		if (numBadGuesses < 6 && displayWordState().contains("-"))
			return false;
		else {
			player.removeWord();
			return true;
		}
	} // isComplete()

	public boolean hasWon() {
		if (numBadGuesses == 6)
			return false;
		return true;
	} // hasWon()

	public boolean displayHint() {
		if (numBadGuesses == 5 || numLettersLeft == 1)
			return false;
		char hintLetter = '?';
		int index;
		while (hintLetter == '?') {
			Random random = new Random();
			index = random.nextInt(letters.getLength());
			hintLetter = letters.getElementAt(index);
			if (guessedLetters.find(hintLetter) == null)
				break;
			else
				hintLetter = '?';
		} // while
		guessLetter(hintLetter);
		++numBadGuesses;
		return true;
	} // displayHint()

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

	public void setnumBadGuesses(int num) {
		numBadGuesses = num;
	} // setnumBadGuesses(int)

	public int getnumBadGuesses() {
		return numBadGuesses;
	} // getnumBadGuesses()

	public SinglyLinkedList<Character> getGuessedLetters() {
		return guessedLetters;
	} // getGuessedLetters()

} // HangmanGame class
