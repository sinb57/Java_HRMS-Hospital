package main.page;

import java.awt.BorderLayout;

import javax.swing.*;

import main.service.HospitalService;

public class PageHandler extends JFrame {
	public HospitalService service = new HospitalService();
	final String PATH = "./image/";
	private Header header = null;
	String patientId;
	String reservationDate;
	String reservationTime;

	public PageHandler() {
		service.connect("localhost", 9999);

		this.setTitle("거긴 어때");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1240, 845);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		header = new Header(this);
	}

	public void start() {
		change("LoginPage");
	}

	void change(String panelName) {

		JPanel page = null;
		String title = "";

		switch (panelName) {
		case "LoginPage":
			page = new LoginPage(this);
			title = "로그인";
			break;
		case "ReservationStatusPage":
			page = new ReservationStatusPage(this);
			break;
		case "ReservationHistoryPage":
			page = new ReservationHistoryPage(this);
			break;

		case "PatientInformationPage":
			page = new PatientInformationPage(this, patientId, reservationDate, reservationTime);
			break;
		case "HospitalInformationPage":
			page = new HospitalInformationPage(this);
			break;
		}

		getContentPane().removeAll();

		if (!title.contentEquals("로그인")) {
			getContentPane().add(header, BorderLayout.PAGE_START);
		}
		getContentPane().add(page);
		revalidate();
		repaint();
	}

	void reload() {

	}
}