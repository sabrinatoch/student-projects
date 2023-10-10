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

public class HangmanFrame extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 955, 566);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel background = new JLabel(new ImageIcon("background-image.png"));
		background.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		background.setBounds(0, 0, 941, 507);

		getContentPane().add(background);

		background.setLayout(new FlowLayout());

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);

		JMenuItem newGameMenuItem = new JMenuItem("New Game");
		gameMenu.add(newGameMenuItem);

		JMenuItem saveMenuItem = new JMenuItem("Save");
		gameMenu.add(saveMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		gameMenu.add(exitMenuItem);

		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);

		JMenuItem scoreMenuItem = new JMenuItem("Scoreboard");
		viewMenu.add(scoreMenuItem);

		JMenuItem rulesMenuItem = new JMenuItem("Rules");
		viewMenu.add(rulesMenuItem);

		background.setBorder(new EmptyBorder(5, 5, 5, 5));

//		setContentPane(background);
		background.setLayout(null);

		JLabel lblHangman = new JLabel("HANGMAN");
		lblHangman.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHangman.setForeground(Color.WHITE);
		lblHangman.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 37));
		lblHangman.setBounds(635, 10, 267, 83);
		background.add(lblHangman);

		JButton btnA = new JButton("A");
		btnA.setForeground(Color.WHITE);
		btnA.setBackground(Color.BLACK);
		btnA.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		btnA.setBounds(555, 220, 49, 41);
		background.add(btnA);

		JButton btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnB.setForeground(Color.WHITE);
		btnB.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		btnB.setBackground(Color.BLACK);
		btnB.setBounds(619, 220, 49, 41);
		background.add(btnB);

		JButton btnC = new JButton("C");
		btnC.setForeground(Color.WHITE);
		btnC.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		btnC.setBackground(Color.BLACK);
		btnC.setBounds(678, 220, 49, 41);
		background.add(btnC);

		JButton btnHint = new JButton("Hint!");
		btnHint.setForeground(Color.WHITE);
		btnHint.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		btnHint.setBackground(new Color(0, 0, 128));
		btnHint.setBounds(786, 440, 116, 41);
		background.add(btnHint);

		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.BLACK);
		imagePanel.setBounds(39, 101, 407, 320);
		background.add(imagePanel);

		ImageIcon img = new ImageIcon("skull.png");
		imagePanel.add(new JLabel(img));
		
		JLabel lblUnder1_4_1 = new JLabel("_");
		background.add(lblUnder1_4_1);
		lblUnder1_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnder1_4_1.setForeground(SystemColor.textHighlightText);
		lblUnder1_4_1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 19));
		lblUnder1_4_1.setBounds(744, 113, 78, 43);
		
		JLabel lblUnder1_4 = new JLabel("_");
		background.add(lblUnder1_4);
		lblUnder1_4.setForeground(SystemColor.textHighlightText);
		lblUnder1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnder1_4.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 19));
		lblUnder1_4.setBounds(682, 113, 78, 43);
		
		JLabel lblUnder1_3 = new JLabel("_");
		background.add(lblUnder1_3);
		lblUnder1_3.setForeground(SystemColor.textHighlightText);
		lblUnder1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnder1_3.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 19));
		lblUnder1_3.setBounds(620, 113, 78, 43);
		
		JLabel lblUnder1_2 = new JLabel("_");
		background.add(lblUnder1_2);
		lblUnder1_2.setForeground(SystemColor.textHighlightText);
		lblUnder1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnder1_2.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 19));
		lblUnder1_2.setBounds(565, 113, 78, 43);
		
		JLabel lblUnder1_1 = new JLabel("_");
		background.add(lblUnder1_1);
		lblUnder1_1.setForeground(SystemColor.textHighlightText);
		lblUnder1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnder1_1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 19));
		lblUnder1_1.setBounds(507, 113, 78, 43);
		
		JLabel lblUnder1 = new JLabel("_");
		background.add(lblUnder1);
		lblUnder1.setForeground(SystemColor.textHighlightText);
		lblUnder1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnder1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 19));
		lblUnder1.setBounds(452, 113, 78, 43);
		
		JLabel lblUnder1_2_1 = new JLabel("_");
		background.add(lblUnder1_2_1);
		lblUnder1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnder1_2_1.setForeground(SystemColor.textHighlightText);
		lblUnder1_2_1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 19));
		lblUnder1_2_1.setBounds(808, 113, 78, 43);
	} // HangmanFrame()
} // HangmanFrame class
