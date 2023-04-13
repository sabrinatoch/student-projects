/**
 * Description: The SudokuInterface class holds the gameplay entirely in the console.
 */
package sudokuPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Sabrina Tochkov
 *
 */
public class SudokuInterface {

	/**
	 * @param args
	 */
	private static SudokuGame sudoku;
	private static Scanner in;

	public SudokuInterface() {
		SudokuGame sudoku = null;
		in = new Scanner(System.in);
	} // SudokuInterface()

	public static void main(String[] args) {

		SudokuInterface sudokuInterface = new SudokuInterface();

		System.out.println("Welcome to Heritage Sudoku!");

		// Prompt for file //

		System.out.print("Please enter the filename for your puzzle (if you don't have one, enter X): ");
		String filename = in.next();
		boolean validFile = false;
		while (!validFile) {
			if (filename.equalsIgnoreCase("X"))
				filename = "sudoku.txt";
			else {
				try {
					if (SudokuGame.validateFile(filename)) {
						try {
							sudoku = new SudokuGame(filename);
							validFile = true;
						} catch (IOException e) {
							System.out.println("ERROR: Something went wrong while setting the board.");
							System.out.print(
									"\nPlease enter the filename for your puzzle (if you don't have one, enter X): ");
							filename = in.next();
						} // catch
					} // if valid file
					else {
						System.out.println(filename + " does not have the correct file format.");
						System.out.print(
								"\nPlease enter the filename for your puzzle (if you don't have one, enter X): ");
						filename = in.next();
					} // else
				} // try
				catch (FileNotFoundException e) {
					System.out.println(filename + " does not exist.");
					System.out.print("\nPlease enter the filename for your puzzle (if you don't have one, enter X): ");
					filename = in.next();
				} // catch
			} // else
		} // while (!validFile)

		// Gameplay //

		hasWon: while (!sudoku.hasWon()) {
			sudokuInterface.displayBoard();
			System.out
					.println("\nType Q at any time to exit the game, S to save the game or U to undo your last move.");

			// Prompt for row //

			System.out.print("\nEnter a row number: ");
			String r = in.next();
			while (!sudokuInterface.validInput(r)) {
				sudokuInterface.displayBoard();
				System.out.print("\nInvalid row number. Please try again.\n\nEnter a row number: ");
				r = in.next();
			} // while invalid row

			// Prompt for columm //

			System.out.print("Enter a column number: ");
			String c = in.next();
			while (!sudokuInterface.validInput(c)) {
				sudokuInterface.displayBoard();
				System.out.print("\nInvalid column number. Please try again.\n\nEnter a column number: ");
				c = in.next();
			} // while invalid col

			int row = Integer.parseInt(r);
			int col = Integer.parseInt(c);

			// Check square availability //

			if (!sudoku.selectSquare(row, col)) {
				System.out.println(
						"That square (" + row + ", " + col + ") already has a value. Please select another square.");
				continue hasWon;
			} // if square has a value

			// Prompt for value

			System.out.print("Enter a value: ");
			String val = in.next();
			int value = 0;
			while (!sudokuInterface.validInput(val)) {
				sudokuInterface.displayBoard();
				System.out.print("\nInvalid value. Please try again.\n\nEnter a value: ");
				val = in.next();
			} // while invalid

			// Check legality of value //

			if (sudokuInterface.validInput(val)) {
				value = Integer.parseInt(val);
				int code = sudoku.setValue(row, col, value);
				if (code == 0) // set value
					System.out.println("You placed a " + value + " in row " + row + ", column " + col + ".");
				else if (code == 1) // row
					System.out.println("Illegal value, there is already a " + value + " in that row.");
				else if (code == 2) // col
					System.out.println("Illegal value, there is already a " + value + " in that column.");
				else if (code == 3) // square
					System.out.println("Illegal value, there is already a number " + value + " in that square.");
			} // if validateInput(val)

		} // while (!hasWon)

		// Win //

		sudokuInterface.displayBoard();
		System.out.println("\nCongratulations! You have become the Heritage Sudoku Master.\n");
		String ans = "";
		do {
			System.out.print("Would you like to save the game? (Y/N) ");
			ans = in.next();
		} while (!ans.equalsIgnoreCase("Y") && !ans.equalsIgnoreCase("N"));
		if (ans.equalsIgnoreCase("Y")) {
			try {
				if (sudoku.save())
					System.out.println("Game saved to " + sudoku.getSudokuFilename() + "!\n");
			} catch (IOException e) {
				System.out.println("Couldn't save to the file...");
			} // catch
		} // if Y

		do {
			System.out.print("Would you like to play again? (Y/N) ");
			ans = in.next();
		} while (!ans.equalsIgnoreCase("Y") && !ans.equalsIgnoreCase("N"));
		if (ans.equalsIgnoreCase("Y")) {
			System.out.println();
			main(args);
		} // if Y
		else {
			System.out.println("\nThanks for playing Heritage Sudoku. See you next time! :)");
			System.exit(-1);
		} // else
	} // main(String[])

	public boolean validInput(String str) {
		boolean valid = true;
		if (!Character.isDigit(str.charAt(0))) {
			if (!str.equalsIgnoreCase("S") && !str.equalsIgnoreCase("U") && !str.equalsIgnoreCase("Q"))
				valid = false;
			else {
				validChar(str);
				valid = false;
			} // else
		} // else if it's not a number
		else {
			try {
				int num = Integer.parseInt(str);
				if (num < 1 || num > 9)
					valid = false;
			} // try integer
			catch (NumberFormatException e) {
				valid = false;
			} // catch
		} // else
		return valid;
	} // validateInput()

	public void validChar(String ch) {
		if (ch.equalsIgnoreCase("S")) {
			try {
				if (sudoku.save())
					System.out.println("Game saved to " + sudoku.getSudokuFilename() + "!");
			} // try
			catch (IOException e) {
				System.out.println("ERROR: Couldn't save to the file.");
			} // catch
		} // if "S"
		else if (ch.equalsIgnoreCase("U")) {
			int lastR = sudoku.getLastRow();
			int lastC = sudoku.getLastColumn();
			if (sudoku.undo())
				System.out.println("Last move (" + lastR + ", " + lastC + ") undone!");
			else if (!sudoku.undo())
				System.out.println("ERROR: Cannot undo anymore.");
		} // else if "U"
		else if (ch.equalsIgnoreCase("Q")) {
			String an = "";
			do {
				System.out.print("Are you sure you want to quit? (Y/N) ");
				an = in.next();
			} while (!an.equalsIgnoreCase("Y") && !an.equalsIgnoreCase("N"));
			if (an.equalsIgnoreCase("Y")) {
				System.out.println("\nThanks for playing Heritage Sudoku. See you next time! :)");
				System.exit(-1);
			} // if Y
		} // else if "Q"
	} // validateChar(String)

	public void displayBoard() {
		System.out.println();
		for (int i = 1; i < SudokuGame.getPuzzleSize(); ++i) {
			for (int j = 1; j < SudokuGame.getPuzzleSize(); ++j) {
				if (j == 3 || j == 6) {
					if (sudoku.getValue(i, j) == 0)
						System.out.print("* | ");
					else
						System.out.print(sudoku.getValue(i, j) + " | ");
				} // if |
				else {
					if (sudoku.getValue(i, j) == 0)
						System.out.print("* ");
					else
						System.out.print(sudoku.getValue(i, j) + " ");
				} // else
			} // inner loop
			if (i == 3 || i == 6)
				System.out.println("\n------+-------+------");
			else
				System.out.println();
		} // outer loop
	} // displayBoard()

} // SudokuInterface class
