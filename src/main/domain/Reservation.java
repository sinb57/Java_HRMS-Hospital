package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reservation {
	private static int index = 0;
	private int reservationId;
	private Patient patient;
	private LocalDate reservationDate;
	private LocalDateTime reservationTime;
	private boolean paid;

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public String getPaid() {
		return paid ? "O" : "X";
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDateTime getReservationTime() { return reservationTime; }

	public Patient getPatient() { return patient; }

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	
	
	public boolean matches(long reservationId2) {
		if (reservationId2 == reservationId)
			return true;
		return false;
	}
	
	public long getReservationID() {
		return reservationId;
	}

}
