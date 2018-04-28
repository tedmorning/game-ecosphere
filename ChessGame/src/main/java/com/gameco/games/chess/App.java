package com.gameco.games.chess;
import com.gameco.games.chess.ChessGame;
import com.gameco.games.chess.view.SimpleChessController;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public Scene scene;

	@Override
	public void start(Stage stage) throws Exception {
		ChessGame game = new ChessGame(false, null);
		stage.setTitle("Game Launcher");
		AnchorPane root = new AnchorPane();
		Canvas canvas = new Canvas();
		AnchorPane.setBottomAnchor(canvas, 0.0);
		AnchorPane.setLeftAnchor(canvas, 0.0);
		AnchorPane.setRightAnchor(canvas, 0.0);
		AnchorPane.setTopAnchor(canvas, 0.0);
		root.getChildren().add(canvas);
		scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		canvas.setHeight(scene.getHeight());
		canvas.setWidth(scene.getWidth());
		SimpleChessController cont = new SimpleChessController(game, scene, canvas);
		scene.heightProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				root.setMaxHeight(scene.getHeight());
				root.setMinHeight(scene.getHeight());
				root.setPrefHeight(scene.getHeight());
				
				root.setMaxWidth(scene.getWidth());
				root.setMinWidth(scene.getWidth());
				root.setPrefWidth(scene.getWidth());
				cont.draw();
			}
		});
		scene.widthProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				root.setMaxHeight(scene.getHeight());
				root.setMinHeight(scene.getHeight());
				root.setPrefHeight(scene.getHeight());
				
				root.setMaxWidth(scene.getWidth());
				root.setMinWidth(scene.getWidth());
				root.setPrefWidth(scene.getWidth());
				cont.draw();
			}
		});
		cont.draw();
		stage.show();
	}
	
	protected InvalidationListener resize = new InvalidationListener() {

		public void invalidated(Observable observable) {
			
		}
	};

}
