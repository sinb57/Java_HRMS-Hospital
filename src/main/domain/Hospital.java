package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String hospitalId;
    private String name;
    private String phoneNumber;
    private String joinedDay;
    private String address;

    private List<Patient> patientList = new ArrayList<>();
    private List<Patient> reservationList = new ArrayList<>();
    
    
    public void read(/**/) {
    	/**/
    }
    
    
    public String getName() {
    	return this.name;
    }
    
    public String getPhoneNumber() {
    	return this.phoneNumber;
    }
    
    public String getJoinedDay() {
    	return joinedDay;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    
}
