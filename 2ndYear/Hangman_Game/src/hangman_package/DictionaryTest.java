package hangman_package;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class DictionaryTest {

	@Test
	void isValidWordTest() throws IOException {
		Dictionary dict = new Dictionary();
		assertTrue(dict.isValidWord("hello"), "test for valid word 5 characters long");
		assertFalse(dict.isValidWord("h-ll@"), "test for special chars");
		assertTrue(dict.isValidWord("tangential"), "test for valid longer word");
		assertTrue(dict.isValidWord("Buckminsterfullerene"), "test for valid word 20 characters long");
		assertFalse(dict.isValidWord("hi"), "test for invalid word (too short)");
		assertFalse(dict.isValidWord("Buckminsterfullereneeeeeee"), "test for invalid word (too long)");
		assertFalse(dict.isValidWord("hi there"), "test for invalid word (includes spaces)");
	} // isValidWordTest()

	@Test
	void generateRandomWordTest() throws IOException {
		Dictionary dict = new Dictionary();
		int numWords = dict.getNumberOfWords();
		assertTrue(dict.generateRandomWord() != null, "test for generating a random word");
	} // generateRandomWordTest()

} // DictionaryTest class
