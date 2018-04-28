package com.gameco.client;
import com.gameco.client.controllers.SceneManager;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class AppEntrance extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Tetris");
		Group root = new Group();
		Scene scene = new Scene(root, 800, 400);
		stage.setScene(scene);
		stage.show();
		SceneManager sc = SceneManager.getInstance();
		sc.init(stage, scene, root);
		sc.launch();
	}

}
