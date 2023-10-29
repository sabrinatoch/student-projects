package hangman_package;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ScoreboardTest {

	@Test
	void constructorTest() {
		Scoreboard score = new Scoreboard();
		assertEquals(score.getNumPlayers(), 0, "numPlayers must be 0 but it's " + score.getNumPlayers());
		assertEquals(score.getPlayers().getLength(), 0,
				"length of linked list must be 0 but it's " + score.getPlayers().getLength());
	} // constructortest()

	@Test
	void addPlayerTest() throws IOException {
		Scoreboard score = new Scoreboard();
		score.addPlayer(new Player("Sabrina"));

		assertTrue(score.getPlayerAt(0).getName().equals("Sabrina"),
				"test for adding a new player" + score.getPlayerAt(0).getName());
		assertEquals(score.getPlayerAt(0).getNumberGamesPlayed(), 0, "test for number of games played");
		assertEquals(score.getPlayerAt(0).getNumberGamesWon(), 0, "test for number of games won");
	} // addPlayerTest()

	@Test
	void addGamePlayedTest() throws IOException {
		Scoreboard score = new Scoreboard();
		Player player = new Player("Sabrina");
		score.addPlayer(player);
		score.addGamePlayed(player, true);
		score.addGamePlayed(player, false);

		assertEquals(score.getPlayerAt(0).getNumberGamesPlayed(), 2, "test for number of games played");
		assertEquals(score.getPlayerAt(0).getNumberGamesWon(), 1, "test for number of games won");
		assertFalse(score.addGamePlayed(new Player("Not a player"), false), "test for player that DNE");
	} // addGamePlayedTest()

	@Test
	void getPlayerAtTest() throws IOException {
		Scoreboard score = new Scoreboard();
		Player player = new Player("Sabrina");
		score.addPlayer(player);
		assertThrows(IllegalArgumentException.class, () -> {
			score.getPlayerAt(-1);
		}, "should throw an IllegalArgumentException");
		assertThrows(IllegalArgumentException.class, () -> {
			score.getPlayerAt(2);
		}, "should throw an IllegalArgumentException");
		assertEquals(score.getPlayerAt(0), player, "should return the player");
	} // getNextPlayerTest()

} // ScoreboardTest class
