package application;

import com.sun.xml.internal.ws.developer.SerializationFeature;

import gui.GameScreen;
import gui.MenuScreen;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Config;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Main extends Application{
	private Stage primaryStage;
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	private Scene menuScene,gameScene;
	private static boolean isGameSceneShown = false;
	
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			this.primaryStage=primaryStage;
			primaryStage.setTitle("Chess Chase[temp]");
			menuScreen = new MenuScreen();
			gameScreen = new GameScreen();
			menuScene = new Scene(menuScreen, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			gameScene = new Scene(gameScreen, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(menuScene);
			primaryStage.show();
			
			menuScene.setOnKeyPressed(e ->{
				if (e.getCode() == KeyCode.ENTER){
					if (!isGameSceneShown){	
						toggleScene();
					}
				}	
			});
			gameScene.setOnKeyPressed(e ->{
				/*
				 * to do na ja
				 * 
				 * 
				 */
			});
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public synchronized void toggleScene(){
		if (isGameSceneShown){
			this.primaryStage.setScene(menuScene);
			isGameSceneShown = !isGameSceneShown;
		}
		else{
			FadeTransition ft = new FadeTransition(new Duration(2000), menuScreen);
			ft.setFromValue(1.0);
			ft.setToValue(0.0);
			ft.setCycleCount(1);
			ft.playFromStart();
			ft.setOnFinished(e -> {
				this.primaryStage.setScene(gameScene);
				isGameSceneShown = !isGameSceneShown;
			});
			
		}
	}

	public static boolean isGameSceneShown() {
		return isGameSceneShown;
	}

}
