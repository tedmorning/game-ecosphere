package com.gameco.client.view;

import javafx.scene.Scene;

public interface ISceneManager {

	public void changeLayout(ManagerMessage m);
	
	public Scene getScene();
	
}
