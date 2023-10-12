package hangman_package;

import java.io.IOException;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		Player player = null;
		HangmanGame game = null;
		try {
			player = new Player("Sabrina");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			game = new HangmanGame(player);
		} catch (NoWordsLeftException e) {
			e.printStackTrace();
		}
		
		System.out.println(game.getWord());
		System.out.print("Enter a letter: ");
		String letter = keyboard.next();
		
		game.guessLetter(letter.charAt(0));
		System.out.println(game.displayWordState());
		
		
	} // main()

} // Tester class
