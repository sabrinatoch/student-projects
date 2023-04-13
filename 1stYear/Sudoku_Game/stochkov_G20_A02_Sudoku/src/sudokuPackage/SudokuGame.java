/**
 * Description: The SudokuGame class contains the backend logic for the SudokuInterface & SudokuFrame classes.
 * 
 */
package sudokuPackage;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Sabrina Tochkov
 *
 */
public class SudokuGame {
	private static final int PUZZLE_SIZE = 10;
	private int puzzle[][] = new int[PUZZLE_SIZE][PUZZLE_SIZE];
	private int lastRow;
	private int lastColumn;
	private static String sudokuFilename;
	private static FileManager fileManager = new FileManager();

	public SudokuGame() throws IOException {
		sudokuFilename = "sudoku.txt";
		lastRow = 0;
		lastColumn = 0;
		setBoard();
	} // SudokuGame()

	public SudokuGame(String file) throws IOException {
		sudokuFilename = file;
		lastRow = 0;
		lastColumn = 0;
		setBoard();
	} // SudokuGame(String)

	public static boolean validateFile(String file) throws FileNotFoundException {
		if (fileManager.isValidFile(file))
			return true;
		else
			return false;
	} // validateFile(String)

	public void setBoard() throws IOException {
		File sudokuFile = new File(sudokuFilename);
		Scanner input = new Scanner(sudokuFile).useDelimiter("\r?\n");

		for (int i = 1; i < PUZZLE_SIZE; ++i) {
			StringTokenizer token = new StringTokenizer(input.nextLine(), "~");
			for (int j = 1; j < PUZZLE_SIZE; ++j) {
				String slot = token.nextToken();
				if (slot.equals("*"))
					puzzle[i][j] = 0;
				else
					puzzle[i][j] = Integer.parseInt(slot);
			} // while (token.hasMoreTokens())
		} // outer for
		input.close();
	} // setBoard()

	public boolean selectSquare(int r, int c) {
		if (puzzle[r][c] != 0)
			return false; // square occupied
		return true;
	} // selectSquare(int, int)

	public int setValue(int r, int c, int val) {
		int error = 0; // no errors
		if (isInRow(r, val))
			error = 1; // illegal move: num in row
		else if (isInColumn(c, val))
			error = 2; // illegal move: num in col
		else if (isInBox(r, c, val))
			error = 3; // illegal move: num in square
		else {
			puzzle[r][c] = val;
			lastRow = r;
			lastColumn = c;
		} // else
		return error;
	} // setValue(int)

	public boolean isInRow(int r, int v) {
		for (int c = 1; c < PUZZLE_SIZE; ++c) {
			if (puzzle[r][c] == v)
				return true;
		} // for each element in the row
		return false;
	} // isInRow()

	public boolean isInColumn(int c, int v) {
		for (int r = 1; r < PUZZLE_SIZE; ++r) {
			if (puzzle[r][c] == v)
				return true;
		} // for each element in the row
		return false;
	} // numInCol()

	public boolean isInBox(int r, int c, int v) {
		int boxRow = r - (r - 1) % 3;
		int boxCol = c - (c - 1) % 3;
		for (int i = boxRow; i < boxRow + 3; ++i) {
			for (int j = boxCol; j < boxCol + 3; ++j) {
				if (puzzle[i][j] == v)
					return true;
			} // for j
		} // for i
		return false;
	} // isInBox()

	public boolean undo() {
		boolean undone = true; // successful undo
		if (lastRow == 0 && lastColumn == 0)
			undone = false;
		else {
			puzzle[lastRow][lastColumn] = 0;
			lastRow = 0;
			lastColumn = 0;
		} // else
		return undone;
	} // undo()

	public boolean save() throws IOException {
		if (fileManager.saveToFile(puzzle, sudokuFilename))
			return true;
		else
			return false;
	} // save()

	public void quit() {
		System.exit(-1);
	} // quit()

	public boolean hasWon() {
		for (int i = 1; i < PUZZLE_SIZE; ++i)
			for (int j = 1; j < PUZZLE_SIZE; ++j)
				if (puzzle[i][j] == 0)
					return false;
		return true;
	} // hasWon()

	public int getLastRow() {
		return lastRow;
	} // getRow()

	public int getLastColumn() {
		return lastColumn;
	} // getColumn()

	public void setVal(int r, int c, int v) {
		puzzle[r][c] = v;
	} // setVal(int, int, int)

	public int getValue(int r, int c) {
		return puzzle[r][c];
	} // getPuzzle()

	public static int getPuzzleSize() {
		return PUZZLE_SIZE;
	} // getPuzzleSize()

	public String getSudokuFilename() {
		return sudokuFilename;
	} // getSudokuFilename()

} // SudokuGame class
