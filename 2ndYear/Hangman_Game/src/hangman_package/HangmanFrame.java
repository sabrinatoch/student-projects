package hangman_package;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.SystemColor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class HangmanFrame extends JFrame implements ActionListener, WindowListener {

	private JPanel contentPane;
	private JMenuItem exitMenuItem;
	private JLabel background;
	private JMenuBar menuBar;
	private JMenu gameMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem saveMenuItem;
	private JMenu viewMenu;
	private JMenuItem scoreMenuItem;
	private JMenuItem rulesMenuItem;
	private JLabel lblHangman;
	private JLabel lblWord;
	private JButton btnHint;
	private JPanel imagePanel;
	private JPanel buttonPanel;
	private JLabel label;
	private HangmanGame game;
	private Player player;
	private Scoreboard scoreboard;
	private JButton alphaButton[][];
	private ImageIcon heartImg1;
	private ImageIcon heartImg2;
	private ImageIcon heartImg3;
	private ImageIcon heartImg4;
	private ImageIcon heartImg5;
	private ImageIcon heartImg6;
	private JLabel lblHealth6;
	private JLabel lblHealth5;
	private JLabel lblHealth4;
	private JLabel lblHealth3;
	private JLabel lblHealth2;
	private JLabel lblHealth;
	private JLabel lblPlayer;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangmanFrame frame = new HangmanFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // catch
			} // run
		}); // Runnable
	} // main()

	public HangmanFrame() {

		// set up frame //

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 955, 566);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		background = new JLabel(new ImageIcon("images/background-image.png"));
		background.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		background.setBounds(0, 0, 941, 507);

		setContentPane(background);

		background.setLayout(new FlowLayout());

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);

		newGameMenuItem = new JMenuItem("New Game");
		gameMenu.add(newGameMenuItem);

		saveMenuItem = new JMenuItem("Save");
		gameMenu.add(saveMenuItem);

		exitMenuItem = new JMenuItem("Exit");
		gameMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			} // actionPerformed()
		});

		viewMenu = new JMenu("View");
		menuBar.add(viewMenu);

		scoreMenuItem = new JMenuItem("Scoreboard");
		viewMenu.add(scoreMenuItem);

		rulesMenuItem = new JMenuItem("Rules");
		viewMenu.add(rulesMenuItem);

		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		background.setLayout(null);

		lblHangman = new JLabel("HANGMAN");
		lblHangman.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHangman.setForeground(Color.WHITE);
		lblHangman.setFont(new Font("Forte", Font.BOLD, 39));
		lblHangman.setBounds(635, 10, 267, 83);
		background.add(lblHangman);
		
		lblPlayer = new JLabel("Player: ");
		lblPlayer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblPlayer.setBounds(600, 60, 267, 83);
		background.add(lblPlayer);

		lblWord = new JLabel("");
		lblWord.setForeground(Color.WHITE);
		lblWord.setFont(new Font("Rockwell", Font.BOLD, 25));
		lblWord.setBounds(40, 60, 567, 83);
		background.add(lblWord);

		btnHint = new JButton("Hint!");
		btnHint.setForeground(Color.WHITE);
		btnHint.setFont(new Font("Rockwell", Font.BOLD, 15));
		btnHint.setBackground(new Color(0, 128, 192));
		btnHint.setBounds(746, 410, 116, 41);
		background.add(btnHint);

		imagePanel = new JPanel();
		imagePanel.setBackground(Color.BLACK);
		imagePanel.setBounds(110, 151, 307, 270);
		imagePanel.setOpaque(false);
		background.add(imagePanel);

		ImageIcon img = new ImageIcon("images/cloaked.gif");
		imagePanel.setLayout(null);
		label = new JLabel(img);
		label.setBounds(40, 0, 250, 250);
		imagePanel.add(label);

		heartImg1 = new ImageIcon("images/heart.png");
		lblHealth = new JLabel(heartImg1);
		lblHealth.setBounds(0, 10, 50, 30);
		background.add(lblHealth);

		heartImg2 = new ImageIcon("images/heart.png");
		lblHealth2 = new JLabel(heartImg2);
		lblHealth2.setBounds(0, 10, 120, 30);
		background.add(lblHealth2);

		heartImg3 = new ImageIcon("images/heart.png");
		lblHealth3 = new JLabel(heartImg3);
		lblHealth3.setBounds(0, 10, 190, 30);
		background.add(lblHealth3);

		heartImg4 = new ImageIcon("images/heart.png");
		lblHealth4 = new JLabel(heartImg4);
		lblHealth4.setBounds(0, 10, 260, 30);
		background.add(lblHealth4);

		heartImg5 = new ImageIcon("images/heart.png");
		lblHealth5 = new JLabel(heartImg5);
		lblHealth5.setBounds(0, 10, 330, 30);
		background.add(lblHealth5);

		heartImg6 = new ImageIcon("images/heart.png");
		lblHealth6 = new JLabel(heartImg6);
		lblHealth6.setBounds(0, 10, 400, 30);
		background.add(lblHealth6);

		// set up alphabet buttons

		alphaButton = new JButton[6][7];
		buttonPanel = new JPanel();
		buttonPanel.setBounds(505, 160, 350, 200);
		buttonPanel.setLayout(new GridLayout(5, 5));
		buttonPanel.setOpaque(false);
		background.add(buttonPanel);

		int i = 0;
		for (int r = 1; r < alphaButton.length; ++r)
			for (int c = 1; c < alphaButton[r].length; ++c) {
				if (i < 26) {
					Character letter = (char) (i++ + 'A');
					alphaButton[r][c] = new JButton(letter.toString());
					alphaButton[r][c].setForeground(Color.WHITE);
					alphaButton[r][c].setBackground(Color.BLACK);
					alphaButton[r][c].setFont(new Font("Arial", Font.BOLD, 12));
					alphaButton[r][c].setPreferredSize(new Dimension(45, 45));
					alphaButton[r][c].addActionListener(this);
					buttonPanel.add(alphaButton[r][c]);
				} // if (i < 26)
			} // inner for

		setupGame();

	} // HangmanFrame()

	@Override
	public void actionPerformed(ActionEvent e) {
		outerloop: for (int r = 1; r < alphaButton.length; ++r)
			for (int c = 1; c < alphaButton[r].length; ++c) {
				if (e.getSource() == alphaButton[r][c]) {
					if (game.guessLetter(alphaButton[r][c].getText().toLowerCase().charAt(0)))
						displayWord();
					else {
						if (game.getnumBadGuesses() == 1) {
							lblHealth6.setIcon(new ImageIcon("images/heart_black.png"));
						} else if (game.getnumBadGuesses() == 2)
							lblHealth5.setIcon(new ImageIcon("images/heart_black.png"));
						else if (game.getnumBadGuesses() == 3)
							lblHealth4.setIcon(new ImageIcon("images/heart_black.png"));
						else if (game.getnumBadGuesses() == 4)
							lblHealth3.setIcon(new ImageIcon("images/heart_black.png"));
						else if (game.getnumBadGuesses() == 5)
							lblHealth2.setIcon(new ImageIcon("images/heart_black.png"));
						else if (game.getnumBadGuesses() == 6)
							lblHealth.setIcon(new ImageIcon("images/heart_black.png"));
					} // else
					alphaButton[r][c].setEnabled(false);
					alphaButton[r][c].setBackground(Color.DARK_GRAY);
					if (game.isComplete()) {
						if (game.hasWon())
							displayWon();
						else
							displayLost();
					} // if (game.isComplete();
					break outerloop;
				} // if button is selected
			} // inner for
		if (e.getSource() == btnHint) {
			// hint stuff
		} // if hint button
	} // actionPerformed(ActionEvent)

	public void checkScoreboard() {
		try {
			FileInputStream file = new FileInputStream("scoreboard.ser");
			ObjectInputStream in = new ObjectInputStream(file);
			scoreboard = (Scoreboard) in.readObject();
			in.close();
			file.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Welcome to Hangman (Ghoul Edition)!", "Hangman",
					JOptionPane.PLAIN_MESSAGE);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException caught " + e);
		} // catch (ClassNotFoundException
	} // checkScoreboard()

	public void resetGame() {
		serializeBoard();
		try {
			game = new HangmanGame(player);
		} catch (NoWordsLeftException e) {
			e.printStackTrace();
		} // catch (NoWordsLeftException)
		resetHearts();
		resetButtons();
		displayWord();
		System.out.println(game.getWord());
	} // resetHearts()

	public void setupGame() {
		checkScoreboard();
		if (scoreboard == null) {
			try {
				player = new Player("Sabrina");
			} catch (IOException e) {
				e.printStackTrace();
			} // catch (IOException)
			try {
				game = new HangmanGame(player);
			} catch (NoWordsLeftException e) {
				e.printStackTrace();
			} // catch (NoWordsLeftException)
			scoreboard = new Scoreboard();
			scoreboard.addPlayer(player);
		} // if no scoreboard yet
		else {
			try {
				player = scoreboard.getPlayerAt(0);
				game = new HangmanGame(player);
			} catch (NoWordsLeftException e) {
				e.printStackTrace();
			} // catch (NoWordsLeftException)
		} // else scoreboard is present

		lblPlayer.setText("Player: " + game.getPlayer().getName());
		displayWord();
		System.out.println(game.getWord());
	} // setupGame()

	public void displayWord() {
		String label = "Word: ";
		for (int i = 0; i < game.displayWordState().length(); i++) {
			if (game.displayWordState().charAt(i) == '-')
				label += " __ ";
			else
				label += " " + game.displayWordState().charAt(i) + " ";
		} // for
		lblWord.setText(label);
	} // displayWord()

	public void resetButtons() {
		int i = 0;
		for (int r = 1; r < alphaButton.length; ++r)
			for (int c = 1; c < alphaButton[r].length; ++c) {
				if (i++ < 26) {
					alphaButton[r][c].setEnabled(true);
					alphaButton[r][c].setBackground(Color.black);
				} // if
			} // inner for
	} // resetButtons()

	public void resetHearts() {
		lblHealth.setIcon(new ImageIcon("images/heart.png"));
		lblHealth2.setIcon(new ImageIcon("images/heart.png"));
		lblHealth3.setIcon(new ImageIcon("images/heart.png"));
		lblHealth4.setIcon(new ImageIcon("images/heart.png"));
		lblHealth5.setIcon(new ImageIcon("images/heart.png"));
		lblHealth6.setIcon(new ImageIcon("images/heart.png"));
	} // resetHearts()

	public void displayWon() {
		JOptionPane.showMessageDialog(this, "Congratulations! You survived the Ghoul!", "Game Won",
				JOptionPane.PLAIN_MESSAGE);
		displayPlayAgain();
	} // displayWon()

	public void displayLost() {
		JOptionPane.showMessageDialog(this,
				"Oh no! You were slain by the Ghoul! The word was " + game.getWord().toUpperCase() + ".", "Game Over",
				JOptionPane.PLAIN_MESSAGE);
		displayPlayAgain();
	} // displayLost()

	public void displayPlayAgain() {
//		final JOptionPane optionPane = new JOptionPane(
//			    "Play Again?",
//			    JOptionPane.YES_NO_OPTION);
		resetGame();
	} // displayPlayAgain()

	public void serializeBoard() {
		try {
			FileOutputStream file = new FileOutputStream("scoreboard.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(scoreboard);
			out.close();
			out.flush();
			file.flush();
			file.close();
		} catch (IOException ex) {
			System.out.println("IOException caught: " + ex);
		} // catch (IOException)
	} // serializeBoard()

	@Override
	public void windowClosing(WindowEvent e) {
		//serializeBoard();
	} // windowClosing(WindowEvent)

	@Override
	public void windowOpened(WindowEvent e) {

	} // windowOpened(WindowEvent)

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
} // HangmanFrame class
