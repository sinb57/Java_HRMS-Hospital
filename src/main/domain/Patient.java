package main.domain;


import java.util.ArrayList;

public class Patient {
    private String patientId;
    private String patientName;
    private String phoneNumber;
    private ArrayList<Reservation> reservationList = new ArrayList<>();
    
    
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
