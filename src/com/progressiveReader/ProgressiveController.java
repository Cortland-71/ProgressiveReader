package com.progressiveReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.progressiveReader.data.IO;
import com.progressiveReader.view.View;

public class ProgressiveController implements ActionListener {

	private Driver driver;
	private List<JButton> buttonList = new ArrayList<>();
	private List<String> inputs = new ArrayList<>();
	private List<String> overrides = new ArrayList<>();
	private String numberText = "";
	public static int dataIndex = 0;
	
	public ProgressiveController(Driver driver) {
		this.driver = driver;
		this.driver.getView().getProgressivePage().setKeyPadListener(this);
	}

	public void setButtonList(List<JButton> buttons) {
		this.buttonList = buttons;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			getNumberPadEvents(e);
			getSubmitButtonEvent(e);
			getOverrideButtonEvent(e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//KeyPad Event \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private void getNumberPadEvents(ActionEvent e) {
		if (e.getSource() == buttonList.get(9)) {
			if (numberText.length() > 0) numberText = getStringWithDeletedItem();
		} else if (e.getSource() == buttonList.get(11)) {
			if (!numberText.contains(".")) numberText += "."; 
		} else {
			for (JButton button : buttonList) {
				if (e.getSource() == button) {
					if (numberHasTwoDecimalSpots()) return; 
					numberText += button.getText();
				} 
			}
		}
		driver.getView().getProgressivePage().setProgressiveFieldText(numberText);
	}
	
	private String getStringWithDeletedItem() {
		List<String> letterList = new ArrayList<>(Arrays.asList(numberText.split("")));
		letterList.remove(letterList.size()-1);
		return String.join("", letterList);
	}
	
	private boolean numberHasTwoDecimalSpots() {
		if (numberText.contains(".")) {
			String sub = numberText.substring(numberText.indexOf("."));
			if (sub.length() > 2) return true;
		}
		return false;
	}
	
	//Submit Button event \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private void getSubmitButtonEvent(ActionEvent e) throws IOException {
		if (e.getSource() == driver.getView().getProgressivePage().getSubmitButton()) {
			if (invalidAmount()) return;
			addEntry("F");
			if (stillEnteringProgressives()) return;
			finish();
		}
	}
	
	private boolean invalidAmount() {
		
		if (driver.getView().getProgressivePage().getProgressiveFieldText().trim().equals("")) return true;
		
		double input = Double.parseDouble(driver.getView().getProgressivePage().getProgressiveFieldText());
		double resetAmount = Double.parseDouble(driver.getIo().getMasterLists().get(dataIndex).get(2));
		double maxAmount = Double.parseDouble(driver.getIo().getMasterLists().get(dataIndex).get(3));
		
		if (input < resetAmount || input > maxAmount) {
			JOptionPane.showMessageDialog(null, "Entry might be incorrect. Please check and try again. "
					+ "If you are sure the entry is correct. You can Override the entry.");
			return true;
		}
		return false;
	}
	
	//Override Button event \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private void getOverrideButtonEvent(ActionEvent e) throws IOException {
		if (e.getSource() == driver.getView().getProgressivePage().getOverrideButton()) {
			if (driver.getView().getProgressivePage().getProgressiveFieldText().trim().equals("")) return;
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to override this entry?", 
					"OVERRIDE", JOptionPane.CANCEL_OPTION);
			if (reply == 0) {
				addEntry("T");
				if (stillEnteringProgressives()) return;
				finish();
			}
		}
	}
	
	//Helper methods
	
	private void addEntry(String overrideState) {
		dataIndex++;
		inputs.add(driver.getView().getProgressivePage().getProgressiveFieldText());
		overrides.add(overrideState);
		clearProgressiveField();
	}
	
	private void clearProgressiveField() {
		numberText = "";
		driver.getView().getProgressivePage().setProgressiveFieldText(numberText);
	}
	
	private boolean stillEnteringProgressives() {
		if (dataIndex < driver.getIo().getMasterLists().size()) {
			driver.getView().getProgressivePage().setMachineNameLabel(driver.getIo().getMasterLists().get(dataIndex).get(1));
			driver.getView().getProgressivePage().setMachineNumberLabel(driver.getIo().getMasterLists().get(dataIndex).get(0));
			return true;
		}
		return false;
	}
	
	private void finish() throws IOException {
		JOptionPane.showMessageDialog(null, "You are done :)");
		driver.getIo().writeFinanceOutput();
		System.exit(0);
	}
	
	public List<String> getInputs() {
		return inputs;
	}
	
	public List<String> getOverrides() {
		return overrides;
	}
}
