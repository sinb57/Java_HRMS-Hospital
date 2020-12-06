package main.page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Header extends JPanel {

	public Header(PageHandler pageHandler) {
		this.setLayout(null);
		this.setBackground(new Color(203, 211, 216));
		this.setPreferredSize(new Dimension(0, 90));

		JLabel menuTitleLabel = new JLabel("�ű� �");
		menuTitleLabel.setFont(new Font("���� ���", Font.BOLD, 30));
		menuTitleLabel.setBounds(30, 25, 300, 50);

		JButton menuWaitButton = new JButton("��� ��Ȳ");
		menuWaitButton.setBounds(200, 25, 125, 50);
		menuWaitButton.setBackground(Color.gray);
		menuWaitButton.setForeground(Color.white);
		menuWaitButton.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuWaitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("ReservationStatusPage");
			}
		});

		JButton menuHistoryButton = new JButton("���� ���");
		menuHistoryButton.setBounds(330, 25, 125, 50);
		menuHistoryButton.setBackground(Color.gray);
		menuHistoryButton.setForeground(Color.white);
		menuHistoryButton.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("ReservationHistoryPage");
			}
		});

		JButton menuHospitalInformationButton = new JButton("���� ����");
		menuHospitalInformationButton.setBounds(460, 25, 125, 50);
		menuHospitalInformationButton.setBackground(Color.gray);
		menuHospitalInformationButton.setForeground(Color.white);
		menuHospitalInformationButton.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuHospitalInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("HospitalInformationPage");
			}
		});

		this.add(menuTitleLabel);
		this.add(menuWaitButton);
		this.add(menuHistoryButton);
		this.add(menuHospitalInformationButton);

	}
}
