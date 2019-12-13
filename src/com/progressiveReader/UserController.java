package com.progressiveReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.progressiveReader.view.View;

public class UserController implements ActionListener {
	
private View view;
	
	public UserController(View view) {
		this.view = view;
		this.view.getUserPage().setBackButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getUserPage().getBackButton()) {
			View.cl.show(view.getRootPanel(), "1");
			return;
		}
		submitNewUser();
	}
	
	private void submitNewUser() {
		if (view.getUserPage().getNameFieldText().trim().isEmpty()) {
			view.getUserPage().clearNameField();
			JOptionPane.showMessageDialog(null,"You must enter your name to proceed", "No name entered",JOptionPane.ERROR_MESSAGE);
			return;
		}
		View.cl.show(view.getRootPanel(), "3");
		
	}
}
