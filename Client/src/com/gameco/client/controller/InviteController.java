package com.gameco.client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.gameco.client.ClientContext;
import com.gameco.client.SceneManager;
import com.gameco.client.view.ManagerMessage;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class InviteController implements Initializable {

	@FXML
	Label first;
	@FXML
	Label second;
	@FXML
	HBox decision;
	@FXML
	Label status;
	
	StringProperty firstPlayer, secondPlayer, statusString;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		firstPlayer = new SimpleStringProperty("");
		secondPlayer = new SimpleStringProperty("");
		statusString = new SimpleStringProperty("");
		Bindings.bindBidirectional(first.textProperty(), firstPlayer);
		Bindings.bindBidirectional(second.textProperty(), secondPlayer);
		Bindings.bindBidirectional(status.textProperty(), statusString);
		firstPlayer.setValue(ClientContext.getInstance().getPlayers().get(0));
		secondPlayer.setValue(ClientContext.getInstance().getPlayers().get(1));
		if (ClientContext.getInstance().getPlayers().contains(ClientContext.getInstance().getUser())) {
			decision.setVisible(false);
			statusString.setValue("Awaiting other members..");
		}
	}
	
	public void doAccept() {
		ClientContext.getInstance().getServiceImpl().accept(ClientContext.getInstance().getUser());
		statusString.setValue("Game starting..");
	}
	
	public void doDecline() {
		ClientContext.getInstance().getServiceImpl().cancel(ClientContext.getInstance().getUser());
		SceneManager.getInstance().changeLayout(ManagerMessage.MENU);
	}

}
