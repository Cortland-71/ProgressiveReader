package com.progressiveReader.data;

import java.util.*;

public class User {
	private List<String> userData = new ArrayList<>();
	
	public User() {}
	
	public void setUser(String name, String date, String time, String location) {
		userData.add(name);
		userData.add(location);
		userData.add(date);
		userData.add(time);
	}
	
	public List<String> getUserData() {
		return userData;
	}
}
