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
	private String numberText = "";
	private int dataIndex = 0;
	
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
			
			dataIndex++;
			inputs.add(view.getProgressivePage().getProgressiveFieldText());
			clearProgressiveField();
			if (dataIndex < io.getMasterLists().size()) {
				view.getProgressivePage().setMachineNameLabel(io.getMasterLists().get(dataIndex).get(1));
				view.getProgressivePage().setMachineNumberLabel(io.getMasterLists().get(dataIndex).get(0));
				return;
			}
			JOptionPane.showMessageDialog(null, "You are done :)");
			System.exit(0);
		}
	}
	
	private void clearProgressiveField() {
		numberText = "";
		view.getProgressivePage().setProgressiveFieldText(numberText);
	}
}
