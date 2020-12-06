package main.domain;

import java.util.ArrayList;
import java.util.StringTokenizer;

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

	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	public void addReservation(Reservation reservation) {
		reservationList.add(reservation);
	}

	public void popReservation(Reservation reservation) {
		reservationList.remove(reservation);
	}

	public Reservation searchReservaion(String reservationDate, String reservationTime) {
		for (Reservation reservation : reservationList)
			if (reservation.matchesDateTime(reservationDate, reservationTime))
				return reservation;

		return null;
	}

	public Object[][] getReservationList() {
		int count = reservationList.size();

		Object values[][] = new Object[reservationList.size()][6];

		for (int i = 0; i < count; i++) {
			Reservation reservation = reservationList.get(i);
			values[i][0] = i + 1;
			values[i][1] = reservation.getDocterName();
			values[i][2] = reservation.getCareType();
			values[i][3] = reservation.getReservationDate();
			values[i][4] = reservation.getReservationTime();
			values[i][5] = patientId + " " + reservation.getReservationDate() + " " + reservation.getReservationTime();
		}

		return values;
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

	public void read(StringTokenizer patientTokenizer) {
		patientId = patientTokenizer.nextToken().trim();
		patientName = patientTokenizer.nextToken().trim();
		phoneNumber = patientTokenizer.nextToken().trim();
	}

}
