package com.progressiveReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.progressiveReader.data.IO;
import com.progressiveReader.view.View;

public class LocationController implements ActionListener {
	
	private Driver driver;

	private final String YC_MASTER_PATH = "\\\\yganas01\\YDrive\\Drop\\Drop Crew Progressive\\YavapaiMaster-Dummy.csv";
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
		driver.getView().getUserPage().setDateLabel(LocalDate.now().toString());
		driver.getView().getUserPage().setTimeLabel(DateTimeFormatter.ofPattern("kk:mm").format(LocalTime.now()));
		driver.getView().getUserPage().setLocationLabel(locationText);
		driver.getIo().readMasterCSV(masterPath);
		driver.getView().getProgressivePage().setMachineNameLabel(driver.getIo().getMasterLists().get(0).get(1));
		driver.getView().getProgressivePage().setMachineNumberLabel(driver.getIo().getMasterLists().get(0).get(0));
		View.cl.show(driver.getView().getRootPanel(), "2");
		
	}

}
