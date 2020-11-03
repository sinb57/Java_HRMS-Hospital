package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reservation {
	private Long reservationId;
	private ArrayList<Patient> reservationPatientList;
	private LocalDateTime reservationDay;
	private LocalDateTime reservationTime;
	
	public ArrayList<Patient> getReservationPatientList() {
		return reservationPatientList;
	}
	
	public void setReservationDay(LocalDateTime reservationDay) {
		this.reservationDay = reservationDay;
	}
	
	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}
	
	private int indexOf(Patient patient) {
		int index = 0;
		String patientId = "";
		
		for (Patient reservationPatient: reservationPatientList) {
			patientId = reservationPatient.getPatientId();
			if (patient.matches(patientId))	
				return index;			
			index++;
		}
		return -1;
	}
	

	public void addPatient(Patient patient) {
		reservationPatientList.add(patient);
	}
	
	public void popPatient(Patient patient) {
		int index = reservationPatientList.indexOf(patient);
		
		if (index == -1)
			return;
		
		reservationPatientList.remove(index);
	}

}
