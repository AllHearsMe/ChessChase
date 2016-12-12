package util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import model.BishopEnemy;
import model.EntityState;
import model.Field;
import model.KingEnemy;
import model.KnightEnemy;
import model.PawnEnemy;
import model.Player;
import model.QueenEnemy;
import model.RookEnemy;

public class DrawingUtility
{
	private static void drawSprite(GraphicsContext gc, Image sprite, EntityState state, int spriteCounter, double x, double y, int facing)
	{
		if (state == EntityState.DYING) gc.setGlobalAlpha(1 - (double) spriteCounter / Config.DYING_FRAMES);
		gc.scale(facing, 1);
		gc.drawImage(sprite, facing * x - (facing < 0 ? sprite.getWidth() : 0), y);
		gc.setGlobalAlpha(1);
		gc.scale(facing, 1);
	}
	
	public static void drawField(GraphicsContext gc, Field field)
	{
		Image background = ResourceLoader.getFieldBackground();
		gc.setFill(new ImagePattern(background, 0, 0, background.getWidth() / field.getWidth(), background.getHeight() / field.getHeight(), true));
		gc.fillRect(-field.getX(), -field.getY(), field.getWidth(), field.getHeight());
	}
	
	public static void drawPlayer(GraphicsContext gc, Player player, Field field)
	{
		drawSprite(gc, ResourceLoader.getPlayerSprite(player.getState(), player.getSpriteCounter()), player.getState(),
				player.getSpriteCounter(), player.getDrawX() - field.getX(), player.getDrawY() - field.getY(), player.getDx() < 0 ? -1 : 1);
	}
	
	public static void drawPawn(GraphicsContext gc, PawnEnemy pawn, Field field)
	{
		drawSprite(gc, ResourceLoader.getPawnSprite(pawn.getState(), pawn.getSpriteCounter()), pawn.getState(),
				pawn.getSpriteCounter(), pawn.getDrawX() - field.getX(), pawn.getDrawY() - field.getY(), pawn.getDirection().getFacing());
	}
	
	public static void drawRook(GraphicsContext gc, RookEnemy rook, Field field)
	{
		drawSprite(gc, ResourceLoader.getRookSprite(rook.getState(), rook.getSpriteCounter()), rook.getState(),
				rook.getSpriteCounter(), rook.getDrawX() - field.getX(), rook.getDrawY() - field.getY(), rook.getDirection().getFacing());
	}
	
	public static void drawKnight(GraphicsContext gc, KnightEnemy knight, Field field)
	{
		drawSprite(gc, ResourceLoader.getKnightSprite(knight.getState(), knight.getSpriteCounter()), knight.getState(),
				knight.getSpriteCounter(), knight.getDrawX() - field.getX(), knight.getDrawY() - field.getY(), knight.getDirection().getFacing());
	}
	
	public static void drawBishop(GraphicsContext gc, BishopEnemy bishop, Field field)
	{
		drawSprite(gc, ResourceLoader.getBishopSprite(bishop.getState(), bishop.getSpriteCounter()), bishop.getState(),
				bishop.getSpriteCounter(), bishop.getDrawX() - field.getX(), bishop.getDrawY() - field.getY(), bishop.getDirection().getFacing());
	}
	
	public static void drawQueen(GraphicsContext gc, QueenEnemy queen, Field field)
	{
		drawSprite(gc, ResourceLoader.getQueenSprite(queen.getState(), queen.getSpriteCounter()), queen.getState(),
				queen.getSpriteCounter(), queen.getDrawX() - field.getX(), queen.getDrawY() - field.getY(), queen.getDirection().getFacing());
	}
	
	public static void drawKing(GraphicsContext gc, KingEnemy king, Field field)
	{
		drawSprite(gc, ResourceLoader.getKingSprite(king.getState(), king.getSpriteCounter()), king.getState(),
				king.getSpriteCounter(), king.getDrawX() - field.getX(), king.getDrawY() - field.getY(), king.getDirection().getFacing());
	}
	
	public static void drawBurstLinkEffect(GraphicsContext gc)
	{
		gc.setGlobalAlpha(0.50);
		gc.setFill(Color.DARKSLATEBLUE);
		gc.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		gc.setGlobalAlpha(1);
	}
	
	public static void drawPauseEffect(GraphicsContext gc)
	{
		gc.setGlobalAlpha(0.50);
		gc.setFill(Color.STEELBLUE);
		gc.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		gc.setGlobalAlpha(1);
		Image image = ResourceLoader.getPauseImage();
		gc.drawImage(image, (Config.SCREEN_WIDTH - image.getWidth()) / 2, (Config.SCREEN_HEIGHT - image.getHeight()) / 2);
	}
	
	public static void drawPlayerAfterImage(GraphicsContext gc, double x, double y, EntityState state, int spriteCounter, int facing, Field field, double opacity)
	{
		gc.setGlobalAlpha(opacity);
		drawSprite(gc, ResourceLoader.getPlayerSprite(state, spriteCounter), state,
				spriteCounter, x - field.getX(), y - field.getY(), facing);
		gc.setGlobalAlpha(1);
	}
}
