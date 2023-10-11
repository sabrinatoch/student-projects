package hangman_package;

import java.io.IOException;

public class Tester {

	public static void main(String[] args) {
		Player player = null;
		try {
			player = new Player("Sabrina");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 40; ++i) {
			System.out.println(player.getNextWord());
		}
	} // main()

} // Tester class
