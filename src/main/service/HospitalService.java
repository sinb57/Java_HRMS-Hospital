package main.service;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.*;
import main.socket.*;
import main.domain.*;

public class HospitalService {
	static public LocalDate date = LocalDate.now();
	static public LocalDateTime time = LocalDateTime.now();
	static private Hospital hospital = new Hospital();
	static public ArrayList<Patient> patientList = new ArrayList<>();

	
	
	public static void main(String[] args) {		
		HospitalService service = Login(id, password);
		
		setTime(time);
		service.runMenu();
	}

	
	
	void runMenu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1 ���� ��Ȳ    2 ���� ��Ȳ     3 ���� ����    4 ȯ�� ����    5 ���� ���� �˻�    0 ����");
		int num = scan.nextInt();
		if (num > 5) {System.out.println("�߸� �Է��ϼ̽��ϴ�.");}
		
		
		
		while(true){
			switch (num) {
			case 1:
				System.out.println("���� ��Ȳ");
				reservationsToday();
				break;
			case 2:
				System.out.println("���� ��Ȳ");
				reservationList(date);
				break;
				
			
				
			case 3: //to from
				System.out.println("���� ����");
				reservationList(from, to);
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
				Logout();
				break;
			}
			if (num == 0) break;
		}
	}
	
	
	
	
	
	public Hospital searchHospital(String keyword) {
		// ��������Ʈ ��û �� ������ ����
	    ArrayList<Hospital> hospitalList = new ArrayList<>();
	    
	    hospitalList.add(hospitalExample1);
	    hospitalList.add(hospitalExample2);
	    hospitalList.add(hospitalExample3);
	    
	    for (Hospital hospital: hospitalList) {
	    	if (hospital.getHospitalID().equals(keyword))
	    		return hospital;
	    }
		return null;
		
	}
	// �α���
	public static HospitalService Login(String ID, String password) {
		HospitalService instance = new HospitalService();
		if (instance.searchHospital(ID) == null) {
			System.out.println("�������� �ʴ� ���̵��Դϴ�.");
			return null;
		}
		hospital = instance.searchHospital(ID);
		
		if (hospital.getHospitalPW().equals(password)) {
			System.out.println("�α��� �Ǿ����ϴ�.");
			System.out.printf("������: %s \t�ּ�: %s \t����ó: %s\n", hospital.getName(), hospital.getAddress(),
					hospital.getPhoneNumber());
			return instance;
		}
		
		System.out.println("��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
		return null;
	}

	
	// �α׾ƿ�
	public void Logout() {
		hospital = null;
		patientList.clear();
	}

	static public void setTime(LocalDateTime time) {
		HospitalService.time = time;
	}

	
	
	
	
	/*
	 * ���� ��Ȳ ����Ʈ�� ��ȯ�ϴ� �޼ҵ� (������ ���� ����Ʈ)
	 */
	public void reservationsToday() {
		hospital.getReservationList(time.toLocalDate());
	}
	/*
	 * ���� ��Ȳ ����Ʈ�� ��ȯ�ϴ� �޼ҵ�
	 */
	public void reservationList(LocalDate date) {
		hospital.getReservationList(date);
	}
	/*
	 * Ư�� �Ⱓ�� ���� ������ ��ȯ�ϴ� �޼ҵ�
	 */
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

}
