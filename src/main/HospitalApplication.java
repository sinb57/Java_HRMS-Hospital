package main;

import main.page.PageHandler;

public class HospitalApplication {

	public void run() {
		PageHandler pageHandler = new PageHandler();
		pageHandler.start();
	}

	public static void main(String[] args) {
		HospitalApplication app = new HospitalApplication();
		app.run();

	}

}
