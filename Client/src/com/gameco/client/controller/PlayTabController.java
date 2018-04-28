package com.gameco.client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.gameco.client.SceneManager;
import com.gameco.client.view.ManagerMessage;
import com.gameco.games.chess.ChessGame;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PlayTabController implements Initializable {

	@FXML
	ListView<String> players;
	@FXML
	VBox info;
	@FXML
	ImageView gamelogo;
	@FXML
	Label enemy;
	protected boolean hasEnemy;
	protected StringProperty enemyname;
	@FXML
	Button invite;
	@FXML
	Label removeB;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hasEnemy = false;
		ObservableList<String> pls = FXCollections.observableArrayList();
		pls.addAll("player1", "player2", "player3", "player4", "player5", "player6", "player7");
		players.setItems(pls);
		
		enemyname = new SimpleStringProperty("player1");
		Bindings.bindBidirectional(enemy.textProperty(), enemyname);
		enemyname.setValue("");
		
		removeB.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				enemyname.setValue("");
				removeB.setVisible(false);
			}
		});
		
		players.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!hasEnemy) {
					enemyname.setValue(newValue);
					removeB.setVisible(true);
				}
			}

		});
		
		gamelogo.setImage(ChessGame.getLogo());
	}
	
	public void invite() {
		SceneManager.getInstance().changeLayout(ManagerMessage.GAME);
	}
	
}
