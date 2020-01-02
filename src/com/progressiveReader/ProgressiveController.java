package com.progressiveReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ProgressiveController implements ActionListener {

	private Driver driver;
	private List<JButton> buttonList = new ArrayList<>();
	private List<String> inputs = new ArrayList<>();
	private List<String> overrides = new ArrayList<>();
	private StringBuilder numberText = new StringBuilder();
	private final int BACK_SPACE = 9;
	private final int PERIOD = 11;
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
			getBackButtonEvent(e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//KeyPad Event \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private void getNumberPadEvents(ActionEvent e) {
		if (e.getSource() == buttonList.get(BACK_SPACE)) {
			if (numberText.length() > 0) deleteLastNumber();
		} else if (e.getSource() == buttonList.get(PERIOD)) {
			if (!numberText.toString().contains(".")) numberText.append("."); 
		} else {
			for (JButton button : buttonList) {
				if (e.getSource() == button) {
					if (numberHasTwoDecimalSpots()) return; 
					numberText.append(button.getText());
				} 
			}
		}
		driver.getView().getProgressivePage().setProgressiveFieldText(numberText.toString());
	}
	
	private void deleteLastNumber() {
		numberText.deleteCharAt(numberText.length()-1);
	}
	
	private boolean numberHasTwoDecimalSpots() {
		if (numberText.toString().contains(".")) {
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
	
	//Back Button event \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private void getBackButtonEvent(ActionEvent e) {
		if (e.getSource() == driver.getView().getProgressivePage().getBackButton()) {
			System.out.println(dataIndex);
			if (dataIndex > 0) {
				dataIndex-=1;
				updateMachineNameAndNumber();
				inputs.remove(dataIndex);
			}
		}
	}
	
	//Helper methods
	
	private void addEntry(String overrideState) {
		dataIndex++;
		inputs.add(driver.getView().getProgressivePage().getProgressiveFieldText());
		overrides.add(overrideState);
		
		inputs.forEach(System.out::println);
		System.out.println();
	}
	
	private boolean stillEnteringProgressives() {
		if (dataIndex < driver.getIo().getMasterLists().size()) {
			updateMachineNameAndNumber();
			clearProgressiveField();
			return true;
		}
		return false;
	}
	
	private void updateMachineNameAndNumber() {
		driver.getView().getProgressivePage().setMachineNameLabel(driver.getIo().getMasterLists().get(dataIndex).get(1));
		driver.getView().getProgressivePage().setMachineNumberLabel(driver.getIo().getMasterLists().get(dataIndex).get(0));
		driver.getView().getProgressivePage().setCountLabel(dataIndex + 1 + "/" + driver.getIo().getMasterLists().size());
	}
	
	private void clearProgressiveField() {
		numberText.delete(0, numberText.length());
		driver.getView().getProgressivePage().setProgressiveFieldText(numberText.toString());
	}
	
	private void finish() throws IOException {
		JOptionPane.showMessageDialog(null, "You are done :)");
		driver.getIo().writeFinanceOutput(driver.getUser().getUserData().get(1));
		driver.getIo().copyCSV(driver.getUser().getUserData().get(1));
		System.exit(0);
	}
	
	public List<String> getInputs() {
		return inputs;
	}
	
	public List<String> getOverrides() {
		return overrides;
	}
}
