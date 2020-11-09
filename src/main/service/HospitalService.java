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
		System.out.println("1 접수 현황    2 예약 현황     3 접수 내역    4 환자 정보    5 접수 내역 검색    0 종료");
		int num = scan.nextInt();
		if (num > 5) {System.out.println("잘못 입력하셨습니다.");}
		
		
		
		while(true){
			switch (num) {
			case 1:
				System.out.println("접수 현황");
				reservationsToday();
				break;
			case 2:
				System.out.println("예약 현황");
				reservationList(date);
				break;
				
			
				
			case 3: //to from
				System.out.println("접수 내역");
				reservationList(from, to);
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
				Logout();
				break;
			}
			if (num == 0) break;
		}
	}
	
	
	
	
	
	public Hospital searchHospital(String keyword) {
		// 병원리스트 요청 후 데이터 저장
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
	// 로그인
	public static HospitalService Login(String ID, String password) {
		HospitalService instance = new HospitalService();
		if (instance.searchHospital(ID) == null) {
			System.out.println("존재하지 않는 아이디입니다.");
			return null;
		}
		hospital = instance.searchHospital(ID);
		
		if (hospital.getHospitalPW().equals(password)) {
			System.out.println("로그인 되었습니다.");
			System.out.printf("병원명: %s \t주소: %s \t연락처: %s\n", hospital.getName(), hospital.getAddress(),
					hospital.getPhoneNumber());
			return instance;
		}
		
		System.out.println("비밀번호가 올바르지 않습니다.");
		return null;
	}

	
	// 로그아웃
	public void Logout() {
		hospital = null;
		patientList.clear();
	}

	static public void setTime(LocalDateTime time) {
		HospitalService.time = time;
	}

	
	
	
	
	/*
	 * 접수 현황 리스트를 반환하는 메소드 (오늘자 예약 리스트)
	 */
	public void reservationsToday() {
		hospital.getReservationList(time.toLocalDate());
	}
	/*
	 * 예약 현황 리스트를 반환하는 메소드
	 */
	public void reservationList(LocalDate date) {
		hospital.getReservationList(date);
	}
	/*
	 * 특정 기간의 예약 내역을 반환하는 메소드
	 */
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

}
