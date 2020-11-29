package main.socket;

import java.util.StringTokenizer;

public class SocketHandler {

	private Client client;

	public boolean run(String ipAddress, int port) {
		try {
			client = new Client(ipAddress, port);

		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public StringTokenizer login(String userId, String userPw) {
		
		String requestData = "POST /auth/login";
		requestData += "\n" + userId;
		requestData += "\n" + userPw;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Login Failed"))
			return null;

		return responseData;
	}
	
	public StringTokenizer requestSelfInfo(String token) {
		
		String requestData = "GET /auth/me";
		requestData += "\n" + token;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Self Info Failed"))
			return null;

		return responseData;
	}
	
	public boolean modifySelfPw(String cookie, String passwdFrom, String passwdTo) {
		
		String requestData = "PUT /auth/me";
		requestData += "\n" + cookie;
		requestData += "\n" + passwdFrom;
		requestData += "\n" + passwdTo;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Modify Self Password Failed"))
			return false;
		
		return true;
	}

	
	public StringTokenizer requestReservationList(String cookie, String reservationDateFrom, String reservationDateTo, String searchType, String keywords) {
		String requestData = "GET /hospitals/reservations/list";
		requestData += "\n" + cookie;
		requestData += "\n" + reservationDateFrom + " " + reservationDateTo;
		requestData += "\n" + searchType;
		requestData += "\n" + keywords;

		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Reservation List Failed"))
			return null;
		
		return responseData;
	}
	
	public StringTokenizer requestReservationListOfPatient(String cookie, String patientId) {
		String requestData = "GET /hospitals/reservations/list/" + patientId;
		requestData += "\n" + cookie;

		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Reservation List Failed"))
			return null;
		
		return responseData;
	}
	
	public boolean processReservation(String cookie, String patientId, String reservationDate, String reservationTime, String docterName) {
		String requestData = "POST /hospitals/reservations/" + patientId;
		requestData += "\n" + cookie;
		requestData += "\n" + reservationDate;
		requestData += "\n" + reservationTime;
		requestData += "\n" + docterName;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.contentEquals("Process Reservation Failed"))
			return false;
		
		return true;
	}
	
	public boolean cancelReservation(String cookie, String patientId, String reservationDate, String reservationTime) {

		String requestData = "DELETE /hospitals/reservations/" + patientId;
		requestData += "\n" + cookie;

		StringTokenizer responseData = client.request(requestData);

		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Delete Reservation Failed"))
			return false;
		
		return true;
	}
}
