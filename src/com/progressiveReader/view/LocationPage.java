package com.progressiveReader.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LocationPage {
	
	private final int BTN_WIDTH = 450;
	private final int BTN_HEIGHT = 350; 
	private final int BTN_EDGE = 100;
	
	JPanel getLocationPanel() throws IOException {
		JPanel locationPanel = new JPanel();
		locationPanel.setLayout(new BorderLayout());
		locationPanel.setBackground(View.DEFAULT_BG);
		locationPanel.add(northPanel(), BorderLayout.NORTH);
		locationPanel.add(westPanel(), BorderLayout.WEST);
		locationPanel.add(eastPanel(), BorderLayout.EAST);
		return locationPanel;
	}
	//North Panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel northPanel() throws IOException {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridBagLayout());
		northPanel.setBackground(new Color(43,82,117));
		northPanel.setBorder(BorderFactory.createEtchedBorder(0));
		northPanel.add(locationLabel(), View.c);
		return northPanel;
	}
	
	private JLabel locationLabel() {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.insets = new Insets(40,0,40,0);
		JLabel locationLabel = new JLabel("Select Location");
		View.setFont(locationLabel, "Arial", 1, 40, Color.WHITE);
		return locationLabel;
	}
	
	
	//West Panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel westPanel() throws IOException {
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridBagLayout());
		westPanel.setBackground(View.DEFAULT_BG);
		westPanel.add(buckysButton(), View.c);
		return westPanel;
	}
	
	private JButton buckysButton;
	private JButton buckysButton() throws IOException {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.insets = new Insets(0,BTN_EDGE,0,0);
		buckysButton = new JButton();
		Image bcImage = ImageIO.read(getClass().getResource("/BCLogo2.png"));
		buckysButton.setIcon(new ImageIcon(bcImage));
		buckysButton.setPreferredSize(new Dimension(BTN_WIDTH,BTN_HEIGHT));
		buckysButton.setBackground(View.BTN_COLOR);
		buckysButton.setBorder(BorderFactory.createBevelBorder(0));
		buckysButton.setFocusable(false);
		return buckysButton;
	}
	
	//West Panel \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private JPanel eastPanel() throws IOException {
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridBagLayout());
		eastPanel.setBackground(View.DEFAULT_BG);
		eastPanel.add(yavapaiButton(), View.c);
		return eastPanel;
	}
	
	private JButton yavapaiButton;
	private JButton yavapaiButton() throws IOException {
		View.c.gridx = 0;
		View.c.gridy = 0;
		View.c.insets = new Insets(0,0,0,BTN_EDGE);
		yavapaiButton = new JButton();
		Image ycImage = ImageIO.read(getClass().getResource("/YCLogo2.png"));
		yavapaiButton.setIcon(new ImageIcon(ycImage));
		yavapaiButton.setPreferredSize(new Dimension(BTN_WIDTH,BTN_HEIGHT));
		yavapaiButton.setBackground(View.BTN_COLOR);
		yavapaiButton.setBorder(BorderFactory.createBevelBorder(0));
		yavapaiButton.setFocusable(false);
		return yavapaiButton;
	}
	
	//Getters \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	public JButton getBuckysButton() {
		return buckysButton;
	}
	
	public JButton getYavapaiButton() {
		return yavapaiButton;
	}
	
	//Set ActionListers
	public void setLocationActionListener(ActionListener l) {
		buckysButton.addActionListener(l);
		yavapaiButton.addActionListener(l);
	}

}
