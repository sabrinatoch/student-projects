/**
 * 
 */
package hangman_package;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author Sabrina Tochkov
 */

public class Player implements Serializable {

	private String name;
	private int numberGamesPlayed;
	private int numberGamesWon;
	private Dictionary dict;

	public Player() throws IOException {
		name = "Unknown";
		numberGamesPlayed = 0;
		numberGamesWon = 0;
		dict = new Dictionary();
	} // Player()

	public Player(String nm) throws IOException {
		name = nm;
		numberGamesPlayed = 0;
		numberGamesWon = 0;
		dict = new Dictionary();
	} // Player(String)

	public void setName(String nm) {
		name = nm;
	} // setName(String)

	public String getName() {
		return name;
	} // getName()

	public void setNumberGamesPlayed(int played) {
		numberGamesPlayed = played;
	} // setNumberGamesPlayed(int)

	public int getNumberGamesPlayed() {
		return numberGamesPlayed;
	} // getNumberGamesPlayed()

	public void setNumberGamesWon(int won) {
		numberGamesWon = won;
	} // setNumberGamesWon(int)

	public int getNumberGamesWon() {
		return numberGamesWon;
	} // getNumberGamesWon()

	// if this returns N/A, that means the player went through all the words in the
	// dictionary
	public String getNextWord() {
		return dict.generateRandomWord();
	} // getWord()

	public void restartDictionary() throws IOException {
		dict.storeWordList();
	} // restardDictionary()

	public void removeWord() {
		dict.removeWord();
	} // removeWord();

} // Player class
