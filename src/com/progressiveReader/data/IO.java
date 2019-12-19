package com.progressiveReader.data;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.progressiveReader.Driver;

public class IO {
	
	private Scanner sc;
	private Driver driver;
	private Writer w;
	private List<List<String>> masterLists = new ArrayList<>(); 
	private List<String> inputs = new ArrayList<>();
	private List<String> overrides = new ArrayList<>();
	private String rawOutputPath;
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
	
	public void writeFinanceOutput(String location) throws IOException {
		inputs = driver.getProgressiveController().getInputs();
		overrides = driver.getProgressiveController().getOverrides();
		rawOutputPath = "\\\\yganas01\\YDrive\\Finance\\Shared\\Daily Casino Progressives\\"
				+ "Dailey Progressives\\" + location + " Progressives\\" + location + "Finance.csv";
		w = new FileWriter(rawOutputPath);
		generateFinalLists();
		writeSingleRow(w, headerInfo);
		writeOutData(w);
		writeSingleRow(w, driver.getUser().getUserData());
		w.flush();
        w.close();
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
		
		String date = DateTimeFormatter.ofPattern("yyyy-MM-dd (hhmm a)").format(LocalDateTime.now());
		File dest = new File("\\\\yganas01\\YDrive\\Finance\\Shared\\Daily Casino Progressives\\"
				+ "Progressive Backups\\"+location+" Backups\\"+location+date+".csv");
		try {
			Files.copy(source.toPath(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<List<String>> getMasterLists() {
		return masterLists;
	}

}
