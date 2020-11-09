package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String patientId;
    private String userName;
    private String phoneNumber;
    private Long point;
    private ArrayList<Reservation> reservationList = new ArrayList<>(); // ���� ��Ȳ
    private ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<>(); // ���� ����
    
    
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
	
	
	 // reservationList���� reservation�� remove�ϰ�
    // reservationHistoryList�� reservationHistory�� add�ϸ� �˴ϴ�.
    public void moveReservationToHistory(Reservation reservation) {
    	
    	ReservationHistory reservationHistory = new ReservationHistory();
    	//���� �Ű� ���
    	reservationHistoryList.add(reservationHistory);
    	
    	reservationList.remove(reservation);
    	
    }
    
}
