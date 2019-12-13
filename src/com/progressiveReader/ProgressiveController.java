package com.progressiveReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.progressiveReader.data.IO;
import com.progressiveReader.view.View;

public class ProgressiveController implements ActionListener {
	
	private View view;
	private IO io;
	private List<JButton> buttonList = new ArrayList<>();
	private List<String> inputs = new ArrayList<>();
	private List<String> overrides = new ArrayList<>();
	private String numberText = "";
	public static int dataIndex = 0;
	
	public ProgressiveController(View view, IO io) {
		this.view = view;
		this.io = io;
		this.view.getProgressivePage().setKeyPadListener(this);
	}

	public void setButtonList(List<JButton> buttons) {
		this.buttonList = buttons;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		getNumberPadEvents(e);
		getSubmitButtonEvent(e);
		getOverrideButtonEvent(e);
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
		view.getProgressivePage().setProgressiveFieldText(numberText);
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
	private void getSubmitButtonEvent(ActionEvent e) {
		if (e.getSource() == view.getProgressivePage().getSubmitButton()) {
			if (invalidAmount()) return;
			addEntry("F");
			if (stillEnteringProgressives()) return;
			finish();
		}
	}
	
	private boolean invalidAmount() {
		
		if (view.getProgressivePage().getProgressiveFieldText().trim().equals("")) return true;
		
		double input = Double.parseDouble(view.getProgressivePage().getProgressiveFieldText());
		double resetAmount = Double.parseDouble(io.getMasterLists().get(dataIndex).get(2));
		double maxAmount = Double.parseDouble(io.getMasterLists().get(dataIndex).get(3));
		
		if (input < resetAmount || input > maxAmount) {
			JOptionPane.showMessageDialog(null, "Entry might be incorrect. Please check and try again. "
					+ "If you are sure the entry is correct. You can Override the entry.");
			return true;
		}
		return false;
	}
	
	//Override Button event \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private void getOverrideButtonEvent(ActionEvent e) {
		if (e.getSource() == view.getProgressivePage().getOverrideButton()) {
			if (view.getProgressivePage().getProgressiveFieldText().trim().equals("")) return;
			addEntry("T");
			if (stillEnteringProgressives()) return;
			finish();
		}
	}
	
	//Helper methods
	
	private void addEntry(String overrideState) {
		dataIndex++;
		inputs.add(view.getProgressivePage().getProgressiveFieldText());
		overrides.add(overrideState);
		clearProgressiveField();
	}
	
	private void clearProgressiveField() {
		numberText = "";
		view.getProgressivePage().setProgressiveFieldText(numberText);
	}
	
	private boolean stillEnteringProgressives() {
		if (dataIndex < io.getMasterLists().size()) {
			view.getProgressivePage().setMachineNameLabel(io.getMasterLists().get(dataIndex).get(1));
			view.getProgressivePage().setMachineNumberLabel(io.getMasterLists().get(dataIndex).get(0));
			return true;
		}
		return false;
	}
	
	private void finish() {
		JOptionPane.showMessageDialog(null, "You are done :)");
		for (int i=0;i<io.getMasterLists().size(); i++)
			System.out.println("Entry: " + inputs.get(i) + " " + io.getMasterLists().get(i));
		overrides.forEach(System.out::print);
		System.exit(0);
	}
}
