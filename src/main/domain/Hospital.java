package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String hospitalId;
    private String name;
    private String phoneNumber;
    private String address;
    private String password;

    
    private ArrayList<Patient> patientList = new ArrayList<>();
    private ArrayList<Reservation> reservationList = new ArrayList<>();
    private ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<>();
    
    
    
    public String getHospitalID() {
    	return hospitalId;
    }
    public String getHospitalPW() {
    	return password;
    }
    
    public void addReservation(Reservation reservation) {
    	reservationList.add(reservation);
        Patient patient = reservation.getPatient();

        if (!patientList.contains(patient)) {
            patientList.add(patient);
        }
    }

    
    //예약 리스트
    public void getReservationList(String patientID) {
       for (Reservation reservation : reservationList) {
        	if (reservation.getPatient().getPatientId().contains(patientID))
        		System.out.printf("환자 이름: %s \t 납부 여부: %s\n", reservation.getPatient().getUsername(), reservation.getPaid());
        }
    }
    public void getReservationList(LocalDate keyword) {
        for (Reservation reservation : reservationList) {
         	if (reservation.getReservationDate().equals(keyword))
         		System.out.printf("환자 이름: %s \t 납부 여부: %s\n", reservation.getPatient().getUsername(), reservation.getPaid());
         }
     }
    
    
    
        
    
    public void getReservationHistoryList(LocalDate Keyword) {
    	for (ReservationHistory history : reservationHistoryList) {
    		if (history.getReservationDay().equals(Keyword))
    			System.out.printf("환자 이름: %s \t접수날짜:%s \t진료시간:%s \t납부 여부: %s\n", 
    					history.getPatient().getUsername(), history.getReservationDay(), history.getCareTime(), history.getPaid());
    	}
    }
    
    public void getReservationHistoryList(String Keyword) {
    	for (ReservationHistory history : reservationHistoryList) {
    		if (history.getPatient().getPatientId().contains(Keyword))
    			System.out.printf("환자 이름: %s \t접수날짜:%s \t진료시간:%s \t납부 여부: %s\n", 
    					history.getPatient().getUsername(), history.getReservationDay(), history.getCareTime(), history.getPaid());
        }
    }

 
    
    
    

    
    public void read(/**/) {
    	/**/
    }
    
    
    public String getName() {
    	return this.name;
    }
    
    public String getPhoneNumber() {
    	return this.phoneNumber;
    }

    public String getAddress() {
    	return address;
    }
    
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    
}
