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

		// 로그인
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
		System.out.println("1 접수 현황    2 예약 현황     3 접수 내역    4 환자 정보    5 접수 내역 검색    0 종료");
		int num = scan.nextInt();
		if (num < 0 || 5 < num) {System.out.println("잘못 입력하셨습니다.");}
		
		while(true){
			switch (num) {
			case 1:
				System.out.println("접수 현황");
				printReservationListOfToday();
				break;
			case 2:
				System.out.println("예약 현황");
				printReservationList();
				break;
				
			case 3:
				System.out.println("접수 내역");
				printReservationHistoryList();
				break;
				
			case 4: 
				System.out.println("환자 정보 검색: ");
				searchPatientInform(scan);
				break;
			case 5: 
				System.out.println("접수 내역 검색: ");
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
		// 병원리스트 요청 후 데이터 저장
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

	
	
	
	public void reservationHistory(Scanner scan) { //접수 내역
		//LocalDate 입력
		reservationList(from, to);
	}
	
	
	
	
	// 환자 정보 보기 (이름, 연락처) -> 관련 접수 내역 표시
	public void searchPatientInform(Scanner scan) {
		System.out.println("검색어를 입력하세요: ");
		String keyword = scan.next();

		Patient patient = searchPatient(keyword);

		if (patient == null) {
			System.out.println("해당 환자가 없습니다.");
			return;
		}

		String patientID = patient.getPatientId();
		System.out.println("---------환자 정보------------");
		System.out.printf("아이디: %s \t이름: %s \t연락처: %s\n", 
				patient.getPatientId(), patient.getUsername(), patient.getPhoneNumber());
		hospital.getReservationHistoryList(patientID); //접수 내역
		
	}

	
	
	// 접수 내역 검색 (검색어: 환자정보, 접수일자)
	public void searchReservationList(Scanner scan) {
		System.out.println("(1)환자 아이디로 검색 (2)접수 일자로 검색");

		int num = scan.nextInt();
		String keyword = null;

		if (num == 1) {
			System.out.println("환자 아이디: ");
			keyword = scan.next();
			hospital.getReservationList(keyword);
		}

		else if (num == 2) {
			System.out.println("접수 일자: ");
			String reservationdate = scan.next();
			if (reservationdate.equals(date))
				reservationsToday();
			else
				hospital.getReservationHistoryList(reservationdate);
		}

		else
			System.out.println("잘못 입력하셨습니다.");

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
