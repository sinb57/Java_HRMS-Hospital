package main.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ChangePasswordPage {
	JFrame frame = new JFrame();

	void passwordChangeGUI(PageHandler pageHandler) {

		JPanel passwordChangePanel = new JPanel();
		JPanel passwordChangeWhitePanel = whitePanel(passwordChangePanel);

		JLabel existingPassword = new JLabel("���� ��й�ȣ");
		existingPassword.setFont(new Font("���� ���", Font.BOLD, 25));
		existingPassword.setBounds(40, 30, 162, 35);
		passwordChangeWhitePanel.add(existingPassword);

		JPasswordField existingPasswordField = new JPasswordField();
		existingPasswordField.setFont(new Font("���� ���", Font.PLAIN, 25));
		existingPasswordField.setBounds(40, 74, 400, 35);
		passwordChangeWhitePanel.add(existingPasswordField);

		JLabel passwordToChange = new JLabel("������ ��й�ȣ ");
		passwordToChange.setFont(new Font("���� ���", Font.BOLD, 25));
		passwordToChange.setBounds(40, 147, 200, 35);
		passwordChangeWhitePanel.add(passwordToChange);

		JPasswordField passwordToChangeField = new JPasswordField();
		passwordToChangeField.setFont(new Font("���� ���", Font.PLAIN, 25));
		passwordToChangeField.setBounds(40, 192, 400, 35);
		passwordChangeWhitePanel.add(passwordToChangeField);

		JLabel rePasswordToChange = new JLabel("��й�ȣ ���Է�");
		rePasswordToChange.setFont(new Font("���� ���", Font.BOLD, 25));
		rePasswordToChange.setBounds(40, 262, 200, 35);
		passwordChangeWhitePanel.add(rePasswordToChange);

		JPasswordField rePasswordToChangeField = new JPasswordField();
		rePasswordToChangeField.setFont(new Font("���� ���", Font.PLAIN, 25));
		rePasswordToChangeField.setBounds(40, 307, 400, 35);
		passwordChangeWhitePanel.add(rePasswordToChangeField);

		JButton cancelBt = new JButton("\uCDE8  \uC18C");
		cancelBt.setBackground(new Color(211, 211, 211));
		cancelBt.setFont(new Font("���� ���", Font.BOLD, 23));
		cancelBt.setBounds(40, 400, 162, 42);
		cancelBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		passwordChangeWhitePanel.add(cancelBt);

		JButton changeBt = new JButton("�����ϱ�");
		changeBt.setBackground(new Color(211, 211, 211));
		changeBt.setFont(new Font("���� ���", Font.BOLD, 23));
		changeBt.setBounds(278, 400, 162, 42);
		changeBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String existingPasswordText = String.copyValueOf(existingPasswordField.getPassword());
				String passwordToChangeText = String.copyValueOf(passwordToChangeField.getPassword());
				String rePasswordToChangeText = String.copyValueOf(rePasswordToChangeField.getPassword());

				if (pageHandler.service.modifyHospitalPw(existingPasswordText, passwordToChangeText,
						rePasswordToChangeText)) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����Ǿ����ϴ�.");
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "�߸��� �����Դϴ�. �ٽ� �Է����ּ���.");

				}
			}
		});
		passwordChangeWhitePanel.add(changeBt);

		frame.add(passwordChangePanel);
		frame.add(passwordChangeWhitePanel);

		frame.setTitle("�ű�");
		frame.setVisible(true);
		frame.setSize(500, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	JPanel whitePanel(JPanel mainPanel) {
		JPanel whitePanel = new JPanel();
		whitePanel.setBackground(Color.WHITE);
		whitePanel.setForeground(Color.BLACK);
		whitePanel.setBounds(12, 221, 505, 557);
		mainPanel.add(whitePanel);
		whitePanel.setLayout(null);
		return whitePanel;
	}

}