package main.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JPanel {

	public LoginPage(PageHandler pageHandler) {
		this.setLayout(null);

		JPanel upperBarPanel = new JPanel();

		upperBarPanel.setVisible(true);
		upperBarPanel.setLayout(null);
		upperBarPanel.setBounds(0, 0, 1240, 90);

		JLabel MenuTitle = new JLabel("°Å±ä ¾î¶§");
		MenuTitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		MenuTitle.setBounds(30, 25, 300, 50);
		upperBarPanel.add(MenuTitle);

		JPanel loginPanel = new JPanel();
		loginPanel.setVisible(true);
		loginPanel.setLayout(null);
		loginPanel.add(upperBarPanel);
		loginPanel.setBounds(0, 0, 1240, 845);

		loginPanel.setBackground(new Color(203, 211, 216));

		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setBounds(560, 300, 200, 80);
		loginLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40));
		loginPanel.add(loginLabel);

		JLabel idIconLabel = new JLabel("");
		idIconLabel.setIcon(new ImageIcon(pageHandler.PATH + "login_ID.png"));
		idIconLabel.setBounds(400, 400, 100, 80);
		loginPanel.add(idIconLabel);

		JTextField idTextField = new JTextField();
		idTextField.setColumns(20);
		idTextField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		idTextField.setBounds(500, 415, 300, 40);
		loginPanel.add(idTextField);

		JLabel passwdIconLabel = new JLabel("");
		passwdIconLabel.setIcon(new ImageIcon(pageHandler.PATH + "login_passwd.png"));
		passwdIconLabel.setBounds(400, 500, 80, 80);
		loginPanel.add(passwdIconLabel);

		JPasswordField passwdTextField = new JPasswordField();
		passwdTextField.setColumns(20);
		passwdTextField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		passwdTextField.setBounds(500, 515, 300, 40);
		loginPanel.add(passwdTextField);

		JButton loginButton = new JButton("·Î±×ÀÎ");
		loginButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		loginButton.setBounds(550, 600, 150, 40);
		loginButton.setBackground(new Color(211, 211, 211));
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = idTextField.getText();
				String userPw = String.copyValueOf(passwdTextField.getPassword());
				if (pageHandler.service.login(userId, userPw)) {
					JOptionPane.showMessageDialog(null, "You have logged in successfully");
					pageHandler.change("ReservationStatusPage");
				} else {
					JOptionPane.showMessageDialog(null, "You failed to log in");
				}

			}
		});

		loginPanel.add(loginButton);

		this.add(loginPanel);

	}
}
