package hangman_package;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class ScoreboardFrame extends JFrame {

	private JLabel background;
	private JTextArea areaDisplay;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				try {
//					ScoreboardFrame frame = new ScoreboardFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
	}

	public ScoreboardFrame(Scoreboard score) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 365);
		background = new JLabel(new ImageIcon("images/background-image.png"));
		background.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		background.setBounds(0, 0, 941, 507);

		setContentPane(background);
		
		background.setLayout(null);
		
		JLabel lblTitle = new JLabel("Scoreboard");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Rockwell", Font.PLAIN, 23));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(130, 10, 195, 30);
		background.add(lblTitle);
		
		areaDisplay = new JTextArea();
		areaDisplay.setForeground(new Color(255, 255, 255));
		areaDisplay.setOpaque(false);
		areaDisplay.setBounds(29, 50, 428, 268);
		areaDisplay.setFont(new Font("Courier New", Font.BOLD, 15));
		areaDisplay.setEditable(false);
		background.add(areaDisplay);	
		
		DefaultCaret caret = (DefaultCaret) areaDisplay.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
		areaDisplay.setText(String.format("%-18s%-18s%-20s\n", "Player Name", "Games Played", "Games Won"));
		areaDisplay.append(String.format("%-18s%-18s%-20s\n", "-----------", "------------", "---------"));
		
		for (int i = 0; i < score.getNumPlayers(); ++i) {
			displayLine(score.getPlayerAt(i));
		} // for
	} // ScoreboardFrame(Scoreboard)
	
	public void displayLine(Player pl) {
		areaDisplay.append(String.format("%-18s%-18d%-20d\n", pl.getName(), pl.getNumberGamesPlayed(), pl.getNumberGamesWon()));
	} // displayLine(Player)
	
	
	public void sortPlayerList(Scoreboard score) {
		
	} // sortPlayerList(Scoreboard)
	
} // ScoreboardFrame class
