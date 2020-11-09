package main.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import main.socket.*;
import main.domain.*;

public class HospitalService {
	public static LocalDate date = LocalDate.now();
	public static LocalDateTime time = LocalDateTime.now();
	private Hospital hospital = new Hospital();
	private ArrayList<Patient> patientList = new ArrayList<>();
	private SocketHandler socketHandler = new SocketHandler();

	
	public boolean connect(String ipAddress, int port) {
		return socketHandler.run(ipAddress, port);
	}
	
	// Login
	public boolean login(String userId, String userPw) {
		
		StringTokenizer tokenizer = socketHandler.login(userId, userPw);
		
		hospital.init(tokenizer);
		
		return true;
	}
	
	// Logout
	public void logout() {
		//socketHandler.logout();
		hospital = null;
		patientList.clear();
	}


	


}
