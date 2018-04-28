package com.gameco.client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.gameco.client.ClientContext;
import com.gameco.client.SceneManager;
import com.gameco.client.view.ManagerMessage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	
	@FXML
	TextField login;
	
	@FXML
	PasswordField password;
	
	@FXML
	private Label status;
	
	@FXML
	public void onSignIn() {
		try {
			ClientContext.getInstance().setConnection(login.getText(), password.getText());
			SceneManager.getInstance().changeLayout(ManagerMessage.MENU);
		} catch (Exception e) {
			this.status.setText(e.getMessage());
			e.printStackTrace();
		}
	}

	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
