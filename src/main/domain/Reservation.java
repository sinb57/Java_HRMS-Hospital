package main.domain;

import java.util.StringTokenizer;

public class Reservation {
	private String reservationState;
	private String patientId;
	private String hospitalId;
	private String reservationDate;
	private String reservationTime;
	private String careTime;
	private String docterName;
	private String careType;
	private String[] symptomList; 

    public void read(StringTokenizer dataTokenizer) {
    	
    	reservationState = dataTokenizer.nextToken().trim();
    	System.out.println(reservationState);
    	patientId = dataTokenizer.nextToken().trim();
    	System.out.println(patientId);
    	hospitalId = dataTokenizer.nextToken().trim();
    	
        reservationDate = dataTokenizer.nextToken().trim();
        reservationTime = dataTokenizer.nextToken().trim();

        careType = dataTokenizer.nextToken().trim();
        symptomList = dataTokenizer.nextToken().trim().split(" ");
        
    	if (reservationState.equals("진료완료")) {
	        careTime = dataTokenizer.nextToken().trim();
            docterName = dataTokenizer.nextToken().trim();
    	}
    }
    
    
    
	public String getReservationState() {
		return reservationState;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public String getCareTime() {
		return careTime;
	}

	public String getDocterName() {
		return docterName;
	}

	public String getCareType() {
		return careType;
	}

	public String[] getSymptomList() {
		return symptomList;
	}
	public String getData() {
    	String data = "";
    	data += reservationState + "\n";
    	data += patientId + "\n";
    	data += hospitalId + "\n";

    	data += reservationDate + "\n";
    	data += reservationTime + "\n";

    	data += careType + "\n";
    	for (String symptom : symptomList)
    		data += symptom + " ";
    	data += "\n";

    	if (reservationState.equals("진료완료")) {
	    	data += careTime + "\n";
	    	data += docterName + "\n";
    	}
    	
        return data;
    }
}
