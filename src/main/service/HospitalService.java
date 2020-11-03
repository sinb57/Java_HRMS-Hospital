package main.service;

import java.util.*;
import main.socket.*;
import main.domain.*;

public class HospitalService {
	
	public ArrayList<Patient> patientList = new ArrayList<Patient>();
	public ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	
	/*
	 * 접수 현황 리스트를 반환하는 메소드 (오늘자 예약 리스트)
	 */
	
	/*
	 * 예약 현황 리스트를 반환하는 메소드
	 */
	
	/*
	 * 특정 기간의 예약 내역을 반환하는 메소드
	 */
	
	
	
	
	public Patient searchPatient(long patientId) {
		
		for (Patient patient: patientList) {
			if (patient.matches(patientId))
				return patient;
		}
			
		return null;
	}
	
	
}
