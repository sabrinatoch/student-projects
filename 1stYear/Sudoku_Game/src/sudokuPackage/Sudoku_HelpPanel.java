/**
 * 
 */
package sudokuPackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

/**
 * @author Sabrina Tochkov
 *
 */
public class Sudoku_HelpPanel extends JPanel {

	public Sudoku_HelpPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 218, 14 };
		gridBagLayout.rowHeights = new int[] { 14, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		JLabel lblTitle = new JLabel("Sudoku Instructions:");
		lblTitle.setForeground(SystemColor.textHighlight);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);

		JLabel lbl1 = new JLabel(
				"   1. Select an empty square from the board. It will turn orange.");
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.anchor = GridBagConstraints.WEST;
		gbc_1.insets = new Insets(0, 0, 3, 3);
		gbc_1.gridx = 0;
		gbc_1.gridy = 1;
		add(lbl1, gbc_1);

		JLabel lbl2 = new JLabel(
				"   2. Select the value (1-9) to enter in that square.");
		GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.anchor = GridBagConstraints.WEST;
		gbc_2.insets = new Insets(0, 0, 3, 3);
		gbc_2.gridx = 0;
		gbc_2.gridy = 2;
		add(lbl2, gbc_2);
		
		JLabel lbl25 = new JLabel(
				"   (You cannot enter a value that is already in that row, column or 3x3 square.)");
		GridBagConstraints gbc_25 = new GridBagConstraints();
		gbc_25.anchor = GridBagConstraints.WEST;
		gbc_25.insets = new Insets(0, 0, 3, 3);
		gbc_25.gridx = 0;
		gbc_25.gridy = 3;
		add(lbl25, gbc_25);

		JLabel lbl3 = new JLabel(
				"   3. If your move is valid, the square will turn white. Otherwise, an error message will appear.");
		GridBagConstraints gbc_3 = new GridBagConstraints();
		gbc_3.anchor = GridBagConstraints.WEST;
		gbc_3.insets = new Insets(0, 0, 3, 3);
		gbc_3.gridx = 0;
		gbc_3.gridy = 4;
		add(lbl3, gbc_3);

		JLabel lbl4 = new JLabel(
				"   4. To save your changes to the file, select the \"Game\" Menu, then \"Save\".");
		GridBagConstraints gbc_4 = new GridBagConstraints();
		gbc_4.anchor = GridBagConstraints.WEST;
		gbc_4.insets = new Insets(0, 0, 3, 3);
		gbc_4.gridx = 0;
		gbc_4.gridy = 5;
		add(lbl4, gbc_4);

		JLabel lbl5 = new JLabel(
				"   5. To start a new game with a different file, select the \"Game\" Menu, then \"New Game\".");
		GridBagConstraints gbc_5 = new GridBagConstraints();
		gbc_5.anchor = GridBagConstraints.WEST;
		gbc_5.insets = new Insets(0, 0, 3, 3);
		gbc_5.gridx = 0;
		gbc_5.gridy = 6;
		add(lbl5, gbc_5);
		
		JLabel lbl6 = new JLabel(
				"   6. To Undo your last move, select the \"Undo\" Button.");
		GridBagConstraints gbc_6 = new GridBagConstraints();
		gbc_6.anchor = GridBagConstraints.WEST;
		gbc_6.insets = new Insets(0, 0, 3, 3);
		gbc_6.gridx = 0;
		gbc_6.gridy = 7;
		add(lbl6, gbc_6);

	} // Sudoku_HelpPanel()

} // Sudoku_HelpPanel class
