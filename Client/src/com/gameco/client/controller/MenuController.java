package com.gameco.client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.gameco.client.SceneManager;
import com.gameco.client.view.ManagerMessage;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuController implements Initializable {

	@FXML
	protected Label user;
	
//	@FXML
//	protected PlayTabController playTabContentController;
//	@FXML
//	protected WatchTabController watchTabContentController;
	
	SimpleStringProperty username;
	
	public StringProperty usernameProperty() {
		return username;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username = new SimpleStringProperty("dfsdf");
		Bindings.bindBidirectional(user.textProperty(), username);
		username.setValue("test");
//		username.setValue(IClientContext.getInstance().getUser().getUsername());
//		playTabContentController.s
	}
	
	@FXML
	public void doLogout() {
		SceneManager.getInstance().changeLayout(ManagerMessage.LOG);
	}

}
