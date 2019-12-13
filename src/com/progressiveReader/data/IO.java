package com.progressiveReader.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IO {
	
	private Scanner sc;
	private List<List<String>> masterLists = new ArrayList<>(); 
	
	public void readMasterCSV(String location) {
		masterLists.clear();
		List<String> dataList = new ArrayList<>();
		try {
			sc = new Scanner(new File(location));
			sc.useDelimiter(",");
			while(sc.hasNext()) {
				String data = sc.nextLine();
				dataList = Arrays.asList(data.split(","));
				masterLists.add(dataList);
			}
			masterLists.remove(0);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<List<String>> getMasterLists() {
		return masterLists;
	}

}
