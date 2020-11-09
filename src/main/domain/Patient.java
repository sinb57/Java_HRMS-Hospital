package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Patient {
    private String patientId;
    private String patientName;
    private String phoneNumber;
    private ArrayList<Reservation> reservationList = new ArrayList<>(); // 예약 현황
    private ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<>(); // 접수 내역
    
    
    public boolean matches(String keyword) {
    	if (keyword.equals(patientId))
    		return true;
    	if (keyword.contentEquals(phoneNumber))
    		return true;
    	if (keyword.equals(patientName))
    		return true;
    	return false;
    }
    
    
    public void addReservation(Reservation reservation) {
    	reservationList.add(reservation);
    }
    
	public void popReservation(Reservation reservation) {
		reservationList.remove(reservation);
	}
	

    public void addReservationHistory(ReservationHistory reservationHistory) {
    	reservationHistoryList.add(reservationHistory);
    }
    
	public void popReservationHistory(ReservationHistory reservationHistory) {
		reservationHistoryList.remove(reservationHistory);
	}
	

	
	
    public void moveReservationToHistory(Reservation reservation) {
    	
    	ReservationHistory reservationHistory = new ReservationHistory();

    	reservationHistoryList.add(reservationHistory);
    	
    	reservationList.remove(reservation);
    	
    }
    
    public String getPatientId() {
    	return patientId;
    }

	public String getPatientname() {
		return patientName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

}
