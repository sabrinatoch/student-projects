package hangman_package;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class HangmanFrame extends JFrame implements ActionListener {

	private JMenuItem exitMenuItem;
	private JLabel background;
	private JMenuBar menuBar;
	private JMenu gameMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem newPlayerMenuItem;
	private JMenu viewMenu;
	private JMenuItem scoreMenuItem;
	private JMenuItem rulesMenuItem;
	private JLabel lblHangman;
	private JLabel lblWord;
	private JButton btnHint;
	private JPanel imagePanel;
	private JPanel buttonPanel;
	private JLabel label;
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
	private JLabel lblHP;
	private ImageIcon img;
	private HangmanGame game;
	private static Player player;
	private static Scoreboard scoreboard;
	private static LoginFrame login;
	private static HangmanFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					displayLoginFrame();
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
		newGameMenuItem.addActionListener(this);
		gameMenu.add(newGameMenuItem);

		newPlayerMenuItem = new JMenuItem("New Player");
		newPlayerMenuItem.addActionListener(this);
		gameMenu.add(newPlayerMenuItem);

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
		scoreMenuItem.addActionListener(this);
		viewMenu.add(scoreMenuItem);

		rulesMenuItem = new JMenuItem("Rules");
		rulesMenuItem.addActionListener(this);
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
		lblPlayer.setForeground(new Color(192, 192, 192));
		lblPlayer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayer.setFont(new Font("Rockwell", Font.BOLD, 20));
		lblPlayer.setBounds(630, 40, 267, 83);
		background.add(lblPlayer);

		lblWord = new JLabel("");
		lblWord.setForeground(Color.WHITE);
		lblWord.setFont(new Font("Rockwell", Font.BOLD, 25));
		lblWord.setBounds(40, 60, 700, 83);
		background.add(lblWord);

		btnHint = new JButton("Hint!");
		btnHint.setForeground(Color.WHITE);
		btnHint.setFont(new Font("Rockwell", Font.BOLD, 15));
		btnHint.setBackground(new Color(0, 128, 192));
		btnHint.setBounds(746, 410, 116, 41);
		btnHint.addActionListener(this);
		background.add(btnHint);

		imagePanel = new JPanel();
		imagePanel.setBackground(Color.BLACK);
		imagePanel.setBounds(110, 151, 307, 270);
		imagePanel.setOpaque(false);
		background.add(imagePanel);

		img = new ImageIcon("images/cloaked.gif");
		imagePanel.setLayout(null);
		label = new JLabel(img);
		label.setBounds(40, 0, 250, 250);
		imagePanel.add(label);

		lblHP = new JLabel("Lives:");
		lblHP.setForeground(new Color(220, 20, 60));
		lblHP.setBounds(20, 10, 130, 30);
		lblHP.setFont(new Font("Rockwell", Font.BOLD, 20));
		background.add(lblHP);

		heartImg1 = new ImageIcon("images/heart.png");
		lblHealth = new JLabel(heartImg1);
		lblHealth.setBounds(0, 10, 200, 30);
		background.add(lblHealth);

		heartImg2 = new ImageIcon("images/heart.png");
		lblHealth2 = new JLabel(heartImg2);
		lblHealth2.setBounds(0, 10, 270, 30);
		background.add(lblHealth2);

		heartImg3 = new ImageIcon("images/heart.png");
		lblHealth3 = new JLabel(heartImg3);
		lblHealth3.setBounds(0, 10, 340, 30);
		background.add(lblHealth3);

		heartImg4 = new ImageIcon("images/heart.png");
		lblHealth4 = new JLabel(heartImg4);
		lblHealth4.setBounds(0, 10, 410, 30);
		background.add(lblHealth4);

		heartImg5 = new ImageIcon("images/heart.png");
		lblHealth5 = new JLabel(heartImg5);
		lblHealth5.setBounds(0, 10, 480, 30);
		background.add(lblHealth5);

		heartImg6 = new ImageIcon("images/heart.png");
		lblHealth6 = new JLabel(heartImg6);
		lblHealth6.setBounds(0, 10, 550, 30);
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

		serializeBoard();
		setupGame();

	} // HangmanFrame()

	@Override
	public void actionPerformed(ActionEvent e) {
		outerloop: for (int r = 1; r < alphaButton.length; ++r)
			for (int c = 1; c < alphaButton[r].length; ++c) {
				if (e.getSource() == alphaButton[r][c]) {
					if (game.guessLetter(alphaButton[r][c].getText().toLowerCase().charAt(0)))
						displayWord();
					else
						updateImage();
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
			if (!game.displayHint()) {
				JOptionPane.showMessageDialog(this, "No more hints!", "No more hints!", JOptionPane.PLAIN_MESSAGE);
			} else {
				displayWord();
				updateImage();
			} // else
		} // hint
		else if (e.getSource() == newPlayerMenuItem) {
			displayLoginFrame();
		} // new player
		else if (e.getSource() == newGameMenuItem) {
			resetGame();
		} // new game
		else if (e.getSource() == scoreMenuItem) {
			ScoreboardFrame scoreFrame = new ScoreboardFrame(scoreboard);
			scoreFrame.setLocationRelativeTo(null);
			scoreFrame.setVisible(true);
		} // scoreboard
		else if (e.getSource() == rulesMenuItem) {
			JOptionPane.showMessageDialog(this, new RulesPanel(), "Rules", JOptionPane.PLAIN_MESSAGE);
		} // rules
	} // actionPerformed(ActionEvent)

	public void setupGame() {
		try {
			game = new HangmanGame(player);
		} catch (NoWordsLeftException e) {
			noWordsLeftHandler();
		} // catch (NoWordsLeftException)
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

	public void updateImage() {
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
	} // updateImage()

	public void displayWon() {
		scoreboard.addGamePlayed(player, true);
		serializeBoard();
		JOptionPane.showMessageDialog(this, "Congratulations! You survived the Ghoul!", "Game Won",
				JOptionPane.PLAIN_MESSAGE);
		resetGame();
	} // displayWon()

	public void displayLost() {
		scoreboard.addGamePlayed(player, false);
		serializeBoard();
		JOptionPane.showMessageDialog(this,
				"Oh no! You were slain by the Ghoul! The word was " + game.getWord().toUpperCase() + ".", "Game Over",
				JOptionPane.PLAIN_MESSAGE);
		resetGame();
	} // displayLost()

	public void resetGame() {
		try {
			game = new HangmanGame(player);
		} catch (NoWordsLeftException e) {
			noWordsLeftHandler();
		} // catch (NoWordsLeftException)
		resetHearts();
		resetButtons();
		displayWord();
		System.out.println(game.getWord());
	} // resetGame()

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
			JOptionPane.showMessageDialog(this, "There was an error saving your progress.", "Serialization error",
					JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		} // catch (IOException)
	} // serializeBoard()

	public void resetHearts() {
		lblHealth.setIcon(new ImageIcon("images/heart.png"));
		lblHealth2.setIcon(new ImageIcon("images/heart.png"));
		lblHealth3.setIcon(new ImageIcon("images/heart.png"));
		lblHealth4.setIcon(new ImageIcon("images/heart.png"));
		lblHealth5.setIcon(new ImageIcon("images/heart.png"));
		lblHealth6.setIcon(new ImageIcon("images/heart.png"));
	} // resetHearts()

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

	public static void displayLoginFrame() {
		login = new LoginFrame();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		login.getBtnPlay().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scoreboard = login.getScoreboard();
				if (login.getRadioSelect().isSelected()) {
					int index = login.getSelectedIndex();
					player = scoreboard.getPlayerAt(index);
				} // returning player
				else if (login.getRadioNew().isSelected()) {
					if (login.getNewField().getText().isEmpty())
						JOptionPane.showMessageDialog(login, "Please enter a name.", "Missing name",
								JOptionPane.PLAIN_MESSAGE);
					else {
						try {
							player = new Player(login.getNewField().getText());
							scoreboard.addPlayer(player);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(login, "There was an error reading from the word list file.",
									"File error", JOptionPane.PLAIN_MESSAGE);
							System.exit(0);
						} // catch
					} // else
				} // new player
				login.setVisible(false); // Close the login frame
				if (frame != null)
					frame.dispose();
				frame = new HangmanFrame();
				frame.setVisible(true);
			} // actionPerformed(ActionEvent)
		});
	} // displayLoginFrame()

	public void noWordsLeftHandler() {
		int reply = JOptionPane.showConfirmDialog(null, "You went through every word. Would you like to start over?",
				"No more words!", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			try {
				player.restartDictionary();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "There was an error reading from the word list file.", "File error",
						JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			} // catch (IOException)
			resetGame();
		} else {
			JOptionPane.showMessageDialog(null, "See you next time!");
			System.exit(0);
		} // else
	} // noWordsLeftHandler()
} // HangmanFrame class