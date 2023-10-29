package hangman_package;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class HangmanGameTest {

	@Test
	void guessLetterTest() throws NoWordsLeftException, IOException {
		HangmanGame game = new HangmanGame(new Player("Sabrina"));
		String word = game.getWord();
		boolean result;
		if (word.indexOf('c') == -1)
			result = false;
		else
			result = true;
		assertEquals(game.guessLetter('c'), result, "test for guessing a letter");
		assertEquals(game.getGuessedLetters().getLength(), 1, "test for adding to the list of guessed letters");
	} // guessLetterTest()

} // HangmanGameTest class
