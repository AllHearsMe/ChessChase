package gui;


import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.Config;

public class GameScreen extends StackPane {
	private Canvas canvas;
	private GraphicsContext gc;
	private int time = 0;
	private int delay = 0;
	private int powerup = 3;
	public GameScreen(){
		this.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				if (Main.isGameSceneShown()){
					paintComponenet();
					update();
				}
			}
		};
		animation.start();
	}
	
	public synchronized void paintComponenet(){
		gc.setFill(Color.BLACK);
		gc.clearRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		gc.fillRect(0, 0, Config.SCREEN_WIDTH, 100);
		Font font = new Font("Impact", 65);
		gc.setFont(font);
		// delete here
		gc.fillText("Insert background here", 100, 400);
		gc.fillText("Insert powerup pic here", 0, 170);
		gc.fillText("I dont know what to do anymore orz", 0, 500);
		//
		//
		gc.setFill(Color.WHITE);
		gc.fillText(Integer.toString(time),Config.SCREEN_WIDTH - 100, 75);
		FontLoader ft = Toolkit.getToolkit().getFontLoader();
		double width = ft.computeStringWidth("TIME : ", font);
		gc.fillText("TIME : ",Config.SCREEN_WIDTH - 100 - width , 75);
		gc.fillText(Integer.toString(powerup), 150 , 75);
		//insert picture here
		gc.fillText("pic", 50 , 75);
		//
		//
		
	}
	
	public synchronized void update(){
		if (delay%60==0){
		time++;
		}
		delay++;
	}

}
