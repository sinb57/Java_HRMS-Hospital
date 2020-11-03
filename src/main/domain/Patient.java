package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Patient {
    private String patientId;
    private String username;
    private String phoneNumber;
    private ArrayList<Reservation> reservationList = new ArrayList<>(); // 예약 현황
    private ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<>(); // 접수 내역
    
    public String getPatientId() {
    	return patientId;
    }
    
    public boolean matches(String patientId) {
    	if (this.patientId.contentEquals(patientId))
    		return true;
    	return false;
    }
    
    public void addReservation(Reservation reservation) {
    	reservationList.add(reservation);
    }
    
    // reservationList에서 reservation을 remove하고
    // reservationHistoryList에 reservationHistory를 add하면 됩니다.
    public void moveReservationToHistory(Reservation reservation) {
    }
    


}
