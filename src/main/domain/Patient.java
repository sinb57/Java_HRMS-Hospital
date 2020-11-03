package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Patient {
    private Long patientId;
    private String username;
    private String phoneNumber;
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>(); // ���� ��Ȳ
    private ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<ReservationHistory>(); // ���� ����
    
    public long getPatientId() {
    	return patientId;
    }
    
    public boolean matches(long patientId) {
    	if (this.patientId == patientId)
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
    

    private class ReservationHistory {
    	private Long reservationId;
    	private LocalDateTime reservationDay;
    	private LocalDateTime reservationTime;
    	private boolean careStatus;
        private LocalDateTime careTime;
        private boolean payStatus;
        private LocalDateTime payTime;
        


        public boolean isCareStatus() {
			return careStatus;
		}
		public void setCareStatus(boolean careStatus) {
			this.careStatus = careStatus;
		}
		public LocalDateTime getCareTime() {
			return careTime;
		}
		public void setCareTime(LocalDateTime careTime) {
			this.careTime = careTime;
		}
		public boolean isPayStatus() {
			return payStatus;
		}
		public void setPayStatus(boolean payStatus) {
			this.payStatus = payStatus;
		}
		public LocalDateTime getPayTime() {
			return payTime;
		}
		public void setPayTime(LocalDateTime payTime) {
			this.payTime = payTime;
		}
    	
    }
}
