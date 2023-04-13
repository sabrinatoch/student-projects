/**
 * Description: The FileManager class contains all methods necessary for reading from and writing to the file.
 * The methods below are called only in the SudokuGame class.
 */
package sudokuPackage;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Sabrina Tochkov
 *
 */
public class FileManager {

	public boolean isValidFile(String file) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(file)).useDelimiter("\r?\n");
		int numCol = 0;
		while (reader.hasNext()) {
			String line = reader.nextLine();
			if (line.length() != 18)
				return false;
			StringTokenizer token = new StringTokenizer(line, "~");
			if (token.countTokens() != 9)
				return false;
			while (token.hasMoreTokens()) {
				String slot = token.nextToken();
				if (slot.length() != 1)
					return false;
				else if (!Character.isDigit(slot.charAt(0)) && !slot.equals("*"))
					return false;
				else if (slot.equals("*"))
					continue;
				else if (Character.isDigit(slot.charAt(0))) {
					int numericSlot = Integer.parseInt(slot);
					if (numericSlot < 1 || numericSlot > 9)
						return false;
				} // if slot is a digit
			} // while (token.hasMoreTokens())
			++numCol;
		} // while (reader.hasNext()
		if (numCol != SudokuGame.getPuzzleSize() - 1)
			return false;
		else if (isRuleBreaker(file))
			return false;
		else
			return true;
	} // isValidFile(String)

	public boolean isRuleBreaker(String filename) {
		boolean breaks = false;
		SudokuGame test = null;
		try {
			test = new SudokuGame(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		outer: for (int r = 1; r < SudokuGame.getPuzzleSize(); ++r) {
			for (int c = 1; c < SudokuGame.getPuzzleSize(); ++c) {
				int value = test.getValue(r, c);
				test.setVal(r, c, 0);
				if (value != 0 && (test.isInRow(r, value) || test.isInColumn(c, value) || test.isInBox(r, c, value))) {
					breaks = true;
					break outer;
				} // if breaks rules
				test.setVal(r, c, value);
			} // inner loop
		} // outer loop
		return breaks;
	} // isRuleBreaker(String)

	public boolean saveToFile(int board[][], String file) throws IOException {
		boolean valid = true;
		FileWriter writer = new FileWriter(file);
		for (int i = 1; i < board.length; ++i) {
			for (int j = 1; j < board[i].length; ++j) {
				if (j == 9) {
					if (board[i][j] == 0)
						writer.write("*~\n");
					else
						writer.write(String.valueOf(board[i][j]) + "~\n");
				} // if end of line
				else {
					if (board[i][j] == 0)
						writer.write("*~");
					else
						writer.write(String.valueOf(board[i][j]) + "~");
				} // else
			} // inner loop
		} // outer loop
		writer.flush();
		writer.close();
		return valid;
	} // saveToFile()

} // FileManager class
