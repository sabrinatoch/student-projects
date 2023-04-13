/**
 * Description: The SudokuFrame class contains the gameplay through an interactive JFrame.
 */
package sudokuPackage;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.font.TextAttribute;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SudokuFrame extends JFrame implements ActionListener, SwingConstants {

	private JPanel sudokuPanel;
	private JButton sudokuButton[][];
	private JLabel title;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnUndo;
	private JButton btnBack;

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;
	private JMenu infoMenu;
	private JMenuItem helpMenuItem;
	private JMenuItem aboutMenuItem;
	private SudokuGame sudoku;
	private JLabel lblLogo;

	private int row;
	private int column;
	private String sudokuFilename = "sudoku.txt";
	private String filename;
	private String directoryName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuFrame frame = new SudokuFrame();
				} // try
				catch (Exception e) {
					e.printStackTrace();
				} // catch
			} // run()
		});
	} // main(String[])

	/**
	 * Create the frame.
	 */
	public SudokuFrame() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 426);
		getContentPane().setLayout(null);
		title = new JLabel("<HTML><U>Heritage Sudoku</U></HTML>");
		title.setForeground(SystemColor.textHighlightText);
		title.setBounds(89, 0, 221, 33);
		title.setHorizontalAlignment(CENTER);
		title.setFont(new Font("Rockwell", Font.PLAIN, 19));
		this.getContentPane().add(title);

		btn1 = new JButton("1");
		btn1.setBackground(SystemColor.inactiveCaption);
		btn1.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn1.setBounds(406, 141, 48, 38);
		btn1.setEnabled(false);
		getContentPane().add(btn1);
		btn1.addActionListener(this);

		btn2 = new JButton("2");
		btn2.setBackground(SystemColor.inactiveCaption);
		btn2.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn2.setBounds(464, 141, 48, 38);
		btn2.setEnabled(false);
		getContentPane().add(btn2);
		btn2.addActionListener(this);

		btn3 = new JButton("3");
		btn3.setBackground(SystemColor.inactiveCaption);
		btn3.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn3.setBounds(521, 141, 48, 38);
		btn3.setEnabled(false);
		getContentPane().add(btn3);
		btn3.addActionListener(this);

		btn4 = new JButton("4");
		btn4.setBackground(SystemColor.inactiveCaption);
		btn4.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn4.setBounds(406, 190, 48, 38);
		btn4.setEnabled(false);
		getContentPane().add(btn4);
		btn4.addActionListener(this);

		btn5 = new JButton("5");
		btn5.setBackground(SystemColor.inactiveCaption);
		btn5.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn5.setBounds(464, 190, 48, 38);
		btn5.setEnabled(false);
		getContentPane().add(btn5);
		btn5.addActionListener(this);

		btn6 = new JButton("6");
		btn6.setBackground(SystemColor.inactiveCaption);
		btn6.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn6.setBounds(521, 190, 48, 38);
		btn6.setEnabled(false);
		getContentPane().add(btn6);
		btn6.addActionListener(this);

		btn7 = new JButton("7");
		btn7.setBackground(SystemColor.inactiveCaption);
		btn7.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn7.setBounds(406, 239, 48, 38);
		btn7.setEnabled(false);
		getContentPane().add(btn7);
		btn7.addActionListener(this);

		btn8 = new JButton("8");
		btn8.setBackground(SystemColor.inactiveCaption);
		btn8.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn8.setBounds(464, 239, 48, 38);
		btn8.setEnabled(false);
		getContentPane().add(btn8);
		btn8.addActionListener(this);

		btn9 = new JButton("9");
		btn9.setBackground(SystemColor.inactiveCaption);
		btn9.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btn9.setEnabled(false);
		btn9.addActionListener(this);
		btn9.setBounds(521, 239, 48, 38);
		getContentPane().add(btn9);

		sudokuPanel = new JPanel();
		sudokuPanel.setBackground(Color.DARK_GRAY);
		sudokuPanel.setBounds(10, 37, 376, 317);
		getContentPane().add(sudokuPanel);

		btnUndo = new JButton("Undo");
		btnUndo.setBackground(Color.GRAY);
		btnUndo.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btnUndo.setBounds(406, 287, 80, 38);
		btnUndo.setEnabled(false);
		getContentPane().add(btnUndo);
		btnUndo.addActionListener(this);

		btnBack = new JButton("Back");
		btnBack.setBackground(Color.GRAY);
		btnBack.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btnBack.setBounds(490, 287, 79, 38);
		btnBack.setEnabled(false);
		getContentPane().add(btnBack);
		btnBack.addActionListener(this);

		lblLogo = new JLabel(new ImageIcon("blue_logo.png"));
		lblLogo.setBounds(396, 37, 187, 85);
		getContentPane().add(lblLogo);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu("Game");
		menuBar.add(fileMenu);

		newGameMenuItem = new JMenuItem("New Game");
		fileMenu.add(newGameMenuItem);
		newGameMenuItem.addActionListener(this);

		saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		saveMenuItem.addActionListener(this);

		exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(this);

		infoMenu = new JMenu("Information");
		menuBar.add(infoMenu);

		helpMenuItem = new JMenuItem("Help");
		infoMenu.add(helpMenuItem);
		helpMenuItem.addActionListener(this);

		aboutMenuItem = new JMenuItem("About");
		infoMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(this);

		sudokuButton = new JButton[10][10];
		UIManager.getDefaults().put("Button.disabledText", Color.BLACK);
		setVisible(true);
		setLocationRelativeTo(null);

		initializeBoard();
	} // SudokuFrame()

	public void initializeBoard() {
		if (!initializeFile())
			selectGameFile();
		else {
			sudokuPanel.setLayout(new GridLayout(9, 9));
			for (int r = 1; r < sudokuButton.length; ++r)
				for (int c = 1; c < sudokuButton[r].length; ++c) {

					if (sudoku.getValue(r, c) != 0) {
						sudokuButton[r][c] = new JButton(String.valueOf(sudoku.getValue(r, c)));
						sudokuButton[r][c].setEnabled(false);
					} // if square is occupied
					else {
						sudokuButton[r][c] = new JButton("");
					} // else empty square
					sudokuButton[r][c].setFont(new Font("Rockwell", Font.PLAIN, 13));
					sudokuPanel.add(sudokuButton[r][c]);
					sudokuButton[r][c].addActionListener(this);

					// color for 3x3 squares //
					if ((r > 0 && r < 4 && c > 3 && c < 7) || (r > 3 && r < 7 && c > 0 && c < 4)
							|| (r > 3 && r < 7 && c > 6 && c < 10) || (r > 6 && r < 10 && c > 3 && c < 7))
						sudokuButton[r][c].setBackground(SystemColor.inactiveCaption);
					else
						sudokuButton[r][c].setBackground(SystemColor.activeCaption);
				} // inner for loop
			setVisible(true);
			enableBoard();
			if (sudoku.hasWon())
				JOptionPane.showMessageDialog(this, "Congratulations! You have become the Heritage Sudoku Master.",
						"Game Won", JOptionPane.PLAIN_MESSAGE);
		} // else valid file
	} // initializeBoard()

	public boolean initializeFile() {
		try {
			if (SudokuGame.validateFile(sudokuFilename)) {
				try {
					sudoku = new SudokuGame(sudokuFilename);
					return true;
				} // try
				catch (IOException e) {
					JOptionPane.showMessageDialog(this, "ERROR: Something went wrong while initializing the board.",
							"File Error", JOptionPane.ERROR_MESSAGE);
					return false;
				} // catch
			} // if valid file
			else {
				JOptionPane.showMessageDialog(this, "ERROR: " + filename + " does not have the correct file format.",
						"File Error", JOptionPane.ERROR_MESSAGE);
				return false;
			} // else
		} // try
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "ERROR: sudoku.txt does not exist.", "File Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} // catch
	} // initializeFile()

	@Override
	public void actionPerformed(ActionEvent e) {

		// selecting a square //
		outerloop: for (int r = 1; r < sudokuButton.length; ++r)
			for (int c = 1; c < sudokuButton[r].length; ++c) {
				if (e.getSource() == sudokuButton[r][c]) {
					row = r;
					column = c;
					selectSquare(row, column);
					break outerloop;
				} // if button is selected
			} // inner for

		// menu items //
		if (e.getSource() == newGameMenuItem) // new game
			selectGameFile();
		else if (e.getSource() == saveMenuItem) // save game
			saveGame();
		else if (e.getSource() == exitMenuItem) // exit
			System.exit(-1);
		else if (e.getSource() == aboutMenuItem) // about
			JOptionPane.showMessageDialog(this, new Sudoku_AboutPanel(), "About", JOptionPane.PLAIN_MESSAGE);
		else if (e.getSource() == helpMenuItem) // help
			JOptionPane.showMessageDialog(this, new Sudoku_HelpPanel(), "Help", JOptionPane.PLAIN_MESSAGE);

		// selecting a value //
		if (e.getSource() == btn1)
			makeMove(row, column, 1);
		else if (e.getSource() == btn2)
			makeMove(row, column, 2);
		else if (e.getSource() == btn3)
			makeMove(row, column, 3);
		else if (e.getSource() == btn4)
			makeMove(row, column, 4);
		else if (e.getSource() == btn5)
			makeMove(row, column, 5);
		else if (e.getSource() == btn6)
			makeMove(row, column, 6);
		else if (e.getSource() == btn7)
			makeMove(row, column, 7);
		else if (e.getSource() == btn8)
			makeMove(row, column, 8);
		else if (e.getSource() == btn9)
			makeMove(row, column, 9);
		else if (e.getSource() == btnUndo)
			undoLast();
		else if (e.getSource() == btnBack) {
			recolor();
			enableBoard();
		} // back

	} // actionPerformed(ActionEvent)

	public void selectSquare(int row, int col) {
		enableButtons();
		sudokuButton[row][col].setBackground(Color.ORANGE);
	} // selectSquare(int, int)

	public void makeMove(int row, int col, int value) {
		int code = sudoku.setValue(row, col, value);

		if (code == 0) {
			sudokuButton[row][col].setText(String.valueOf(value));
			sudokuButton[row][col].setBackground(Color.WHITE);
			enableBoard();
			btnUndo.setEnabled(true);
			btnUndo.setBackground(Color.WHITE);
			JOptionPane.showMessageDialog(this, "You placed a " + value + " in row " + row + ", column " + col + ".",
					"Move Made", JOptionPane.PLAIN_MESSAGE);
			if (sudoku.hasWon())
				JOptionPane.showMessageDialog(this, "Congratulations! You have become the Heritage Sudoku Master!",
						"Game Won", JOptionPane.PLAIN_MESSAGE);
		} // move made
		else if (code == 1) {
			JOptionPane.showMessageDialog(this, "Illegal Move: There is already a " + value + " in that row.",
					"Illegal Move", JOptionPane.ERROR_MESSAGE);
			recolor();
			enableBoard();
		} // in row
		else if (code == 2) {
			JOptionPane.showMessageDialog(this, "Illegal Move: There is already a " + value + " in that column.",
					"Illegal Move", JOptionPane.ERROR_MESSAGE);
			recolor();
			enableBoard();
		} // in column
		else if (code == 3) {
			JOptionPane.showMessageDialog(this, "Illegal Move: There is already a " + value + " in that square.",
					"Illegal Move", JOptionPane.ERROR_MESSAGE);
			recolor();
			enableBoard();
		} // in square
	} // makeMove(int, int, int)

	public void selectGameFile() {
		FileDialog sudokuFileDialog = new FileDialog(this, "Select Sudoku File", FileDialog.LOAD);
		sudokuFileDialog.setVisible(true);

		directoryName = sudokuFileDialog.getDirectory();
		filename = sudokuFileDialog.getFile();
		if (directoryName != null && filename != null) {
			sudokuFilename = directoryName + filename;
			if (!initializeFile())
				selectGameFile();
			else if (initializeFile() && sudokuButton[1][1] != null)
				newGame();
			else
				initializeBoard();
		} // if file not null
		else
			sudokuFileDialog.setVisible(false);
	} // selectGameFile()

	public void newGame() {
		btnUndo.setEnabled(false);
		btnUndo.setBackground(Color.GRAY);
		for (int r = 1; r < sudokuButton.length; ++r)
			for (int c = 1; c < sudokuButton[r].length; ++c)
				sudokuPanel.remove(sudokuButton[r][c]);
		initializeBoard();
	} // newGame()

	public void undoLast() {
		int lastR = sudoku.getLastRow();
		int lastC = sudoku.getLastColumn();
		if (sudoku.undo()) {
			JOptionPane.showMessageDialog(this, "Last move (" + lastR + ", " + lastC + ") undone!", "Undo Successful",
					JOptionPane.PLAIN_MESSAGE);
			sudokuButton[lastR][lastC].setText("");
			recolor();
			btnUndo.setBackground(Color.GRAY);
			sudokuButton[lastR][lastC].setEnabled(true);
			btnUndo.setEnabled(false);
		} // undo successful
		else if (!sudoku.undo())
			JOptionPane.showMessageDialog(this, "Cannot undo anymore.", "Cannot Undo", JOptionPane.ERROR_MESSAGE);
	} // undoLast()

	public void saveGame() {
		try {
			if (sudoku.save())
				JOptionPane.showMessageDialog(this, "Game saved to " + sudoku.getSudokuFilename() + "!", "Game Saved",
						JOptionPane.PLAIN_MESSAGE);
		} // try
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "ERROR: Could not save to " + sudoku.getSudokuFilename() + ".",
					"Game Not Saved", JOptionPane.ERROR_MESSAGE);
		} // catch
	} // saveGame()

	public void enableBoard() {
		for (int r = 1; r < sudokuButton.length; ++r)
			for (int c = 1; c < sudokuButton[r].length; ++c) {
				if (sudoku.getValue(r, c) != 0)
					sudokuButton[r][c].setEnabled(false);
				else
					sudokuButton[r][c].setEnabled(true);
			} // inner for
		btn1.setEnabled(false);
		btn1.setBackground(SystemColor.inactiveCaption);
		btn2.setEnabled(false);
		btn2.setBackground(SystemColor.inactiveCaption);
		btn3.setEnabled(false);
		btn3.setBackground(SystemColor.inactiveCaption);
		btn4.setEnabled(false);
		btn4.setBackground(SystemColor.inactiveCaption);
		btn5.setEnabled(false);
		btn5.setBackground(SystemColor.inactiveCaption);
		btn6.setEnabled(false);
		btn6.setBackground(SystemColor.inactiveCaption);
		btn7.setEnabled(false);
		btn7.setBackground(SystemColor.inactiveCaption);
		btn8.setEnabled(false);
		btn8.setBackground(SystemColor.inactiveCaption);
		btn9.setEnabled(false);
		btn9.setBackground(SystemColor.inactiveCaption);
		btnBack.setEnabled(false);
		btnBack.setBackground(Color.GRAY);
	} // enableBoard()

	public void enableButtons() {
		for (int r = 1; r < sudokuButton.length; ++r)
			for (int c = 1; c < sudokuButton[r].length; ++c)
				sudokuButton[r][c].setEnabled(false);
		btn1.setEnabled(true);
		btn1.setBackground(Color.ORANGE);
		btn2.setEnabled(true);
		btn2.setBackground(Color.ORANGE);
		btn3.setEnabled(true);
		btn3.setBackground(Color.ORANGE);
		btn4.setEnabled(true);
		btn4.setBackground(Color.ORANGE);
		btn5.setEnabled(true);
		btn5.setBackground(Color.ORANGE);
		btn6.setEnabled(true);
		btn6.setBackground(Color.ORANGE);
		btn7.setEnabled(true);
		btn7.setBackground(Color.ORANGE);
		btn8.setEnabled(true);
		btn8.setBackground(Color.ORANGE);
		btn9.setEnabled(true);
		btn9.setBackground(Color.ORANGE);
		btnBack.setEnabled(true);
		btnBack.setBackground(Color.WHITE);
	} // enableButtons()

	public void recolor() {
		if ((row > 0 && row < 4 && column > 3 && column < 7) || (row > 3 && row < 7 && column > 0 && column < 4)
				|| (row > 3 && row < 7 && column > 6 && column < 10)
				|| (row > 6 && row < 10 && column > 3 && column < 7))
			sudokuButton[row][column].setBackground(SystemColor.inactiveCaption);
		else
			sudokuButton[row][column].setBackground(SystemColor.activeCaption);
	} // recolor()
} // SudokuFrame class