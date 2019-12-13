package com.progressiveReader.data;

public class User {
	
	private String name;
	private String date;
	private String time;
	private String location;
	
	public User() {}
	
	public void setUser(String name, String date, String time, String location) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.location = location;
	}
	public String getName() {
		return name;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", date=" + date + ", time=" + time + ", location=" + location + "]";
	}
	
	
}
