package com.progressiveReader.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProgressivePage {
	
	private final int EAST_WEST_WIDTH = 100;
	
	JPanel getProgressivePanel() throws IOException {
		JPanel progressivePanel = new JPanel();
		progressivePanel.setLayout(new BorderLayout());
		progressivePanel.setBackground(View.DEFAULT_BG);
		progressivePanel.add(northPanel(), BorderLayout.NORTH);
		progressivePanel.add(centerPanel(), BorderLayout.CENTER);
		progressivePanel.add(southPanel(), BorderLayout.SOUTH);
		return progressivePanel;
	}
	
	//North panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel northPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.setBackground(new Color(194,194,194));
		northPanel.setBorder(BorderFactory.createEtchedBorder());
		northPanel.add(northInNorthPanel(), BorderLayout.NORTH);
		northPanel.add(centerInNorthPanel(), BorderLayout.CENTER);
		return northPanel;
	}
	
	private JPanel northInNorthPanel() {
		JPanel northInNorthPanel = new JPanel();
		northInNorthPanel.setLayout(new GridBagLayout());
		northInNorthPanel.setBackground(new Color(194,194,194));
		northInNorthPanel.add(countLabel(), View.c);
		return northInNorthPanel;
	}
	
	private JLabel countLabel;
	private JLabel countLabel() {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.insets = new Insets(5,0,0,0);
		View.c.anchor = GridBagConstraints.WEST;
		countLabel = new JLabel();
		View.setFont(countLabel, "Consolas", 0, 12, Color.DARK_GRAY);
		return countLabel;
	}
	public void setCountLabel(String text) {
		countLabel.setText(text);
	}
	
	private JPanel centerInNorthPanel() {
		JPanel centerInNorthPanel = new JPanel();
		centerInNorthPanel.setLayout(new GridBagLayout());
		centerInNorthPanel.setBackground(new Color(194,194,194));
		centerInNorthPanel.add(machineNameLabel(), View.c);
		centerInNorthPanel.add(machineNumberLabel(), View.c);
		return centerInNorthPanel;
	}
	
	private JLabel machineNameLabel;
	private JLabel machineNameLabel() {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.insets = new Insets(5,0,0,0);
		View.c.anchor = GridBagConstraints.CENTER;
		machineNameLabel = new JLabel("Machine Name");
		View.setFont(machineNameLabel, "Arial", 1, 35, Color.BLACK);
		return machineNameLabel;
	}
	public void setMachineNameLabel(String name) {
		machineNameLabel.setText(name);
	}
	
	private JLabel machineNumberLabel;
	private JLabel machineNumberLabel() {
		View.c.gridx = 0;
		View.c.gridy = 1;
		View.c.insets = new Insets(0,0,20,0);
		machineNumberLabel = new JLabel("Machine Number");
		View.setFont(machineNumberLabel, "Arial", 1, 50, Color.BLACK);
		return machineNumberLabel;
	}
	public void setMachineNumberLabel(String number) {
		machineNumberLabel.setText(number);
	}
	
	//Center panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel centerPanel;
	private JPanel centerPanel() {
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(View.DEFAULT_BG);
		centerPanel.add(northInCenterPanel(), BorderLayout.NORTH);
		centerPanel.add(eastInCenterPanel(), BorderLayout.EAST);
		centerPanel.add(centerInCenterPanel(), BorderLayout.CENTER);
		centerPanel.add(westInCenterPanel(), BorderLayout.WEST);
		return centerPanel;
	}
	
	//North
	private JPanel northInCenterPanel() {
		JPanel northInCenterPanel = new JPanel();
		northInCenterPanel.setBackground(View.DEFAULT_BG);
		northInCenterPanel.add(progressiveField());
		return northInCenterPanel;
	}
	
	private JTextField progressiveField;
	private JTextField progressiveField() {
		progressiveField = new JTextField();
		progressiveField.setPreferredSize(new Dimension(850,80));
		progressiveField.setHorizontalAlignment(JTextField.CENTER);
		View.setFont(progressiveField, "Arial", 1, 72, Color.BLACK);
		return progressiveField;
	}
	public void setProgressiveFieldText(String text) {
		progressiveField.setText(text);
	}
	public String getProgressiveFieldText() {
		return progressiveField.getText();
	}
	
	//East
	private JPanel eastInCenterPanel() {
		JPanel eastInCenterPanel = new JPanel();
		eastInCenterPanel.setBackground(View.DEFAULT_BG);
		eastInCenterPanel.setPreferredSize(new Dimension(EAST_WEST_WIDTH,0));
		return eastInCenterPanel;
	}
	
	//Center
	private JPanel centerInCenterPanel;
	private JPanel centerInCenterPanel() {
		centerInCenterPanel = new JPanel();
		centerInCenterPanel.setBackground(View.DEFAULT_BG);
		centerInCenterPanel.setLayout(new GridLayout(4,3));
		populateKeyPad();
		return centerInCenterPanel;
	}
	
	private List<String> keyPadNumbers = Arrays.asList("7","8","9","4","5","6","1","2","3","<","0",".");
	private List<JButton> keyButtons = new ArrayList<>();
	private void populateKeyPad() {
		for (String key : keyPadNumbers) {
			JButton keyButton = new JButton(key);
			keyButtons.add(keyButton);
			keyButton.setFocusable(false);
			keyButton.setBorder(BorderFactory.createLineBorder(View.DEFAULT_BG, 3));
			View.setFont(keyButton, "Articulate", 1, 50, Color.BLACK);
			centerInCenterPanel.add(keyButton);
		}
	}
	
	public List<JButton> getKeyList() {
		return keyButtons;
	}
			
	//South panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel southPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());
		southPanel.setBackground(View.DEFAULT_BG);
		southPanel.add(overrideButton(), View.c);
		southPanel.add(submitButton(), View.c);
		return southPanel;
	}
	
	private JButton overrideButton;
	private JButton overrideButton() {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.insets = new Insets(20,0,20,250);
		overrideButton = new JButton("OVERRIDE");
		overrideButton.setFocusable(false);
		overrideButton.setPreferredSize(new Dimension(200,50));
		overrideButton.setBackground(new Color(222,0,0));
		View.setFont(overrideButton, "Arial", 1, 15, Color.WHITE);
		return overrideButton;
	}
	
	public JButton getOverrideButton() {
		return overrideButton;
	}
	
	private JButton submitButton;
	private JButton submitButton() {
		View.c.gridx = 1;
		View.c.gridy = 0;
		View.c.insets = new Insets(20,250,20,0);
		submitButton = new JButton("SUBMIT");
		submitButton.setFocusable(false);
		submitButton.setPreferredSize(new Dimension(200,50));
		submitButton.setBackground(View.DEFAULT_SUBMIT_COLOR);
		View.setFont(submitButton, "Arial", 1, 15, Color.WHITE);
		return submitButton;
	}
	
	public JButton getSubmitButton() {
		return submitButton;
	}
	
	//West
	private JPanel westInCenterPanel() {
		JPanel westInCenterPanel = new JPanel();
		westInCenterPanel.setBackground(View.DEFAULT_BG);
		westInCenterPanel.setPreferredSize(new Dimension(EAST_WEST_WIDTH,0));
		return westInCenterPanel;
	}
	
	
	public void setKeyPadListener(ActionListener l) {
		for (JButton key : keyButtons)
			key.addActionListener(l);
		submitButton.addActionListener(l);
		overrideButton.addActionListener(l);
	}

}
