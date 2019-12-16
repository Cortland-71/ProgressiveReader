package com.progressiveReader.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import com.progressiveReader.Driver;

public class IO {
	
	private Scanner sc;
	private Driver driver;
	private Writer w;
	private List<List<String>> masterLists = new ArrayList<>(); 
	private List<String> inputs = new ArrayList<>();
	private List<String> overrides = new ArrayList<>();
	private final String outputPath = "\\\\yganas01\\YDrive\\Finance\\Shared\\Daily Casino Progressives\\"
			+ "Dailey Progressives\\Yavapai Progressives\\YavapaiFinanceNew.csv";
	private final List<String> headerInfo = Arrays.asList("Machine Number","Machine Name","Progressive Amount",
			"Reset Amount","Max Amount"," Override");
	
	
	public IO(Driver driver) {
		this.driver = driver;
	}
	
	public void readMasterCSV(String location) {
		masterLists.clear();

		try {
			sc = new Scanner(new File(location));
			sc.useDelimiter(",");
			while(sc.hasNext()) {
				String data = sc.nextLine();
				List<String> dataList = new ArrayList<>(Arrays.asList(data.split(",")));
				masterLists.add(dataList);
			}
			masterLists.remove(0);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void generateFinalLists() {	
		for (int i=0; i<masterLists.size(); i++) {
			masterLists.get(i).add(2, inputs.get(i));
			masterLists.get(i).add(overrides.get(i));
		}
	}
	
	public void writeFinanceOutput() throws IOException {
		inputs = driver.getProgressiveController().getInputs();
		overrides = driver.getProgressiveController().getOverrides();
		w = new FileWriter(outputPath);
		generateFinalLists();
		writeSingleRow(w, headerInfo);
		writeOutData(w);
		writeSingleRow(w, driver.getUser().getUserData());
		w.flush();
        w.close();
	}
	
	private void writeOutData(Writer w) throws IOException {
		for (List<String> list : masterLists) {
			for (String i : list) {
				w.append(i);
				w.append(",");
			}
			w.append("\n");
		}
	}
	
	private void writeSingleRow(Writer w, List<String> list) throws IOException {
		for (String i : list) {
			w.append(i);
			w.append(",");
		}
		w.append("\n");
	}
	
	public List<List<String>> getMasterLists() {
		return masterLists;
	}

}
