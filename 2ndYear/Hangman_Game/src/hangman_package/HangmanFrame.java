package hangman_package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHangman = new JLabel("HANGMAN");
		lblHangman.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHangman.setForeground(Color.WHITE);
		lblHangman.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 37));
		lblHangman.setBounds(635, 10, 267, 83);
		contentPane.add(lblHangman);
		
		JButton btnA = new JButton("A");
		btnA.setForeground(Color.WHITE);
		btnA.setBackground(new Color(0, 153, 153));
		btnA.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		btnA.setBounds(558, 167, 49, 41);
		contentPane.add(btnA);
		
		JButton btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnB.setForeground(Color.WHITE);
		btnB.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		btnB.setBackground(new Color(0, 153, 153));
		btnB.setBounds(617, 167, 49, 41);
		contentPane.add(btnB);
		
		JButton btnC = new JButton("C");
		btnC.setForeground(Color.WHITE);
		btnC.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		btnC.setBackground(new Color(0, 153, 153));
		btnC.setBounds(676, 167, 49, 41);
		contentPane.add(btnC);
	} // HangmanFrame()
} // HangmanFrame class
