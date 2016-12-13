/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package gui;

import java.util.Random;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import model.BishopEnemy;
import model.ZaWarudoSkill;
import model.Enemy;
import model.EntityState;
import model.Field;
import model.KingEnemy;
import model.KnightEnemy;
import model.PauseEffect;
import model.PawnEnemy;
import model.Player;
import model.QueenEnemy;
import model.RenderableHolder;
import model.RookEnemy;
import model.TripleAccelSkill;
import util.AudioUtility;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;

public class GameScreen extends StackPane {
	private Main main;
	private Canvas canvas;
	private GraphicsContext gc;

	private Field field;
	private Player player;
	private PauseEffect pauseEffect;

	private int time;
	private int delay;
	private int powerup;

	public GameScreen(Main main) {
		this.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);

		this.main = main;

		this.setOnKeyPressed(event -> InputUtility.handleKeyPress(event.getCode()));
		this.setOnKeyReleased(event -> InputUtility.handleKeyRelease(event.getCode()));
	}

	public void startNewGame() {
		resetValues();
		
		field = new Field(Config.FIELD_WIDTH, Config.FIELD_HEIGHT);
		player = new Player(field, Config.FIELD_WIDTH / 2, Config.FIELD_HEIGHT / 2);
		pauseEffect = new PauseEffect();
		field.setPlayer(player);
		for (int i = 0; i < 3; i++) {
			createEnemy(field);
		}
		
		//Initial enemies
		field.addEnemy(new PawnEnemy(field, player.getX() - 300, player.getX() - 300));
		field.addEnemy(new PawnEnemy(field, player.getX() + 300, player.getX() + 300));

		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				if (Main.isGameSceneShown()) {
					if (delay % (Config.NORMAL_TICK_PER_SECOND * 3 * Enemy.getDivider()) == 0) {
						createEnemy(field);
					}
					
					update();
					paintComponent();
					
					if (player.isDestroyed()) {
						AudioUtility.playGameOverSound();
						Platform.runLater(() -> DrawingUtility.fadeScreen(canvas, 1.0, e -> showGameOverAlert()));
						this.stop();
					}
				}
			}
		};
		animation.start();
	}

	public synchronized void paintComponent() {
		gc.clearRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		RenderableHolder.draw(gc);
		DrawingUtility.drawGameMenu(gc, time, powerup, field);

	}

	public synchronized void update()
	{
		checkInputKeys();
		if (pauseEffect.isPaused()) return;
		
		if (delay % Config.NORMAL_TICK_PER_SECOND == 0) time++;
		delay++;
		
		field.updateFieldState();
		if (!field.isSkillActive() && delay % Config.MULTIPLIER_DELAY == 0)
			Enemy.setMultiplier(Enemy.getMultiplier() + Config.MULTIPLIER_INCREMENT);
	}

	private void checkInputKeys()
	{
		player.setDx((InputUtility.getKeyPressed(KeyCode.LEFT)
				|| InputUtility.getKeyPressed(KeyCode.A) ? -1 : 0)
				+ (InputUtility.getKeyPressed(KeyCode.RIGHT)
						|| InputUtility.getKeyPressed(KeyCode.D) ? 1 : 0));
		player.setDy((InputUtility.getKeyPressed(KeyCode.UP)
				|| InputUtility.getKeyPressed(KeyCode.W) ? -1 : 0)
				+ (InputUtility.getKeyPressed(KeyCode.DOWN)
						|| InputUtility.getKeyPressed(KeyCode.S) ? 1 : 0));
		if(player.getState() != EntityState.DYING)
		{
			if (InputUtility.getKeyTriggered(KeyCode.SPACE))
				pauseEffect.togglePaused();
			if (!field.isSkillActive() && !pauseEffect.isPaused() && powerup > 0)
			{
				if (InputUtility.getKeyTriggered(KeyCode.Z))
				{
					powerup--;
					field.setSkill(new ZaWarudoSkill());
					AudioUtility.playZaWarudoSound();
				}
				else if (InputUtility.getKeyTriggered(KeyCode.X))
				{
					powerup--;
					field.setSkill(new TripleAccelSkill(player, field));
					AudioUtility.playTripleAccelSound();
				}
			}
		}
		InputUtility.postUpdate();
	}

	private void resetValues()
	{
		time = 0;
		delay = 0;
		powerup = 3;
		Enemy.setPaused(false);
		Enemy.setMultiplier(1);
		Enemy.setDivider(1);
		
		InputUtility.reset();
		RenderableHolder.getRenderables().clear();
	}

	public void createEnemy(Field field) {
		long now = System.nanoTime();
		int spawnNumber = new Random(now).nextInt(Config.MAX_SPAWN) + 1;
		for (int i = 0; i < spawnNumber; i++) {
			int spawnX = new Random(now / Config.SCREEN_HEIGHT / (i + 10)).nextInt((int) field.getWidth());
			int spawnY = new Random(now / Config.SCREEN_WIDTH / (i + 20)).nextInt((int) field.getHeight());
			int spawnCase = new Random(now / (i + 30)).nextInt(100);
			if (spawnCase < 40) {
				field.addEnemy(new PawnEnemy(field, spawnX, spawnY));
			} else if (spawnCase < 55) {
				field.addEnemy(new KnightEnemy(field, spawnX, spawnY));
			} else if (spawnCase < 70) {
				field.addEnemy(new RookEnemy(field, spawnX, spawnY));
			} else if (spawnCase < 85) {
				field.addEnemy(new BishopEnemy(field, spawnX, spawnY));
			} else if (spawnCase < 95) {
				field.addEnemy(new QueenEnemy(field, spawnX, spawnY));
			} else {
				field.addEnemy(new KingEnemy(field, spawnX, spawnY));
			}
		}
	}
	
	private void showGameOverAlert()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("You survived for "+ time + " seconds");
		if (time < 5){
			alert.setContentText("Use ArrowPad or WASD to move, OK?");
		} else if (time < 20){
			alert.setContentText("This game is not that hard, you know?");
		} else if (time < 30){
			alert.setContentText("Died already huh?");
		} else if (time < 50){
			alert.setContentText("DIE DIE DIE LOLOLOLOL");
		}else if (time < 90){
			alert.setContentText("You have done well for surviving this long.");
		}else {
			alert.setContentText("Go buy a lottery, maybe you will get the 1st price.");
		}
		
		alert.show();
		alert.setOnCloseRequest(f -> {
			main.toggleScene();
			AudioUtility.stopSoundEffect();
		});
	}

}