package com.progressiveReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.progressiveReader.data.IO;
import com.progressiveReader.view.View;

public class LocationController implements ActionListener {
	
	private View view;
	private IO io;
	private final String YC_MASTER_PATH = "\\\\yganas01\\YDrive\\Drop\\Drop Crew Progressive\\YavapaiMaster-Dummy.csv";
	private final String BC_MASTER_PATH = "\\\\yganas01\\YDrive\\Drop\\Drop Crew Progressive\\BuckysMaster.csv";
	private String locationText;
	
	public LocationController(View view, IO io) {
		this.view = view;
		this.view.getLocationPage().setLocationActionListener(this);
		this.io = io;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String locationPath = null;
		String masterPath = null;
		if (e.getSource() == view.getLocationPage().getBuckysButton()) {
			locationPath = "/BCLogo2.png";
			masterPath = BC_MASTER_PATH;
			locationText = "Buckys";
		}
		else {
			locationPath = "/YCLogo2.png";
			masterPath = YC_MASTER_PATH;
			locationText = "Yavapai";
		}
		
		view.getUserPage().setSelectedLocationLabel(locationPath);
		view.getUserPage().setDateLabel(LocalDate.now().toString());
		view.getUserPage().setTimeLabel(DateTimeFormatter.ofPattern("kk:mm").format(LocalTime.now()));
		view.getUserPage().setLocationLabel(locationText);
		io.readMasterCSV(masterPath);
		io.getMasterLists().forEach(System.out::println);
		view.getProgressivePage().setMachineNameLabel(io.getMasterLists().get(0).get(1));
		view.getProgressivePage().setMachineNumberLabel(io.getMasterLists().get(0).get(0));
		View.cl.show(view.getRootPanel(), "2");
		
	}

}
