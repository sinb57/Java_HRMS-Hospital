package main;

import main.domain.Hospital;
import main.domain.Patient;
import main.service.HospitalService;
import main.service.PatientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// GUI Class
public class HospitalApplication {
	private HospitalService service = new HospitalService();
	private Scanner scan = new Scanner(System.in);

	public void run() {
		
		service.connect("localhost",  9999);

		// �α���
        String userId = scan.next();
        String userPw = scan.next();
        if (service.login(userId, userPw)) {
        	System.out.println("Login Success");
        }
        else {
        	System.out.println("Login Fail");
        	return;
        }
        
        runMenu();
	}
	
	void runMenu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1 ���� ��Ȳ    2 ���� ��Ȳ     3 ���� ����    4 ȯ�� ����    5 ���� ���� �˻�    0 ����");
		int num = scan.nextInt();
		if (num < 0 || 5 < num) {System.out.println("�߸� �Է��ϼ̽��ϴ�.");}
		
		while(true){
			switch (num) {
			case 1:
				System.out.println("���� ��Ȳ");
				printReservationListOfToday();
				break;
			case 2:
				System.out.println("���� ��Ȳ");
				printReservationList();
				break;
				
			case 3:
				System.out.println("���� ����");
				printReservationHistoryList();
				break;
				
			case 4: 
				System.out.println("ȯ�� ���� �˻�: ");
				searchPatientInform(scan);
				break;
			case 5: 
				System.out.println("���� ���� �˻�: ");
				searchReservationList(scan);
				break;
				
			default:
				num = 0;
				service.logout();
				break;
			}
			if (num == 0) break;
		}
	}
	
	public Hospital searchHospital(String keyword) {
		// ��������Ʈ ��û �� ������ ����
	    ArrayList<Hospital> hospitalList = new ArrayList<>();
	    
	    for (Hospital hospital: hospitalList) {
	    	if (hospital.getHospitalID().equals(keyword))
	    		return hospital;
	    }
		return null;
		
	}
	

	public void printReservationListOfToday() {
		
	}

	public void printReservationList() {
		service.getReservationList();
	}


	public void printReservationHistoryList() {
		service.getReservationHistoryList();
	}


	public void reservationList(LocalDate from, LocalDate to) {
		long days = 0;
		while (from.plusDays(days).isBefore(to.plusDays(1))) {
			reservationList(from.plusDays(days++));
		}
	}

	
	
	
	public void reservationHistory(Scanner scan) { //���� ����
		//LocalDate �Է�
		reservationList(from, to);
	}
	
	
	
	
	// ȯ�� ���� ���� (�̸�, ����ó) -> ���� ���� ���� ǥ��
	public void searchPatientInform(Scanner scan) {
		System.out.println("�˻�� �Է��ϼ���: ");
		String keyword = scan.next();

		Patient patient = searchPatient(keyword);

		if (patient == null) {
			System.out.println("�ش� ȯ�ڰ� �����ϴ�.");
			return;
		}

		String patientID = patient.getPatientId();
		System.out.println("---------ȯ�� ����------------");
		System.out.printf("���̵�: %s \t�̸�: %s \t����ó: %s\n", 
				patient.getPatientId(), patient.getUsername(), patient.getPhoneNumber());
		hospital.getReservationHistoryList(patientID); //���� ����
		
	}

	
	
	// ���� ���� �˻� (�˻���: ȯ������, ��������)
	public void searchReservationList(Scanner scan) {
		System.out.println("(1)ȯ�� ���̵�� �˻� (2)���� ���ڷ� �˻�");

		int num = scan.nextInt();
		String keyword = null;

		if (num == 1) {
			System.out.println("ȯ�� ���̵�: ");
			keyword = scan.next();
			hospital.getReservationList(keyword);
		}

		else if (num == 2) {
			System.out.println("���� ����: ");
			String reservationdate = scan.next();
			if (reservationdate.equals(date))
				reservationsToday();
			else
				hospital.getReservationHistoryList(reservationdate);
		}

		else
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");

	}

	
	
	public Patient searchPatient(String keyword) {

		for (Patient patient : patientList) {
			if (patient.matches(keyword))
				return patient;
		}

		return null;
	}
	
	
	
	public static void main(String[] args) {		
    	HospitalApplication app = new HospitalApplication();
    	app.run();

	}

}
