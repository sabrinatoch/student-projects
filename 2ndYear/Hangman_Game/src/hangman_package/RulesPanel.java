package hangman_package;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;

public class RulesPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public RulesPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 218, 14 };
		gridBagLayout.rowHeights = new int[] { 14, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		JLabel lblTitle = new JLabel("Hangman Rules:");
		lblTitle.setFont(new Font("Rockwell", Font.PLAIN, 15));
		lblTitle.setForeground(new Color(0, 0, 128));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);

		JLabel lbl1 = new JLabel(
				"   1. Guess each letter using the alphabetic keypad.");
		lbl1.setFont(new Font("Rockwell", Font.PLAIN, 13));
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.anchor = GridBagConstraints.WEST;
		gbc_1.insets = new Insets(0, 0, 3, 3);
		gbc_1.gridx = 0;
		gbc_1.gridy = 1;
		add(lbl1, gbc_1);

		JLabel lbl2 = new JLabel(
				"   2. Each time you guess incorrectly, you lose a life.");
		lbl2.setFont(new Font("Rockwell", Font.PLAIN, 13));
		GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.anchor = GridBagConstraints.WEST;
		gbc_2.insets = new Insets(0, 0, 3, 3);
		gbc_2.gridx = 0;
		gbc_2.gridy = 2;
		add(lbl2, gbc_2);
		
		JLabel lbl25 = new JLabel(
				"   3. If you lose 6 lives, the Ghoul wins and you die!");
		lbl25.setFont(new Font("Rockwell", Font.PLAIN, 13));
		GridBagConstraints gbc_25 = new GridBagConstraints();
		gbc_25.anchor = GridBagConstraints.WEST;
		gbc_25.insets = new Insets(0, 0, 3, 3);
		gbc_25.gridx = 0;
		gbc_25.gridy = 3;
		add(lbl25, gbc_25);

		JLabel lbl3 = new JLabel(
				"   4. If you guess the word before that happens, you survive!");
		lbl3.setFont(new Font("Rockwell", Font.PLAIN, 13));
		GridBagConstraints gbc_3 = new GridBagConstraints();
		gbc_3.anchor = GridBagConstraints.WEST;
		gbc_3.insets = new Insets(0, 0, 3, 3);
		gbc_3.gridx = 0;
		gbc_3.gridy = 4;
		add(lbl3, gbc_3);

		JLabel lbl4 = new JLabel(
				"   5. To play a new game or create a new player, select the \"Game\" Menu.");
		lbl4.setFont(new Font("Rockwell", Font.PLAIN, 13));
		GridBagConstraints gbc_4 = new GridBagConstraints();
		gbc_4.anchor = GridBagConstraints.WEST;
		gbc_4.insets = new Insets(0, 0, 3, 3);
		gbc_4.gridx = 0;
		gbc_4.gridy = 5;
		add(lbl4, gbc_4);

		JLabel lbl5 = new JLabel(
				"   6. To display a hint click the \"Hint\" Button. You cannot win or lose on a hint.");
		lbl5.setFont(new Font("Rockwell", Font.PLAIN, 13));
		GridBagConstraints gbc_5 = new GridBagConstraints();
		gbc_5.anchor = GridBagConstraints.WEST;
		gbc_5.insets = new Insets(0, 0, 3, 3);
		gbc_5.gridx = 0;
		gbc_5.gridy = 6;
		add(lbl5, gbc_5);
		
		JLabel lbl6 = new JLabel(
				"   7. To display the scoreboard, select the \"View\" Menu.");
		lbl6.setFont(new Font("Rockwell", Font.PLAIN, 13));
		GridBagConstraints gbc_6 = new GridBagConstraints();
		gbc_6.anchor = GridBagConstraints.WEST;
		gbc_6.insets = new Insets(0, 0, 3, 3);
		gbc_6.gridx = 0;
		gbc_6.gridy = 7;
		add(lbl6, gbc_6);
		
		JLabel lbl7 = new JLabel(
				"   8. Most importantly... Have fun :)");
		lbl7.setFont(new Font("Rockwell", Font.PLAIN, 13));
		GridBagConstraints gbc_7 = new GridBagConstraints();
		gbc_7.anchor = GridBagConstraints.WEST;
		gbc_7.insets = new Insets(0, 0, 3, 3);
		gbc_7.gridx = 0;
		gbc_7.gridy = 8;
		add(lbl7, gbc_7);
	} // RulesPanel()

} // RulesPanel class
