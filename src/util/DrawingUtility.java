package util;

import javafx.scene.canvas.GraphicsContext;
import model.BishopEnemy;
import model.Field;
import model.KingEnemy;
import model.KnightEnemy;
import model.PawnEnemy;
import model.Player;
import model.QueenEnemy;
import model.RookEnemy;

public class DrawingUtility
{
	public static void drawPlayer(GraphicsContext gc, Player player, Field field)
	{
		gc.drawImage(ResourceLoader.getInstance().getPlayerSprite(player.getState(), player.getSpriteCounter()), 
				player.getDrawX() - field.getX(), player.getDrawY() - field.getY());
	}
	
	public static void drawPawn(GraphicsContext gc, PawnEnemy pawn, Field field)
	{
		gc.drawImage(ResourceLoader.getInstance().getPlayerSprite(pawn.getState(), pawn.getSpriteCounter()), 
				pawn.getDrawX() - field.getX(), pawn.getDrawY() - field.getY());
	}
	
	public static void drawRook(GraphicsContext gc, RookEnemy rook, Field field)
	{
		gc.drawImage(ResourceLoader.getInstance().getPlayerSprite(rook.getState(), rook.getSpriteCounter()), 
				rook.getDrawX() - field.getX(), rook.getDrawY() - field.getY());
	}
	
	public static void drawKnight(GraphicsContext gc, KnightEnemy knight, Field field)
	{
		gc.drawImage(ResourceLoader.getInstance().getKnightSprite(knight.getState(), knight.getSpriteCounter()), 
				knight.getDrawX() - field.getX(), knight.getDrawY() - field.getY());
	}
	
	public static void drawBishop(GraphicsContext gc, BishopEnemy bishop, Field field)
	{
		gc.drawImage(ResourceLoader.getInstance().getPlayerSprite(bishop.getState(), bishop.getSpriteCounter()), 
				bishop.getDrawX() - field.getX(), bishop.getDrawY() - field.getY());
	}
	
	public static void drawQueen(GraphicsContext gc, QueenEnemy queen, Field field)
	{
		gc.drawImage(ResourceLoader.getInstance().getQueenSprite(queen.getState(), queen.getSpriteCounter()), 
				queen.getDrawX() - field.getX(), queen.getDrawY() - field.getY());
	}
	
	public static void drawKing(GraphicsContext gc, KingEnemy king, Field field)
	{
		gc.drawImage(ResourceLoader.getInstance().getKingSprite(king.getState(), king.getSpriteCounter()), 
				king.getDrawX() - field.getX(), king.getDrawY() - field.getY());
	}
}
