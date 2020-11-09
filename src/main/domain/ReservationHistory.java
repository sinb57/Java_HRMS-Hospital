package main.domain;

import java.time.LocalDateTime;

public class ReservationHistory {
	private Long reservationId;
	private LocalDateTime reservationDay;
	private LocalDateTime reservationTime;
	private boolean careStatus;
    private LocalDateTime careTime;
    private boolean payStatus;
    private LocalDateTime payTime;
    private Patient patient;
    
    public Patient getPatient() { return patient; }
    public LocalDateTime getReservationDay() {return reservationDay;}
    public String getPaid() {
		return isPayStatus() ? "O" : "X";
	}
	
    
    
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