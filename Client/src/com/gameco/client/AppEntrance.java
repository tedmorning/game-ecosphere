package com.gameco.client;
import javafx.application.*;
import javafx.stage.*;

public class AppEntrance extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		SceneManager sc = SceneManager.getInstance();
		sc.init(stage);
		sc.launch();
	}

}
