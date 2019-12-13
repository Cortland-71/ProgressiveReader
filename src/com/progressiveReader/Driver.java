package com.progressiveReader;

import java.io.IOException;

import com.progressiveReader.data.IO;
import com.progressiveReader.data.User;
import com.progressiveReader.view.View;

public class Driver {
	
	private IO io;
	private View view;
	private LocationController locationController;
	private ProgressiveController progressiveController;
	private UserController userController;
	private User user;
	
	public static void main(String[] args) throws IOException {
		Driver driver = new Driver();
		driver.user = new User();
		driver.view = new View();
		driver.io = new IO(driver);
		driver.locationController = new LocationController(driver);
		driver.progressiveController = new ProgressiveController(driver);
		driver.userController = new UserController(driver);
		driver.progressiveController.setButtonList(driver.view.getProgressivePage().getKeyList());
	}

	public IO getIo() {
		return io;
	}

	public View getView() {
		return view;
	}

	public LocationController getLocationController() {
		return locationController;
	}

	public ProgressiveController getProgressiveController() {
		return progressiveController;
	}

	public UserController getUserController() {
		return userController;
	}
	
	public User getUser() {
		return user;
	}
}
