package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Patient {
    private String patientId;
    private String username;
    private String phoneNumber;
    private ArrayList<Reservation> reservationList = new ArrayList<>(); // ���� ��Ȳ
    private ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<>(); // ���� ����
    
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
    
    // reservationList���� reservation�� remove�ϰ�
    // reservationHistoryList�� reservationHistory�� add�ϸ� �˴ϴ�.
    public void moveReservationToHistory(Reservation reservation) {
    }
    


}
