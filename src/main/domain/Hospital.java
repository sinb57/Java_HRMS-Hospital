package main.domain;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Hospital {
    private String hospitalId;
    private String hospitalName;
    private String phoneNumber;
    private String address;
    private HashMap<String, String[]> careTypeMap = new HashMap<>();
    private CareTime[] careTimeList = new CareTime[7];
	private String cookie;
    
    public void init(StringTokenizer dataTokenizer) {
    	cookie = dataTokenizer.nextToken();
    	read(dataTokenizer);
    }
    
    public void read(StringTokenizer dataTokenizer) {
    	hospitalId = dataTokenizer.nextToken().trim();
    	hospitalName = dataTokenizer.nextToken().trim();
    	phoneNumber = dataTokenizer.nextToken().trim();
    	address = dataTokenizer.nextToken().trim();

        String[] careTypeList = dataTokenizer.nextToken().trim().split(" ");
        
        for (String careType: careTypeList) {
        	String[] docterList = dataTokenizer.nextToken().trim().split(" ");
        	careTypeMap.put(careType, docterList);
        }
        
        for (int i=0; i<7; i++) {
        	try {
        		careTimeList[i] = new CareTime();
				careTimeList[i].read(dataTokenizer);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
    
    public String getCookie() {
    	return this.cookie;
    }
    
    public String getHospitalID() {
    	return hospitalId;
    }
    
    public String getHospitalName() {
    	return this.hospitalName;
    }
    
    public String getPhoneNumber() {
    	return this.phoneNumber;
    }

    public String getAddress() {
    	return address;
    }
    
    
    class CareTime {
    	private String dayOfWeek;
    	private LocalTime startCareTime;
    	private LocalTime endCareTime;
    	private LocalTime startLunchTime;
    	private LocalTime endLunchTime;
    	private boolean hasCareTime;
    	private boolean hasLunchTime;
    	private String rawData;
    	
    	void read(StringTokenizer careTimeTokenizer) throws IOException {
            StringTokenizer dataTokenizer = new StringTokenizer(careTimeTokenizer.nextToken()," ");
        	DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("HH:mm");

        	dayOfWeek = dataTokenizer.nextToken();
        	
        	if (dataTokenizer.nextToken().equals("x")) {
        		hasCareTime = false;
        		return;
        	}

        	hasCareTime = true;
        	
        	startCareTime = LocalTime.parse(dataTokenizer.nextToken(), formatter);
        	endCareTime = LocalTime.parse(dataTokenizer.nextToken(), formatter);
        	
        	if (dataTokenizer.nextToken().equals("x")) {
        		hasLunchTime = false;
        		return;
        	}

        	hasLunchTime = true;
        	
        	startLunchTime = LocalTime.parse(dataTokenizer.nextToken(), formatter);
        	endLunchTime = LocalTime.parse(dataTokenizer.nextToken(), formatter);
    	}
    	
    	
    	boolean equalsDate(String dayOfWeek) {
    		if (this.dayOfWeek.equals(dayOfWeek))
    			return true;
    		return false;
    	}
    	
    	boolean isClosedDay() {
    		if (hasCareTime)
    			return false;
    		return true;
    	}
    	
    	boolean isCareTimeNow(LocalTime now) {
    		if (hasCareTime) {
	    		if (now.isBefore(startCareTime))
	    			return false;
	    		if (now.isAfter(endCareTime))
	    			return false;
	    		return true;
    		}
    		return false;
    	}
    	
    	boolean isLunchTimeNow(LocalTime now) {
    		if (hasLunchTime) {
	    		if (now.isBefore(startLunchTime))
	    			return false;
	    		if (now.isAfter(endLunchTime))
	    			return false;
	    		return true;
    		}
    		return false;
    	}
    	
    	void print() {
    		String data = dayOfWeek + " ";

			data += "진료시간: ";
    		if (hasCareTime) {
	    		data += startCareTime.toString();
	    		data += "~";
	    		data += endCareTime.toString();
	    		data += "  ";
	    	
	    		data += "점심시간: ";
	    		if (hasLunchTime) {
		    		data += startLunchTime.toString();
		    		data += "~";
		    		data += endLunchTime.toString();
	    		}
	    		else {
	    			data += "진료 진행";
	    		}
    		}
    		else {
    			data += "진료 안함";
    		}
    	}
    	
    	public String toString() {
    		return rawData;
    	}
    }
}
