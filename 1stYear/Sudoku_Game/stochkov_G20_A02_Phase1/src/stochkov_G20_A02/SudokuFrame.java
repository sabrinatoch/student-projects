package stochkov_G20_A02;

import java.awt.*;
import java.awt.event.*;

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

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem saveMenuFile;
	private JMenuItem exitMenuItem;
	private JMenu infoMenu;
	private JMenuItem helpMenuItem;
	private JMenuItem aboutMenuItem;
	private JMenuItem undoMenuItem;

	private JLabel lblLogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuFrame frame = new SudokuFrame();
					frame.setVisible(true);
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
		title = new JLabel("Heritage Sudoku");
		title.setForeground(SystemColor.textHighlightText);
		title.setBounds(89, 0, 221, 33);
		title.setHorizontalAlignment(CENTER);
		title.setFont(new Font("Rockwell", Font.PLAIN, 18));
		this.getContentPane().add(title);

		btn1 = new JButton("1");
		btn1.setBackground(SystemColor.inactiveCaption);
		btn1.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn1.setBounds(406, 141, 48, 38);
		getContentPane().add(btn1);

		btn2 = new JButton("2");
		btn2.setBackground(SystemColor.inactiveCaption);
		btn2.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn2.setBounds(464, 141, 48, 38);
		getContentPane().add(btn2);

		btn3 = new JButton("3");
		btn3.setBackground(SystemColor.inactiveCaption);
		btn3.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn3.setBounds(521, 141, 48, 38);
		getContentPane().add(btn3);

		btn4 = new JButton("4");
		btn4.setBackground(SystemColor.inactiveCaption);
		btn4.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn4.setBounds(406, 190, 48, 38);
		getContentPane().add(btn4);

		btn5 = new JButton("5");
		btn5.setBackground(SystemColor.inactiveCaption);
		btn5.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn5.setBounds(464, 190, 48, 38);
		getContentPane().add(btn5);

		btn6 = new JButton("6");
		btn6.setBackground(SystemColor.inactiveCaption);
		btn6.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn6.setBounds(521, 190, 48, 38);
		getContentPane().add(btn6);

		btn7 = new JButton("7");
		btn7.setBackground(SystemColor.inactiveCaption);
		btn7.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn7.setBounds(406, 239, 48, 38);
		getContentPane().add(btn7);

		btn8 = new JButton("8");
		btn8.setBackground(SystemColor.inactiveCaption);
		btn8.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn8.setBounds(464, 239, 48, 38);
		getContentPane().add(btn8);

		btn9 = new JButton("9");
		btn9.setBackground(SystemColor.inactiveCaption);
		btn9.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn9.setBounds(521, 239, 48, 38);
		getContentPane().add(btn9);

		sudokuPanel = new JPanel();
		sudokuPanel.setBackground(Color.DARK_GRAY);
		sudokuPanel.setBounds(10, 37, 376, 317);
		getContentPane().add(sudokuPanel);

		btnUndo = new JButton("Undo");
		btnUndo.setBackground(SystemColor.textHighlightText);
		btnUndo.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnUndo.setBounds(449, 287, 80, 38);
		getContentPane().add(btnUndo);

		lblLogo = new JLabel(new ImageIcon("blue_logo.png"));
		lblLogo.setBounds(396, 37, 187, 85);
		getContentPane().add(lblLogo);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu("Game");
		menuBar.add(fileMenu);

		newGameMenuItem = new JMenuItem("New Game");
		fileMenu.add(newGameMenuItem);

		undoMenuItem = new JMenuItem("Undo");
		fileMenu.add(undoMenuItem);

		saveMenuFile = new JMenuItem("Save");
		fileMenu.add(saveMenuFile);

		exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);

		infoMenu = new JMenu("Information");
		menuBar.add(infoMenu);

		helpMenuItem = new JMenuItem("Help");
		infoMenu.add(helpMenuItem);

		aboutMenuItem = new JMenuItem("About");
		infoMenu.add(aboutMenuItem);
		sudokuButton = new JButton[9][9];

		initializeBoard();

		setLocationRelativeTo(null);
	} // SudokuFrame()

	public void initializeBoard() {
		sudokuPanel.setLayout(new GridLayout(9, 9));
		for (int r = 0; r < sudokuButton.length; ++r)
			for (int c = 0; c < sudokuButton[r].length; ++c) {
				sudokuButton[r][c] = new JButton("");
				if ((r >= 0 && r < 3) && (c > 2 && c < 6))
					sudokuButton[r][c].setBackground(SystemColor.inactiveCaption);
				else if ((r > 2 && r < 6) && (c >= 0 && c < 3))
					sudokuButton[r][c].setBackground(SystemColor.inactiveCaption);
				else if ((r > 2 && r < 6) && (c > 5 && c < 9))
					sudokuButton[r][c].setBackground(SystemColor.inactiveCaption);
				else if ((r > 5 && r < 9) && (c > 2 && c < 6))
					sudokuButton[r][c].setBackground(SystemColor.inactiveCaption);
				else
					sudokuButton[r][c].setBackground(SystemColor.activeCaption);

				sudokuButton[r][c].setFont(new Font("Rockwell", Font.PLAIN, 11));
				sudokuPanel.add(sudokuButton[r][c]);
				sudokuButton[r][c].addActionListener(this);
			} // inner for loop

	} // initializeBoard()

	@Override
	public void actionPerformed(ActionEvent e) {

	} // actionPerformed(ActionEvent)
} // SudokuFrame class
