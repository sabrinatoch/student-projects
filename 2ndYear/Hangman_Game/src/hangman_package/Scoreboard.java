/**
 * 
 */
package hangman_package;

import java.io.Serializable;

import linked_data_structures.DoublyLinkedList;

/**
 * @author Sabrina Tochkov
 *
 */
public class Scoreboard implements Serializable {

	private DoublyLinkedList<Player> players;
	private int numPlayers;

	public Scoreboard() {
		players = new DoublyLinkedList<Player>();
		numPlayers = 0;
	} // Scoreboard()

	public void setPlayers(DoublyLinkedList<Player> list) {
		players = list;
	} // setPlayers(DoublyLinkedList<Player>)

	public DoublyLinkedList<Player> getPlayers() {
		return players;
	} // getPlayers()

	public void setNumPlayers(int num) {
		numPlayers = num;
	} // setNumPlayers(int)

	public int getNumPlayers() {
		return numPlayers;
	} // getNumPlayers()

	public boolean addPlayer(Player player) {
		if (player == null)
			return false;
		players.add(player);
		++numPlayers;
		return true;
	} // addPlayer(Player)

	public boolean addGamePlayed(Player pl, boolean win) {
		if (players.find(pl) == null)
			return false;
		Player player = players.find(pl).getElement();
		player.setNumberGamesPlayed(player.getNumberGamesPlayed() + 1);
		if (win)
			player.setNumberGamesWon(player.getNumberGamesWon() + 1);
		return true;
	} // addGamePlayed()

	public Player getPlayerAt(int index) {
		if (index < 0 || index >= players.getLength())
			throw new IllegalArgumentException();
		return players.getElementAt(index);
	} // getPlayerAt(int)

	public void sortPlayers() {
		boolean sorted = false;
		int loopend = numPlayers - 1;

		while (loopend > 1 && !sorted) {
			sorted = true;
			for (int i = 0; i < loopend; ++i) {
				Player player1 = players.getElementAt(i);
				Player player2 = players.getElementAt(i + 1);
				if (player1.getName().compareToIgnoreCase(player2.getName()) > 0) {
					Player temp = player1;
					players.remove(i);
					players.add(player2, i);
					players.remove(i + 1);
					players.add(temp, i + 1);
					sorted = false;
				} // if
			} // for
			--loopend;
		} // while
	} // sortPlayers()
} // Scoreboard class
