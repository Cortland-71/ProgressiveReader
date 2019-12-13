package com.progressiveReader;

import java.io.IOException;

import com.progressiveReader.data.IO;
import com.progressiveReader.view.View;

public class Driver {
	public static void main(String[] args) throws IOException {
		IO io = new IO();
		View view = new View();
		LocationController locationController = new LocationController(view, io);
		ProgressiveController progressiveController = new ProgressiveController(view, io);
		UserController userController = new UserController(view);
		progressiveController.setButtonList(view.getProgressivePage().getKeyList());
	}
}
