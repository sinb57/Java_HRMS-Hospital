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
		
		String requestData = "POST /auth/Login";
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
	
	public boolean modifySelfPw(String token, String passwd) {
		
		String requestData = "PUT /auth/me";
		requestData += "\n" + token;
		requestData += "\n" + passwd;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Modify Self Password Failed"))
			return false;
		
		return true;
	}
	

	public StringTokenizer requestPatientInfo(String token, String patientId) {
		
		String requestData = "GET /patients/" + patientId;
		requestData += "\n" + token;
		
		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Patient Info Failed"))
			return null;
		
		return responseData;
	}

	
	public StringTokenizer requestReservationList(String token) {
		String requestData = "GET /hospitals/reservations";
		requestData += "\n" + token;

		StringTokenizer responseData = client.request(requestData);
		
		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("Get Reservation List Failed"))
			return null;
		
		return responseData;
	}
	
	public StringTokenizer deleteReservation(String token, String patientId) {

		String requestData = "DELETE /hospitals/reservations/" + patientId;
		requestData += "\n" + token;

		StringTokenizer responseData = client.request(requestData);

		String responseHeader = responseData.nextToken();
		
		if (responseHeader.equals("DELETE Reservation Failed"))
			return null;
		
		return responseData;
	}
}
