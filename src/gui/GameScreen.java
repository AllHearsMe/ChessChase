package gui;

import java.util.Random;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.*;
import util.Config;

public class GameScreen extends StackPane {
	private Main main;
	private Canvas canvas;
	private GraphicsContext gc;

	private Field field;
	private Player player;

	private int time = 0;
	private int delay = 0;
	private int powerup = 3;

	private boolean isPaused = false, isHoldingPause = false;

	public GameScreen(Main main) {
		this.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);

		this.main = main;
		
		this.setOnKeyPressed(event -> handleKeyPressed(event));
		this.setOnKeyReleased(event -> handleKeyReleased(event));

//		startNewGame();
	}
	
	public void startNewGame()
	{
		time = 0;
		delay = 0;
		powerup = 3;
		isPaused = isHoldingPause = false;
		
		RenderableHolder.getInstance().getRenderables().clear();
		
		field = new Field(5000, 5000);
		player = new Player(field, 2500, 2500);
		field.setPlayer(player);
		for (int i = 0; i < 3; i++) {
			createEnemy(field);
		}
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				if (Main.isGameSceneShown()) {
					if (delay % (Config.NORMAL_TICK_PER_SECOND / 2) == 0){
						createEnemy(field);
					}
					update();
					paintComponent();
					if (player.isDestroyed()) {
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
									alert.setOnCloseRequest(f -> {
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

	public synchronized void paintComponent() {
		gc.clearRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

		RenderableHolder.getInstance().draw(gc);

		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Config.SCREEN_WIDTH, 100);
		Font font = new Font("Impact", 65);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		gc.fillText(Integer.toString(time), Config.SCREEN_WIDTH - 150, 75);
		FontLoader ft = Toolkit.getToolkit().getFontLoader();
		double width = ft.computeStringWidth("TIME : ", font);
		gc.fillText("TIME : ", Config.SCREEN_WIDTH - 150 - width, 75);
		gc.fillText(Integer.toString(powerup), 150, 75);
		Image powerup = new Image("file:pic/powerup.png");
		gc.drawImage(powerup, 50, 12.5);
		
	}

	public synchronized void update() {
		if (isPaused)
			return;
		if (delay % Config.NORMAL_TICK_PER_SECOND == 0) {
			time++;
		}
		delay++;
		field.updateFieldState();
	}

	private void handleKeyPressed(KeyEvent event) {
//		System.out.println("Pressed: " + event.getCode().toString());
		switch (event.getCode()) {
		case LEFT:
		case A:
			player.setDx(-1);
			break;
		case RIGHT:
		case D:
			player.setDx(1);
			break;
		case UP:
		case W:
			player.setDy(-1);
			break;
		case DOWN:
		case S:
			player.setDy(1);
			break;
		case Z:
			
			break;
		case X:
			
			break;
		case SPACE:
			if (!isHoldingPause)
				isPaused = !isPaused;
			isHoldingPause = true;
			break;
		default:
			break;
		}
	}

	private void handleKeyReleased(KeyEvent event) {
//		System.out.println("Released: " + event.getCode().toString());
		switch (event.getCode()) {
		case LEFT:
		case A:
		case RIGHT:
		case D:
			player.setDx(0);
			break;
		case UP:
		case W:
		case DOWN:
		case S:
			player.setDy(0);
			break;
		case SPACE:
			isHoldingPause = false;
			break;
		default:
			break;
		}

	}

	public void requestFocusForCanvas() {
		this.requestFocus();
	}
	
	public void createEnemy(Field field){
		long now = System.nanoTime();
		int spawnNumber = new Random(now).nextInt(Config.MAX_SPAWN) +1;
		for (int i = 0; i < spawnNumber; i++) {
			int spawnX = new Random(now).nextInt((int) field.getWidth());
			int spawnY = new Random(now).nextInt((int) field.getHeight());
			int spawnCase = new Random(now).nextInt(100);
			if (spawnCase < 40){
				field.addEnemy(new PawnEnemy(field, spawnX, spawnY));
			}else if (spawnCase < 55){
				field.addEnemy(new KnightEnemy(field, spawnX, spawnY));
			}else if (spawnCase < 70){
				field.addEnemy(new RookEnemy(field, spawnX, spawnY));
			}else if (spawnCase < 85){
				field.addEnemy(new BishopEnemy(field, spawnX, spawnY));
			}else if (spawnCase <95){
				field.addEnemy(new QueenEnemy(field, spawnX, spawnY));
			}else {
				field.addEnemy(new KingEnemy(field, spawnX, spawnY));
			}
		}
	}

}