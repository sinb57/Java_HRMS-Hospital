package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.domain.Reservation;
import main.service.HospitalService;

// GUI Class
public class HospitalApplication {
	private HospitalService service = new HospitalService();

	public void run() {
		Scanner scanner = new Scanner(System.in);

	}
	
	public static void main(String[] args) {		
    	HospitalApplication app = new HospitalApplication();
    	app.run();

	}

}
