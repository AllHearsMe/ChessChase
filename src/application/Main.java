package application;


import gui.GameScreen;
import gui.MenuScreen;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.Main;
import util.AudioUtility;
import util.Config;
import util.ResourceLoader;
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
		ResourceLoader.loadResources();
		
		try
		{
			this.primaryStage=primaryStage;
			primaryStage.setTitle("Chess Chase");
			menuScreen = new MenuScreen();
			gameScreen = new GameScreen(this);
			menuScene = new Scene(menuScreen, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			gameScene = new Scene(gameScreen, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(menuScene);
			primaryStage.setResizable(false);
			primaryStage.setHeight(Config.SCREEN_HEIGHT);
			primaryStage.setWidth(Config.SCREEN_WIDTH);
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
			isGameSceneShown = !isGameSceneShown;
			this.primaryStage.setScene(menuScene);
			FadeTransition ft = new FadeTransition(new Duration(2000), menuScreen);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.setCycleCount(1);
			ft.playFromStart();
			ft.setOnFinished(e->{
				AudioUtility.playMenuBGM();
			});
		}
		else{
			isGameSceneShown = !isGameSceneShown;
			FadeTransition ft = new FadeTransition(new Duration(2000), menuScreen);
			ft.setFromValue(1.0);
			ft.setToValue(0.0);
			ft.setCycleCount(1);
			ft.playFromStart();
			ft.setOnFinished(e -> {
				gameScreen = new GameScreen(this);
				gameScene = new Scene(gameScreen, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
				gameScreen.requestFocusForCanvas();
				this.primaryStage.setScene(gameScene);
				gameScreen.startNewGame();
				AudioUtility.playGameBGM();
			});
			
		}
	}

	public static boolean isGameSceneShown() {
		return isGameSceneShown;
	}

}
