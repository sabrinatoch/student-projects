package hangman_package;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.SystemColor;

public class HangmanFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem exitMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangmanFrame frame = new HangmanFrame();
					// frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new
					// File("house.png")))));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HangmanFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 955, 566);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel background = new JLabel(new ImageIcon("background-image.png"));
		background.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		background.setBounds(0, 0, 941, 507);

//		getContentPane().add(background);
		setContentPane(background);

		background.setLayout(new FlowLayout());

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);

		JMenuItem newGameMenuItem = new JMenuItem("New Game");
		gameMenu.add(newGameMenuItem);

		JMenuItem saveMenuItem = new JMenuItem("Save");
		gameMenu.add(saveMenuItem);

		exitMenuItem = new JMenuItem("Exit");
		gameMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});

		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);

		JMenuItem scoreMenuItem = new JMenuItem("Scoreboard");
		viewMenu.add(scoreMenuItem);

		JMenuItem rulesMenuItem = new JMenuItem("Rules");
		viewMenu.add(rulesMenuItem);

		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		background.setLayout(null);

		JLabel lblHangman = new JLabel("HANGMAN");
		lblHangman.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHangman.setForeground(Color.WHITE);
		lblHangman.setFont(new Font("Forte", Font.BOLD, 37));
		lblHangman.setBounds(635, 10, 267, 83);
		background.add(lblHangman);

		JLabel lblWord = new JLabel("Word:  _ _ _ _ _ _ _ _ _");
		lblWord.setForeground(Color.WHITE);
		lblWord.setFont(new Font("Rockwell", Font.BOLD, 25));
		lblWord.setBounds(70, 70, 567, 83);
		background.add(lblWord);

		JButton btnHint = new JButton("Hint!");
		btnHint.setForeground(Color.WHITE);
		btnHint.setFont(new Font("Rockwell", Font.BOLD, 15));
		btnHint.setBackground(new Color(0, 128, 192));
		btnHint.setBounds(746, 410, 116, 41);
		background.add(btnHint);

		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.BLACK);
		imagePanel.setBounds(110, 151, 307, 270);
		imagePanel.setOpaque(false);
		background.add(imagePanel);

		ImageIcon img = new ImageIcon("cloaked.gif");
		imagePanel.setLayout(null);
		JLabel label = new JLabel(img);
		label.setBounds(40, 0, 250, 250);
		imagePanel.add(label);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(505, 160, 350, 200);
		buttonPanel.setOpaque(false);
		background.add(buttonPanel);

		for (int i = 0; i < 26; i++) {
			Character letter = (char) (i + 'A');
			JButton button = new JButton(letter.toString());
			button.setForeground(Color.WHITE);
			button.setBackground(Color.BLACK);
			button.setFont(new Font("Arial", Font.BOLD, 12));
			button.setPreferredSize(new Dimension(45, 45));
			buttonPanel.add(button);
		} // for

	} // HangmanFrame()

	@Override
	public void actionPerformed(ActionEvent e) {

	} // actionPerformed(ActionEvent)
} // HangmanFrame class
