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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

public class LoginFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField fldNewPlayer;
	private JRadioButton radioNewPlayer;
	private JRadioButton radioSelectPlayer;
	private JLabel lblLogin;
	private JComboBox cmbxPlayers;
	private JLabel lblHangman;
	private ButtonGroup group;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 347);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		radioNewPlayer = new JRadioButton("New Player:");
		radioNewPlayer.setBackground(new Color(0, 0, 64));
		radioNewPlayer.setForeground(new Color(255, 255, 255));
		radioNewPlayer.setFont(new Font("Rockwell", Font.PLAIN, 15));
		radioNewPlayer.setBounds(141, 193, 117, 44);
		contentPane.add(radioNewPlayer);

		radioSelectPlayer = new JRadioButton("Returning Player:");
		radioSelectPlayer.setForeground(new Color(255, 255, 255));
		radioSelectPlayer.setBackground(new Color(0, 0, 64));
		radioSelectPlayer.setFont(new Font("Rockwell", Font.PLAIN, 15));
		radioSelectPlayer.setBounds(141, 125, 150, 44);
		contentPane.add(radioSelectPlayer);
		radioSelectPlayer.setSelected(true);

		lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblLogin.setBounds(193, 66, 179, 53);
		contentPane.add(lblLogin);

		fldNewPlayer = new JTextField();
		fldNewPlayer.setBounds(264, 208, 168, 19);
		contentPane.add(fldNewPlayer);
		fldNewPlayer.setColumns(10);
		fldNewPlayer.setEnabled(false);

		cmbxPlayers = new JComboBox();
		cmbxPlayers.setBounds(297, 140, 168, 19);
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

		radioSelectPlayer.addActionListener(this);
		radioNewPlayer.addActionListener(this);
	} // LoginFrame()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == radioNewPlayer) {
			cmbxPlayers.setEnabled(false);
			fldNewPlayer.setEnabled(true);
		} //  if new player
		else if (e.getSource() == radioSelectPlayer) {
			cmbxPlayers.setEnabled(true);
			fldNewPlayer.setEnabled(false);
		} // if select
	} // actionPerformed(ActionEvenet)

} // LoginFrame class
