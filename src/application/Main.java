package application;


import gui.GameScreen;
import gui.MenuScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.ResourceMissingException;
import util.AudioUtility;
import util.Config;
import util.DrawingUtility;
import util.ResourceLoader;

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
			ResourceLoader.loadResources();
			
			this.primaryStage = primaryStage;
			primaryStage.setTitle("Chess Chase");
			primaryStage.setResizable(false);
			primaryStage.setHeight(Config.SCREEN_HEIGHT);
			primaryStage.setWidth(Config.SCREEN_WIDTH);
			
			menuScreen = new MenuScreen();
			gameScreen = new GameScreen(this);
			
			menuScene = new Scene(menuScreen, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			gameScene = new Scene(gameScreen, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			
			primaryStage.setScene(menuScene);
			primaryStage.show();
			
			AudioUtility.playMenuBGM();
			
			menuScene.setOnKeyPressed(e ->{
				if (e.getCode() == KeyCode.ENTER){
					if (!isGameSceneShown){
						toggleScene();
					}
				}	
			});
			
			
		}
		catch (ResourceMissingException e)
		{
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Some of the resources are missing.");
			alert.showAndWait();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void toggleScene(){
		if (isGameSceneShown) {
			this.primaryStage.setScene(menuScene);
			DrawingUtility.fadeScreen(menuScreen, 0.0, e -> AudioUtility.playMenuBGM());
		}
		else {
			DrawingUtility.fadeScreen(menuScreen, 1.0, e -> {
				gameScreen = new GameScreen(this);
				gameScene = new Scene(gameScreen, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
				gameScreen.requestFocusForCanvas();
				this.primaryStage.setScene(gameScene);
				gameScreen.startNewGame();
				AudioUtility.playGameBGM();
			});
		}
		isGameSceneShown = !isGameSceneShown;
	}

	public static boolean isGameSceneShown() {
		return isGameSceneShown;
	}

}
