package main.service;

import java.util.*;
import main.socket.*;
import main.domain.*;

public class HospitalService {
	
	public ArrayList<Patient> patientList = new ArrayList<Patient>();
	public ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	
	/*
	 * ���� ��Ȳ ����Ʈ�� ��ȯ�ϴ� �޼ҵ� (������ ���� ����Ʈ)
	 */
	
	/*
	 * ���� ��Ȳ ����Ʈ�� ��ȯ�ϴ� �޼ҵ�
	 */
	
	/*
	 * Ư�� �Ⱓ�� ���� ������ ��ȯ�ϴ� �޼ҵ�
	 */
	
	
	
	
	public Patient searchPatient(long patientId) {
		
		for (Patient patient: patientList) {
			if (patient.matches(patientId))
				return patient;
		}
			
		return null;
	}
	
	
}
