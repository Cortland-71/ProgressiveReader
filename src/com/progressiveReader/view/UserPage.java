package com.progressiveReader.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPage {

	JPanel getUserPanel() throws IOException {
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BorderLayout());
		userPanel.setBackground(View.DEFAULT_BG);
		userPanel.add(northPanel(), BorderLayout.NORTH);
		userPanel.add(centerPanel(), BorderLayout.CENTER);
		userPanel.add(southPanel(), BorderLayout.SOUTH);
		return userPanel;
	}
	
	//North Panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel northPanel() throws IOException {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridBagLayout());
		northPanel.setBackground(View.DEFAULT_BG);
		northPanel.add(selectedLocationLabel(), View.c);
		return northPanel;
	}
	
	private JLabel selectedLocationLabel;
	private JLabel selectedLocationLabel() throws IOException {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.insets = new Insets(50,0,0,0);
		selectedLocationLabel = new JLabel();
		return selectedLocationLabel;
	}
	
	public void setSelectedLocationLabel(String path) {
		Image selectedLabel = null;
		try {
			selectedLabel = ImageIO.read(getClass().getResource(path));
			selectedLocationLabel.setIcon(new ImageIcon(selectedLabel));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Center Panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel centerPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(View.DEFAULT_BG);
		centerPanel.add(centerInCenterPanel(), BorderLayout.CENTER);
		return centerPanel;
	}
	
	
	//Center Panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel centerInCenterPanel() {
		JPanel centerInCenterPanel = new JPanel();
		centerInCenterPanel.setLayout(new GridBagLayout());
		centerInCenterPanel.setBackground(View.DEFAULT_BG);
		centerInCenterPanel.add(nameLabel(), View.c);
		centerInCenterPanel.add(nameField(), View.c);
		centerInCenterPanel.add(dateLabel(), View.c);
		centerInCenterPanel.add(timeLabel(), View.c);
		centerInCenterPanel.add(locationLabel(), View.c);
		return centerInCenterPanel;
	}
	private JLabel nameLabel;
	private JLabel nameLabel() {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.anchor = GridBagConstraints.WEST;
		View.c.insets = new Insets(0,0,5,0);
		nameLabel = new JLabel("Full name");
		View.setFont(nameLabel, "Arial", 0, 15, Color.LIGHT_GRAY);
		return nameLabel;
	}
	
	private JTextField nameField;
	private JTextField nameField() {
		View.c.gridx = 0;
		View.c.gridy = 1;
		View.c.insets = new Insets(0,0,0,0);
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(500,40));
		nameField.setHorizontalAlignment(JTextField.CENTER);
		View.setFont(nameField, "Arial", 1, 30, Color.BLACK);
		return nameField;
	}
	
	public String getNameFieldText() {
		return nameField.getText();
	}
	
	public void clearNameField() {
		nameField.setText("");
	}
	
	private JLabel dateLabel;
	private JLabel dateLabel() {
		View.c.gridx = 0;
		View.c.gridy = 2;
		View.c.insets = new Insets(5,0,0,0);
		View.c.anchor = GridBagConstraints.WEST;
		dateLabel = new JLabel("Date: " + LocalDate.now().toString());
		View.setFont(dateLabel, "Arial", 0, 12, Color.LIGHT_GRAY);
		return dateLabel;
	}
	
	public void setDateLabel(String date) {
		dateLabel.setText(date);
	}
	
	public String getDateLabelText() {
		return dateLabel.getText();
	}
	
	private JLabel timeLabel;
	private JLabel timeLabel() {
		View.c.gridx = 0;
		View.c.gridy = 3;
		View.c.insets = new Insets(5,0,0,0);
		View.c.anchor = GridBagConstraints.WEST;
		timeLabel = new JLabel();
		View.setFont(timeLabel, "Arial", 0, 12, Color.LIGHT_GRAY);
		return timeLabel;
	}
	
	public void setTimeLabel(String time) {
		timeLabel.setText(time);
	}

	public String getTimeLabelText() {
		return timeLabel.getText();
	}
	
	private JLabel locationLabel;
	private JLabel locationLabel() {
		View.c.gridx = 0;
		View.c.gridy = 4;
		View.c.insets = new Insets(5,0,150,0);
		View.c.anchor = GridBagConstraints.WEST;
		locationLabel = new JLabel();
		View.setFont(locationLabel, "Arial", 0, 12, Color.LIGHT_GRAY);
		return locationLabel;
	}
	
	public void setLocationLabel(String location) {
		locationLabel.setText(location);
	}

	public String getLocationLabelText() {
		return locationLabel.getText();
	}
	
	
	
	//South Panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel southPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());
		southPanel.setBackground(View.DEFAULT_BG);
		southPanel.add(backButton(), View.c);
		southPanel.add(submitButton(), View.c);
		return southPanel;
	}
	
	private JButton backButton;
	private JButton backButton() {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.insets = new Insets(0,0,100,250);
		backButton = new JButton("BACK");
		backButton.setPreferredSize(new Dimension(250, 100));
		backButton.setBackground(new Color(230,115,0));
		backButton.setFocusable(false);
		View.setFont(backButton, "Arial", 1, 20, Color.WHITE);
		return backButton;
	}
	
	private JButton submitButton;
	private JButton submitButton() {
		View.c.gridx = 1;
		View.c.gridy = 0;
		View.c.insets = new Insets(0,250,100,0);
		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(250, 100));
		submitButton.setBackground(View.DEFAULT_SUBMIT_COLOR);
		submitButton.setFocusable(false);
		View.setFont(submitButton, "Arial", 1, 20, Color.WHITE);
		return submitButton;
	}
	
	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButtonListener(ActionListener l) {
		backButton.addActionListener(l);
		submitButton.addActionListener(l);
	}
}
