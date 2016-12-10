package application;

import gui.MenuScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import util.Config;
import javafx.scene.Scene;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			MenuScreen root = new MenuScreen();
			Scene scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
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
}
