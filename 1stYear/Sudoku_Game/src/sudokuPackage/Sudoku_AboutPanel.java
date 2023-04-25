/**
 * 
 */
package sudokuPackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * @author Sabrina Tochkov
 *
 */
public class Sudoku_AboutPanel extends JPanel {

	public Sudoku_AboutPanel() {

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 218, 14 };
		gridBagLayout.rowHeights = new int[] { 14, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		JLabel lblTitle = new JLabel("Sudoku Game");
		lblTitle.setForeground(SystemColor.textHighlight);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 0, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);

		JLabel lblAuthor = new JLabel("Sabrina Tochkov");
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.insets = new Insets(0, 0, 0, 0);
		gbc_lblAuthor.gridx = 0;
		gbc_lblAuthor.gridy = 1;
		add(lblAuthor, gbc_lblAuthor);

		JLabel lblCopyright = new JLabel("2023");
		GridBagConstraints gbc_lblCopyright = new GridBagConstraints();
		gbc_lblCopyright.insets = new Insets(0, 0, 0, 0);
		gbc_lblCopyright.gridx = 0;
		gbc_lblCopyright.gridy = 2;
		add(lblCopyright, gbc_lblCopyright);

		JLabel lblCompany = new JLabel("Heritage College");
		GridBagConstraints gbc_lblCompany = new GridBagConstraints();
		gbc_lblCompany.insets = new Insets(0, 0, 0, 0);
		gbc_lblCompany.gridx = 0;
		gbc_lblCompany.gridy = 3;
		add(lblCompany, gbc_lblCompany);

	} // Sudoku_AboutPanel()

} // Sudoku_AboutPanel class
