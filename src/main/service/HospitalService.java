package main.service;

import java.util.*;
import main.socket.*;
import main.domain.*;

public class HospitalService {
	
	public static Hospital hospital = new Hospital();
	public static ArrayList<Patient> patientList = new ArrayList<>();
	public static ArrayList<Reservation> reservationList = new ArrayList<>();
	public static ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<>();

	/*
	 * ���� ��Ȳ ����Ʈ�� ��ȯ�ϴ� �޼ҵ� (������ ���� ����Ʈ)
	 */
	
	/*
	 * ���� ��Ȳ ����Ʈ�� ��ȯ�ϴ� �޼ҵ�
	 */
	
	/*
	 * Ư�� �Ⱓ�� ���� ������ ��ȯ�ϴ� �޼ҵ�
	 */
	
	
	
	
	public Patient searchPatient(String patientId) {
		
		for (Patient patient: patientList) {
			if (patient.matches(patientId))
				return patient;
		}
			
		return null;
	}
	
	
}
