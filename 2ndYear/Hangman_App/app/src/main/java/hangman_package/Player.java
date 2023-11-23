/**
 * 
 */
package hangman_package;

import android.content.Context;

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

	public Player(Context context) throws IOException {
		name = "Unknown";
		numberGamesPlayed = 0;
		numberGamesWon = 0;
		dict = new Dictionary(context);
	} // Player()

	public Player(String playerName, Context context) throws IOException {
		name = playerName;
		numberGamesPlayed = 0;
		numberGamesWon = 0;
		dict = new Dictionary(context);
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

	public void restartDictionary(Context context) throws IOException {
		dict.storeWordList(context);
	} // restardDictionary()

	public int getNumWordsLeft() {
		return dict.getNumberOfWords();
	} // getNumWordsLeft()

	public void removeWord() {
		dict.removeWord();
	} // removeWord()

	@Override
	public boolean equals(Object o) {
		if (o instanceof Player) {
			Player player = (Player) o;
			if (player.getName().equals(this.getName()))
				return true;
		} // Player obj
		return false;
	} // equals

} // Player class
