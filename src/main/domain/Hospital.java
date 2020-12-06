package main.domain;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

		for (String careType : careTypeList) {
			String[] docterList = dataTokenizer.nextToken().trim().split(" ");
			careTypeMap.put(careType, docterList);
		}

		for (int i = 0; i < 7; i++) {
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

	public String getHospitalId() {
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

	public HashMap<String, String[]> getCareTypeMap() {
		return careTypeMap;
	}

	public ArrayList<String> getCareTimeList() {
		ArrayList<String> careTimeStringList = new ArrayList<>();

		for (CareTime careTime : careTimeList) {
			careTimeStringList.add(careTime.toString());
		}
		return careTimeStringList;
	}

	public class CareTime {
		private String dayOfWeek;
		private LocalTime startCareTime;
		private LocalTime endCareTime;
		private LocalTime startLunchTime;
		private LocalTime endLunchTime;
		private boolean hasCareTime;
		private boolean hasLunchTime;

		void read(StringTokenizer careTimeTokenizer) throws IOException {
			StringTokenizer dataTokenizer = new StringTokenizer(careTimeTokenizer.nextToken(), " ");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

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

		public String toString() {

			if (!hasCareTime)
				return String.format("%s : 진료안함", dayOfWeek);

			if (!hasLunchTime)
				return String.format("%s : %s~%s", dayOfWeek, startCareTime, endCareTime);

			return String.format("%s : %s~%s (식사시간 %s~%s)", dayOfWeek, startCareTime, endCareTime, startLunchTime,
					endLunchTime);
		}

	}
}
