package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String patientId;
    private String userName;
    private String phoneNumber;
    private Long point;
    private ArrayList<Reservation> reservationList = new ArrayList<>(); // 예약 현황
    private ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<>(); // 접수 내역
    
    
    public String getPatientId() {
    	return patientId;
    }
    
    public boolean matches(String keyword) {
    	if (keyword.equals(patientId))
    		return true;
    	if (keyword.contentEquals(phoneNumber))
    		return true;
    	if (keyword.equals(userName))
    		return true;
    	return false;
    }
    
    public void addReservation(Reservation reservation) {
    	reservationList.add(reservation);
    }
    
	public void popReservation(Reservation reservation) {
		int index = reservationList.indexOf(reservation);
		
		if (index == -1)
			return;
		
		reservationList.remove(index);
	}
	
	
	private int indexOf(Reservation reservationI) {
		int index = 0;
		long reservationId = 0;
		
		for (Reservation reservation: reservationList) {
			reservationId = reservation.getReservationID();
			if (reservationI.matches(reservationId))	
				return index;			
			index++;
		}
		return -1;
	}

	public String getUsername() {
		return userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	
	 // reservationList에서 reservation을 remove하고
    // reservationHistoryList에 reservationHistory를 add하면 됩니다.
    public void moveReservationToHistory(Reservation reservation) {
    	
    	ReservationHistory reservationHistory = new ReservationHistory();
    	//정보 옮겨 닮기
    	reservationHistoryList.add(reservationHistory);
    	
    	reservationList.remove(reservation);
    	
    }
    
}
