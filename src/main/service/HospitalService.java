package main.service;

import java.util.*;
import main.socket.*;
import main.domain.*;

public class HospitalService {
	private Hospital hospital = new Hospital();
	private SocketHandler socketHandler = new SocketHandler();

	public boolean connect(String ipAddress, int port) {
		return socketHandler.run(ipAddress, port);
	}

	public boolean login(String userId, String userPw) {
		StringTokenizer tokenizer = socketHandler.login(userId, userPw);

		if (tokenizer == null)
			return false;

		hospital.init(tokenizer);
		return true;
	}

	public Hospital getSelfInfo() {

		String cookie = hospital.getCookie();

		StringTokenizer tokenizer = socketHandler.requestSelfInfo(cookie);

		if (tokenizer == null)
			return null;

		hospital.read(tokenizer);

		return hospital;
	}

	public boolean modifyHospitalPw(String passwdFrom, String passwdTo, String passwdRe) {
		if (passwdTo.equals(passwdRe)) {
			String cookie = hospital.getCookie();
			if (socketHandler.modifySelfPw(cookie, passwdFrom, passwdTo))
				return true;
		}
		return false;
	}

	public ArrayList<Reservation> getReservationList(String state, String reservationDateFrom, String reservationDateTo,
			String searchType, String keywords) {

		String cookie = hospital.getCookie();

		if (searchType == null)
			searchType = " ";

		if (keywords == null)
			keywords = " ";

		StringTokenizer tokenizer = socketHandler.requestReservationList(cookie, state, reservationDateFrom,
				reservationDateTo, searchType, keywords);

		if (tokenizer == null)
			return null;

		ArrayList<Reservation> reservationList = new ArrayList<>();

		while (tokenizer.hasMoreTokens()) {
			Reservation reservation = new Reservation();
			reservation.read(tokenizer);
			reservationList.add(reservation);
		}

		return reservationList;
	}

	public ArrayList<Reservation> getReservationListOfPatient(String patientId) {

		String cookie = hospital.getCookie();

		StringTokenizer tokenizer = socketHandler.requestReservationListOfPatient(cookie, patientId);

		if (tokenizer == null)
			return null;

		ArrayList<Reservation> reservationList = new ArrayList<>();

		while (tokenizer.hasMoreTokens()) {
			Reservation reservation = new Reservation();
			reservation.read(tokenizer);
			reservationList.add(reservation);
		}

		return reservationList;
	}

	public Patient getPateintInfo(String patientId) {
		String cookie = hospital.getCookie();

		StringTokenizer tokenizer = socketHandler.requestPatientInfo(cookie, patientId);

		if (tokenizer == null)
			return null;

		Patient patient = new Patient();
		patient.read(tokenizer);

		return patient;
	}

	public boolean processReservation(String patientId, String reservationDate, String reservationTime,
			String docterName) {

		String cookie = hospital.getCookie();

		if (socketHandler.processReservation(cookie, patientId, reservationDate, reservationTime, docterName))
			return true;

		return false;
	}

	public boolean cancelReservation(String patientId, String reservationDate, String reservationTime) {

		String cookie = hospital.getCookie();

		if (socketHandler.cancelReservation(cookie, patientId, reservationDate, reservationTime))
			return true;

		return false;
	}
}
