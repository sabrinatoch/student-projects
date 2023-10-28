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
		player = null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 347);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		radioNewPlayer = new JRadioButton("New Player:");
		radioNewPlayer.setHorizontalAlignment(SwingConstants.RIGHT);
		radioNewPlayer.setBackground(new Color(0, 0, 64));
		radioNewPlayer.setForeground(new Color(255, 255, 255));
		radioNewPlayer.setFont(new Font("Rockwell", Font.PLAIN, 15));
		radioNewPlayer.setBounds(147, 171, 117, 44);
		contentPane.add(radioNewPlayer);

		radioSelectPlayer = new JRadioButton("Select Player:");
		radioSelectPlayer.setForeground(new Color(255, 255, 255));
		radioSelectPlayer.setBackground(new Color(0, 0, 64));
		radioSelectPlayer.setFont(new Font("Rockwell", Font.PLAIN, 15));
		radioSelectPlayer.setBounds(141, 125, 123, 44);
		contentPane.add(radioSelectPlayer);
		radioSelectPlayer.setSelected(true);

		lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblLogin.setBounds(193, 66, 179, 53);
		contentPane.add(lblLogin);

		fldNewPlayer = new JTextField();
		fldNewPlayer.setBounds(264, 186, 168, 19);
		contentPane.add(fldNewPlayer);
		fldNewPlayer.setColumns(10);
		fldNewPlayer.setEnabled(false);

		cmbxPlayers = new JComboBox();
		cmbxPlayers.setBounds(264, 140, 168, 19);
		contentPane.add(cmbxPlayers);

		lblHangman = new JLabel("Hangman (Ghoul Edition)");
		lblHangman.setHorizontalAlignment(SwingConstants.CENTER);
		lblHangman.setForeground(new Color(128, 0, 255));
		lblHangman.setFont(new Font("Forte", Font.PLAIN, 31));
		lblHangman.setBounds(116, 28, 368, 44);
		contentPane.add(lblHangman);

		group = new ButtonGroup();
		group.add(radioSelectPlayer);
		group.add(radioNewPlayer);

		btnPlay = new JButton("Play!");
		btnPlay.setForeground(new Color(255, 255, 255));
		btnPlay.setBackground(new Color(128, 0, 255));
		btnPlay.setFont(new Font("Rockwell", Font.PLAIN, 17));
		btnPlay.setBounds(232, 243, 99, 40);
		contentPane.add(btnPlay);

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

	public JTextField getNewField() {
		return fldNewPlayer;
	} // getNewField()

	public Scoreboard getScoreboard() {
		return scoreboard;
	} // getScoreboard()
} // LoginFrame class
