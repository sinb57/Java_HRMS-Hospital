package main.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.domain.Hospital;
import main.domain.Patient;
import main.domain.Reservation;

public class TypeDoctorNamePage {
	PageHandler pageHandler;
	String patientId;
	String reservationDate;
	String reservationTime;

	JFrame frame = new JFrame();

	public TypeDoctorNamePage(PageHandler pageHandler, String patientId, String reservationDate,
			String reservationTime) {
		this.pageHandler = pageHandler;
		this.patientId = patientId;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;

		load();
	}

	void load() {

		Hospital hospital = pageHandler.service.getSelfInfo();
		Patient patient = pageHandler.service.getPateintInfo(patientId);
		patient.setReservationList(pageHandler.service.getReservationListOfPatient(patientId));
		Reservation thisReservation = patient.searchReservaion(reservationDate, reservationTime);
		String careType = thisReservation.getCareType();

		JPanel typeDoctorNamePanel = new JPanel();
		JPanel whitePanel = whitePanel(typeDoctorNamePanel);

		JLabel doctorNameLabel = new JLabel(careType + " ������ : ");
		doctorNameLabel.setFont(new Font("���� ���", Font.BOLD, 25));
		doctorNameLabel.setBounds(40, 20, 250, 35);
		whitePanel.add(doctorNameLabel);

		String[] select = hospital.getCareTypeMap().get(careType);
		JComboBox doctorNameBox = new JComboBox(select);
		doctorNameBox.setBounds(280, 20, 100, 35);
		whitePanel.add(doctorNameBox);

		JButton cancelBt = new JButton("\uCDE8  \uC18C");
		cancelBt.setBackground(new Color(211, 211, 211));
		cancelBt.setFont(new Font("���� ���", Font.BOLD, 23));
		cancelBt.setBounds(40, 100, 162, 42);
		cancelBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		whitePanel.add(cancelBt);

		JButton changeBt = new JButton("���� ó��");
		changeBt.setBackground(new Color(211, 211, 211));
		changeBt.setFont(new Font("���� ���", Font.BOLD, 23));
		changeBt.setBounds(278, 100, 162, 42);
		changeBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String doctorName = (String) doctorNameBox.getSelectedItem();

				if (pageHandler.service.processReservation(patientId, reservationDate, reservationTime, doctorName)) {
					JOptionPane.showMessageDialog(null, "���� ó���Ǿ����ϴ�.");
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "�߸��� �����Դϴ�. �ٽ� �Է����ּ���.");
				}
			}
		});
		whitePanel.add(changeBt);

		frame.add(whitePanel);

		frame.setTitle("�ű�");
		frame.setVisible(true);
		frame.setSize(500, 200);
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