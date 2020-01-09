package com.progressiveReader.data;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

import com.progressiveReader.Driver;

public class IO {
	
	private Scanner sc;
	private Driver driver;
	private Writer w;
	private List<List<String>> masterLists = new ArrayList<>(); 
	private List<List<String>> previousLists = new ArrayList<>();
	private List<String> inputs = new ArrayList<>();
	private List<String> overrides = new ArrayList<>();
	private String rawOutputPath;
	private String previousRawOutputPath;
	private LocalDate date;
	private final List<String> headerInfo = Arrays.asList("Machine Number","Machine Name","Progressive Amount",
			"Reset Amount","Max Amount"," Override", "Previous");
	
	
	public IO(Driver driver) {
		this.driver = driver;
	}
	
	public void readPreviousCSV(String location) {
		previousLists.clear();
		try {
			sc = new Scanner(new File(location));
			sc.useDelimiter(",");
			while(sc.hasNext()) {
				String data = sc.nextLine();
				List<String> dataList = new ArrayList<>(Arrays.asList(data.split(",")));
				previousLists.add(dataList);
			}
			previousLists.remove(0);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
	
	public void writeFinanceOutput(String location) throws IOException {
		date = LocalDate.now();
		inputs = driver.getProgressiveController().getInputs();
		overrides = driver.getProgressiveController().getOverrides();
		rawOutputPath = "\\\\yganas01\\YDrive\\Finance\\Shared\\Daily Casino Progressives\\"
				+ "Dailey Progressives\\" + location + " Progressives\\" + location + "Finance.csv";
		
		previousRawOutputPath = "\\\\yganas01\\YDrive\\Finance\\Shared\\Daily Casino Progressives\\"
				+ "Progressive Backups\\" + location + " Backups\\" + location + date.minusDays(1) +".csv";
		
		
		w = new FileWriter(rawOutputPath);
		generateFinalLists();
		writeSingleRow(w, headerInfo);
		writeOutData(w);
		writeSingleRow(w, driver.getUser().getUserData());
		w.flush();
        w.close();
	}
	
	private void generateFinalLists() {	
		for (int i=0; i<masterLists.size(); i++) {
			masterLists.get(i).add(2, inputs.get(i));
			masterLists.get(i).add(overrides.get(i));
		}
		searchAndAddFromPrevious();
	}
	
	private void searchAndAddFromPrevious() {
		readPreviousCSV(previousRawOutputPath);
		for (List<String> line : masterLists) {
			for (int i=0; i<previousLists.size(); i++) {
				if (line.get(0).equals(previousLists.get(i).get(0))) {
					line.add(previousLists.get(i).get(2));
					previousLists.remove(i);
					break;
				}
				if (i == previousLists.size()-1) line.add("na");
			}
		}
	}
	
	private void writeOutData(Writer w) throws IOException {
		for (List<String> list : masterLists) {
			writeSingleRow(w, list);
		}
	}
	
	private void writeSingleRow(Writer w, List<String> list) throws IOException {
		for (String i : list) {
			w.append(i);
			w.append(",");
		}
		w.append("\n");
	}
	
	public void copyCSV(String location) {
				
		File source = new File(rawOutputPath);
		File dest = new File("\\\\yganas01\\YDrive\\Finance\\Shared\\Daily Casino Progressives\\"
				+ "Progressive Backups\\"+location+" Backups\\"+location+date+".csv");
		
		if(dest.exists()) dest.delete();
		
		try {
			Files.copy(source.toPath(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<List<String>> getMasterLists() {
		return masterLists;
	}
	
	public List<String> getInputList() {
		return inputs;
	}
	
	public List<List<String>> getPreviousList() {
		return previousLists;
	}

}
