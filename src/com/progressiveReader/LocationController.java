package com.progressiveReader;

import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import com.progressiveReader.view.View;


public class LocationController implements ActionListener {
	
	private Driver driver;

	private final String YC_MASTER_PATH = "\\\\yganas01\\YDrive\\Drop\\Drop Crew Progressive\\YavapaiMaster.csv";
	private final String BC_MASTER_PATH = "\\\\yganas01\\YDrive\\Drop\\Drop Crew Progressive\\BuckysMaster.csv";
	private String locationText;
	
	public LocationController(Driver driver) {
		this.driver = driver;
		this.driver.getView().getLocationPage().setLocationActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String locationPath = null;
		String masterPath = null;
		if (e.getSource() == driver.getView().getLocationPage().getBuckysButton()) {
			locationPath = "/BCLogo2.png";
			masterPath = BC_MASTER_PATH;
			locationText = "Buckys";
		}
		else {
			locationPath = "/YCLogo2.png";
			masterPath = YC_MASTER_PATH;
			locationText = "Yavapai";
		}
		
		driver.getView().getUserPage().setSelectedLocationLabel(locationPath);
		
		driver.getUserController().setDate(LocalDate.now().toString());
		driver.getUserController().setTime(DateTimeFormatter.ofPattern("kk:mm").format(LocalTime.now()));
		driver.getUserController().setLocation(locationText);
		driver.getIo().readMasterCSV(masterPath);
		driver.getView().getProgressivePage().setMachineNameLabel(driver.getIo().getMasterLists().get(0).get(1));
		driver.getView().getProgressivePage().setMachineNumberLabel(driver.getIo().getMasterLists().get(0).get(0));
		driver.getView().getProgressivePage().setCountLabel("1/"+driver.getIo().getMasterLists().size());
		View.cl.show(driver.getView().getRootPanel(), "2");	
	}
}
