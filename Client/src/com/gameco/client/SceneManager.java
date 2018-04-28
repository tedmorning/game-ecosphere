package com.gameco.client;

import java.io.IOException;

import com.gameco.client.view.ISceneManager;
import com.gameco.client.view.ManagerMessage;
import com.gameco.games.chess.ChessGame;
import com.gameco.games.chess.view.SimpleChessController;

import javafx.beans.*;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.*;

public class SceneManager implements ISceneManager {

	protected Stage stage;
	protected Scene scene;
	protected BorderPane root;
	
	protected Parent currentView;
	protected ManagerMessage currentState;
	
	protected static SceneManager instance = null;
	
	public static SceneManager getInstance() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
		
	}
	
	public void init(Stage st) {
		this.stage = st;
		stage.setTitle("Game Launcher");
		this.root = new BorderPane();
		this.scene = new Scene(root, 800, 400);
		stage.setScene(scene);
		
//		stage.setMaximized(true);
		
		stage.setScene(this.scene);
//		stage.setFullScreenExitHint("");
//		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		//stage.setFullScreen(true);
		
		this.scene.heightProperty().addListener(resize);
		this.scene.widthProperty().addListener(resize);
		this.stage.setOnCloseRequest(this.onCloseWindow);
	}
	
	public void launch() {
		stage.show();
		this.changeLayout(ManagerMessage.LOG);
	}
	
	protected InvalidationListener resize = new InvalidationListener() {

		public void invalidated(Observable observable) {
			updateSize();
		}
	};
	
	protected EventHandler<WindowEvent> onCloseWindow = new EventHandler<WindowEvent>() {
		
		public void handle(WindowEvent event) {
		}
	};
	
	/**
	 * Handler is setting sizes of scenes.
	 */
	protected void updateSize() {
		this.root.setMaxHeight(this.scene.getHeight());
		this.root.setMinHeight(this.scene.getHeight());
		this.root.setPrefHeight(this.scene.getHeight());
		
		this.root.setMaxWidth(this.scene.getWidth());
		this.root.setMinWidth(this.scene.getWidth());
		this.root.setPrefWidth(this.scene.getWidth());
	}
	
	/**
	 * Switch layout in depending on given {@link ManagerMessage}.
	 * @param mm Switch signal.
	 */
	public void changeLayout(ManagerMessage mm) {
		switch (mm) {
			case LOG:
				try {
					root.getChildren().clear();
					Parent adding = getView("LoginView.fxml");
					root.setCenter(adding);
					currentView = root;
					currentState = mm;
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MENU:
				try {
					if (this.currentState == ManagerMessage.INVITE) {
						Label label = new Label("Canceled");
						label.setFont(new Font(50));
						this.root.getChildren().addAll(label);
						this.root.getChildren().get(this.root.getChildren().size()-1).toFront();
						Thread.sleep(3000);
					}
					root.getChildren().clear();
					Parent adding = getView("MenuView.fxml");
					root.setCenter(adding);
					currentView = adding;
					currentState = mm;
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case GAME:
				try {
					root.getChildren().clear();
					Canvas canvas = new Canvas();
					SimpleChessController controller = new SimpleChessController(new ChessGame(false, null), scene, canvas);
					canvas.setHeight(scene.getHeight());
					canvas.setWidth(scene.getWidth());
					scene.heightProperty().addListener(new InvalidationListener() {
						@Override
						public void invalidated(Observable observable) {
							root.setMaxHeight(scene.getHeight());
							root.setMinHeight(scene.getHeight());
							root.setPrefHeight(scene.getHeight());
							
							root.setMaxWidth(scene.getWidth());
							root.setMinWidth(scene.getWidth());
							root.setPrefWidth(scene.getWidth());
							controller.draw();
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
							controller.draw();
						}
					});
					controller.draw();
//					ChessGame game = new ChessGame(false, null);
//					context.setGame(game);
//					Parent adding = context.getGame().getView();
					root.setCenter(canvas);
					currentView = null;
					currentState = mm;
				} catch (Exception e) {
					e.printStackTrace();
				}
			case INVITE:
				try {
					this.root.getChildren().get(0).toBack();
					Parent adding = getView("InviteView.fxml");
					root.setCenter(adding);
					root.getChildren().get(1).toFront();
					currentView = adding;
					currentState = mm;
				}
				catch (Exception e) {
					// TODO: handle exception
				}
//				
//				VBox border = new VBox();
//				
//				Label title = new Label("Chessgame");
//				title.setAlignment(Pos.TOP_CENTER);
//				
//				ClientContext co = (ClientContext) ClientContext.getInstance();
//				
//				HBox enemies = new HBox(10.0);
//				enemies.getChildren().addAll(new Label(co.getPlayers().get(0)), new Label("vs."), new Label(co.getPlayers().get(1)));
//				enemies.setAlignment(Pos.CENTER);
//				
//				border.getChildren().addAll(title, enemies);
//				if (!co.getPlayers().contains(co.getUser())) {
//					HBox answer = new HBox(20.0);
//					Button accept = new Button("Accept");
//					accept.setAlignment(Pos.CENTER_LEFT);
//					accept.setOnAction(new EventHandler<ActionEvent>() {
//						
//						@Override
//						public void handle(ActionEvent event) {
//							IGameExchangeService gg = (IGameExchangeService) co.getService();
//							gg.accept(co.gameID);
//						}
//					});
//					Button decline = new Button("Decline");
//					decline.setAlignment(Pos.CENTER_RIGHT);
//					decline.setOnAction(new EventHandler<ActionEvent>() {
//						
//						@Override
//						public void handle(ActionEvent event) {
//							IGameExchangeService gg = (IGameExchangeService) co.getService();
//							gg.cancel(co.gameID);
//							co.gameID = null;
//							co.players = null;
//							co.game = null;
//							SceneManager.getInstance().changeLayout(ManagerMessage.MENU);
//						}
//					});
//					answer.getChildren().addAll(accept, decline);
//					border.getChildren().addAll(answer);
//					this.root.getChildren().addAll(border);
//					this.root.getChildren().get(1).toFront();
//				}
				break;
			default: break;
		}
		updateSize();
	}
	
	protected Parent getView(String view) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setRoot(root);
		return FXMLLoader.load(this.getClass().getResource("/views/"+view));
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
	
}
