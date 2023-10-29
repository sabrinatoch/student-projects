package hangman_package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class LoginFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField fldNewPlayer;
	private JRadioButton radioNewPlayer;
	private JRadioButton radioSelectPlayer;
	private JLabel lblLogin;
	private JComboBox cmbxPlayers;
	private JLabel lblHangman;
	private ButtonGroup group;
	private JButton btnPlay;
	private Scoreboard scoreboard;
	private Player player;
	private boolean isNew;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} // main()

	public LoginFrame() {
		JLabel background = new JLabel(new ImageIcon("images/background-image.png"));
		background.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		background.setBounds(0, 0, 941, 507);
		setContentPane(background);
		player = null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 347);

		setLocationRelativeTo(null);
		radioNewPlayer = new JRadioButton("New Player:");
		radioNewPlayer.setHorizontalAlignment(SwingConstants.RIGHT);
		radioNewPlayer.setOpaque(false);
		radioNewPlayer.setForeground(new Color(255, 255, 255));
		radioNewPlayer.setFont(new Font("Rockwell", Font.PLAIN, 15));
		radioNewPlayer.setBounds(147, 171, 117, 44);
		background.add(radioNewPlayer);

		radioSelectPlayer = new JRadioButton("Select Player:");
		radioSelectPlayer.setForeground(new Color(255, 255, 255));
		radioSelectPlayer.setOpaque(false);
		radioSelectPlayer.setFont(new Font("Rockwell", Font.PLAIN, 15));
		radioSelectPlayer.setBounds(141, 125, 123, 44);
		background.add(radioSelectPlayer);
		radioSelectPlayer.setSelected(true);

		lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblLogin.setBounds(193, 66, 179, 53);
		background.add(lblLogin);

		fldNewPlayer = new JTextField();
		fldNewPlayer.setBackground(new Color(0, 0, 64));
		fldNewPlayer.setForeground(new Color(255, 255, 255));
		fldNewPlayer.setBounds(264, 186, 168, 19);
		background.add(fldNewPlayer);
		fldNewPlayer.setColumns(10);
		fldNewPlayer.setEnabled(false);

		cmbxPlayers = new JComboBox();
		cmbxPlayers.setForeground(new Color(255, 255, 255));
		cmbxPlayers.setBackground(new Color(0, 0, 64));
		cmbxPlayers.setBounds(264, 140, 168, 19);
		background.add(cmbxPlayers);

		lblHangman = new JLabel("Hangman (Ghoul Edition)");
		lblHangman.setBackground(new Color(0, 0, 0));
		lblHangman.setHorizontalAlignment(SwingConstants.CENTER);
		lblHangman.setForeground(new Color(192, 192, 192));
		lblHangman.setFont(new Font("Forte", Font.PLAIN, 31));
		lblHangman.setBounds(116, 28, 368, 44);
		background.add(lblHangman);

		group = new ButtonGroup();
		group.add(radioSelectPlayer);
		group.add(radioNewPlayer);

		btnPlay = new JButton("Play!");
		btnPlay.setForeground(new Color(255, 255, 255));
		btnPlay.setBackground(new Color(0, 0, 64));
		btnPlay.setFont(new Font("Rockwell", Font.PLAIN, 17));
		btnPlay.setBounds(232, 243, 99, 40);
		background.add(btnPlay);

		btnPlay.addActionListener(this);
		radioSelectPlayer.addActionListener(this);
		radioNewPlayer.addActionListener(this);

		checkScoreboard();

	} // LoginFrame()

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

		if (scoreboard == null) {
			scoreboard = new Scoreboard();
			cmbxPlayers.setEnabled(false);
			radioSelectPlayer.setSelected(false);
			radioNewPlayer.setSelected(true);
			fldNewPlayer.setEnabled(true);
			radioSelectPlayer.setEnabled(false);
		} // null scoreboard
		else
			for (int i = 0; i < scoreboard.getNumPlayers(); ++i)
				cmbxPlayers.addItem(scoreboard.getPlayerAt(i).getName());
	} // checkScoreboard()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == radioNewPlayer) {
			cmbxPlayers.setEnabled(false);
			fldNewPlayer.setEnabled(true);
		} // if new player
		else if (e.getSource() == radioSelectPlayer) {
			cmbxPlayers.setEnabled(true);
			fldNewPlayer.setText("");
			fldNewPlayer.setEnabled(false);
		} // else if select
	} // actionPerformed(ActionEvenet)

	public JButton getBtnPlay() {
		return btnPlay;
	} // getBtnPlay()

	public JRadioButton getRadioSelect() {
		return radioSelectPlayer;
	} // getRadioSelect()

	public JRadioButton getRadioNew() {
		return radioNewPlayer;
	} // getRadioNew()

	public Player getPlayer() {
		return player;
	} // getPlayer()
	
	public int getSelectedIndex() {
		return cmbxPlayers.getSelectedIndex();
	}

	public JTextField getNewField() {
		return fldNewPlayer;
	} // getNewField()

	public Scoreboard getScoreboard() {
		return scoreboard;
	} // getScoreboard()
} // LoginFrame class
