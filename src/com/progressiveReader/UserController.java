package com.progressiveReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.progressiveReader.view.View;

public class UserController implements ActionListener {
	
	private String date;
	private String time;
	private String location;
	
	private Driver driver;
	public UserController(Driver driver) {
		this.driver = driver;
		this.driver.getView().getUserPage().setBackButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == driver.getView().getUserPage().getBackButton()) {
			View.cl.show(driver.getView().getRootPanel(), "1");
			return;
		}
		submitNewUser();
	}
	
	private void submitNewUser() {
		if (driver.getView().getUserPage().getNameFieldText().trim().isEmpty()) {
			driver.getView().getUserPage().clearNameField();
			JOptionPane.showMessageDialog(null,"You must enter your name to proceed", "No name entered",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String name = driver.getView().getUserPage().getNameFieldText();
		driver.getUser().setUser(name, date, time, location); 
		View.cl.show(driver.getView().getRootPanel(), "3");
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
}
