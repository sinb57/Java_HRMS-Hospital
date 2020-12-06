package main.page;

import javax.swing.*;

import main.domain.Hospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HospitalInformationPage extends JPanel {

	public HospitalInformationPage(PageHandler pageHandler) {
		Hospital hospital = pageHandler.service.getSelfInfo();

		this.setLayout(null);
		JPanel informationHospitalPanel = new JPanel();
		informationHospitalPanel.setVisible(true);
		informationHospitalPanel.setLayout(null);
		informationHospitalPanel.setBackground(Color.white);
		informationHospitalPanel.setBounds(0, -90, 1240, 845);

		JLabel hospitalNameLabel = new JLabel("º´¿ø¸í:");
		hospitalNameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
		hospitalNameLabel.setBounds(80, 80, 170, 90);
		informationHospitalPanel.add(hospitalNameLabel);

		JLabel hospitalNameOwnLabel = new JLabel(hospital.getHospitalName());
		hospitalNameOwnLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
		hospitalNameOwnLabel.setBounds(220, 80, 1000, 90);
		informationHospitalPanel.add(hospitalNameOwnLabel);

		JPanel informationHospital_SectionPanel = new JPanel();
		informationHospital_SectionPanel.setVisible(true);
		informationHospital_SectionPanel.setLayout(null);
		informationHospital_SectionPanel.setBounds(80, 160, 1000, 530);
		informationHospitalPanel.add(informationHospital_SectionPanel);

		JLabel hospitalIDLabel = new JLabel("¾ÆÀÌµð");
		hospitalIDLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		hospitalIDLabel.setBounds(20, -20, 120, 90);
		informationHospital_SectionPanel.add(hospitalIDLabel);

		JLabel hospitalIDOWNLabel = new JLabel(hospital.getHospitalId());
		hospitalIDOWNLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		hospitalIDOWNLabel.setBounds(120, -20, 500, 90);
		informationHospital_SectionPanel.add(hospitalIDOWNLabel);

		JLabel hospitalmNumberLabel = new JLabel("¿¬¶ôÃ³     ");
		hospitalmNumberLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		hospitalmNumberLabel.setBounds(20, 20, 120, 90);
		informationHospital_SectionPanel.add(hospitalmNumberLabel);

		JLabel hospitalmNumberOwnLabel = new JLabel(hospital.getPhoneNumber());
		hospitalmNumberOwnLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		hospitalmNumberOwnLabel.setBounds(120, 20, 500, 90);
		informationHospital_SectionPanel.add(hospitalmNumberOwnLabel);

		JLabel hospitalAddressLabel = new JLabel("ÁÖ¼Ò     ");
		hospitalAddressLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		hospitalAddressLabel.setBounds(20, 60, 120, 90);
		informationHospital_SectionPanel.add(hospitalAddressLabel);

		JLabel hospitalAddressOwnLabel = new JLabel(hospital.getAddress());
		hospitalAddressOwnLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		hospitalAddressOwnLabel.setBounds(120, 60, 500, 90);
		informationHospital_SectionPanel.add(hospitalAddressOwnLabel);

		JLabel reservaionSubjectLabel = new JLabel("Áø·á°ú¸ñ");
		reservaionSubjectLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		reservaionSubjectLabel.setBounds(20, 100, 120, 90);
		informationHospital_SectionPanel.add(reservaionSubjectLabel);

		HashMap<String, String[]> careType = hospital.getCareTypeMap();
		JLabel reservationSubjects[] = new JLabel[careType.size()];

		JPanel docterPanel = new JPanel();
		docterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
		docterPanel.setVisible(true);
		docterPanel.setBounds(90, 112, 1000, 90);
		Iterator<String> keys = careType.keySet().iterator();
		int index = 0;

		while (keys.hasNext()) {
			String key = keys.next();
			String docters = "";
			for (int i = 0; i < careType.get(key).length; i++) {
				docters += careType.get(key)[i] + "ÀÇ»ç ";
			}
			reservationSubjects[index] = new JLabel(key + ") " + docters);
			reservationSubjects[index].setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
			docterPanel.add(reservationSubjects[index]);
		}
		informationHospital_SectionPanel.add(docterPanel);

		JLabel reservationTime = new JLabel("Áø·á½Ã°£");
		reservationTime.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		reservationTime.setBounds(20, 190, 120, 90);
		informationHospital_SectionPanel.add(reservationTime);

		ArrayList<String> careTime = hospital.getCareTimeList();
		JLabel careTimes[] = new JLabel[7];

		JPanel reservationTimePanel = new JPanel();
		reservationTimePanel.setLayout(new GridLayout(7, 1));
		reservationTimePanel.setVisible(true);
		reservationTimePanel.setBounds(140, 215, 1000, 300);
		for (int i = 0; i < careTime.size(); i++) {
			careTimes[i] = new JLabel(careTime.get(i));
			careTimes[i].setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
			careTimes[i].setBounds(120, 220, 500, 90);
			reservationTimePanel.add(careTimes[i]);
		}

		informationHospital_SectionPanel.add(reservationTimePanel);
		JButton modifyPasswdButton = new JButton("ºñ¹Ð¹øÈ£ º¯°æ");
		modifyPasswdButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		modifyPasswdButton.setBackground(Color.gray);
		modifyPasswdButton.setForeground(Color.white);
		informationHospitalPanel.add(modifyPasswdButton);
		modifyPasswdButton.setBounds(897, 700, 180, 50);
		modifyPasswdButton.setBackground(Color.gray);

		modifyPasswdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePasswordPage changePasswordPage = new ChangePasswordPage();
				changePasswordPage.passwordChangeGUI(pageHandler);
			}
		});

		this.add(informationHospitalPanel);
	}

}
