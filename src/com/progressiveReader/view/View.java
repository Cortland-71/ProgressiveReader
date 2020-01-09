package com.progressiveReader.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class View {
	
	public static GridBagConstraints c = new GridBagConstraints();
	public static final Color DEFAULT_BG = new Color(33,59,82);
	public static final Color BTN_COLOR = new Color(219,219,219);
	public static final Color DEFAULT_SUBMIT_COLOR = new Color(0,200,0);
	public static CardLayout cl = new CardLayout();
	private JPanel rootPanel;
	private LocationPage locationPage = new LocationPage();
	private UserPage userPage = new UserPage();
	private ProgressivePage progressivePage = new ProgressivePage();
	
	public View() throws IOException {
		JFrame frame = new JFrame("Progressive Reader v1.02");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1200,800));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(rootPanel());
		cl.show(rootPanel, "1");
		frame.setVisible(true);
	}
	
	private JPanel rootPanel() throws IOException {
		rootPanel = new JPanel();
		rootPanel.setLayout(cl);
		rootPanel.add(locationPage.getLocationPanel(), "1");
		rootPanel.add(userPage.getUserPanel(), "2");
		rootPanel.add(progressivePage.getProgressivePanel(), "3");
		return rootPanel;
	}
	
	public JPanel getRootPanel() {
		return rootPanel;
	}
	
	public LocationPage getLocationPage() {
		return locationPage;
	}
	
	public UserPage getUserPage() {
		return userPage;
	}
	
	public ProgressivePage getProgressivePage() {
		return progressivePage;
	}
	
	public static void setFont(JComponent comp, String font, int type, int size, Color fg) {
		comp.setFont(new Font(font, type, size));
		comp.setForeground(fg);
	}

}
