package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Hospital {
    private String hospitalId;
    private String hospitalName;
    private String phoneNumber;
    private String address;
    private String careTime;
    private String token;
    
    public void init(StringTokenizer dataTokenizer) {
    	token = dataTokenizer.nextToken();
    	read(dataTokenizer);
    }
    
    public void read(StringTokenizer dataTokenizer) {
    	hospitalId = dataTokenizer.nextToken();
    	hospitalName = dataTokenizer.nextToken();
    	phoneNumber = dataTokenizer.nextToken();
    	address = dataTokenizer.nextToken();
    	careTime = "";
    	
    	String boundary = dataTokenizer.nextToken();
    	String data = dataTokenizer.nextToken();
    	
    	while (boundary.equals(data) == false) {
    		careTime += dataTokenizer.nextToken();
    	}
    }


    // Temporary Method -> Drop after GUI linked
    public void print() {
		System.out.printf("[%s] 병원명: %s\n", hospitalId, hospitalName);
		System.out.printf("주소: %s\n", address);
		System.out.printf("연락처: %s\n", phoneNumber);
		System.out.printf("진료시간\n");
		for(String time: careTime.split("\n"))
			System.out.println(time);
		
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
    
}
