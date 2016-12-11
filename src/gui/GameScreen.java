package gui;


import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.Field;
import model.IRenderable;
import model.KnightEnemy;
import model.Player;
import model.RenderableHolder;
import util.Config;

public class GameScreen extends StackPane {
	private Canvas canvas;
	private GraphicsContext gc;
	
	private Field field;
	private Player player;
	
	private int time = 0;
	private int delay = 0;
	private int powerup = 3;
	public GameScreen(Main main){
		this.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		
		field = new Field(5000, 5000);
		player = new Player(field, 2500, 2500);
		field.setPlayer(player);
		field.addEnemy(new KnightEnemy(field, 2300, 2300));
		
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				if (Main.isGameSceneShown()){
					update();
					paintComponent();
					if (checkEnd()){
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								FadeTransition ft = new FadeTransition(new Duration(2000), canvas);
								ft.setFromValue(1.0);
								ft.setToValue(0.0);
								ft.setCycleCount(1);
								ft.playFromStart();
								ft.setOnFinished(e -> {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setContentText("You died noob LOLOLOL");
									alert.show();
									alert.setOnCloseRequest(f->{
										main.toggleScene();
									});
								});
								
							}
						});
						this.stop();
					}
				}
			}
		};
		animation.start();
		
	}
	
	public synchronized void paintComponent(){
		gc.clearRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
		for(IRenderable r : RenderableHolder.getInstance().getRenderables())
			r.draw(gc);

		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Config.SCREEN_WIDTH, 100);
		Font font = new Font("Impact", 65);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		gc.fillText(Integer.toString(time),Config.SCREEN_WIDTH - 150, 75);
		FontLoader ft = Toolkit.getToolkit().getFontLoader();
		double width = ft.computeStringWidth("TIME : ", font);
		gc.fillText("TIME : ",Config.SCREEN_WIDTH - 150 - width , 75);
		gc.fillText(Integer.toString(powerup), 150 , 75);
		Image powerup = new Image("file:pic/powerup.png");
		gc.drawImage(powerup, 50, 12.5);
		
	}
	
	public synchronized void update(){
		if (delay%60==0){
		time++;
		}
		delay++;
		field.updateFieldState();
	}
	
	public boolean checkEnd(){
		if (player.isDestroyed()){
			return true;
		}else 
			return false;
	}

}
