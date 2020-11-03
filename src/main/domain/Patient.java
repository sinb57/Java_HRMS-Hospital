package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Patient {
    private Long patientId;
    private String username;
    private String phoneNumber;
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>(); // 예약 현황
    private ArrayList<ReservationHistory> reservationHistoryList = new ArrayList<ReservationHistory>(); // 접수 내역
    
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
    
    // reservationList에서 reservation을 remove하고
    // reservationHistoryList에 reservationHistory를 add하면 됩니다.
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
