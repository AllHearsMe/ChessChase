package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import model.EntityState;

public class ResourceLoader
{
	private static ResourceLoader instance = new ResourceLoader();
	
	private ResourceLoader()
	{
		playerSprites = new ArrayList<>();
		pawnSprites = new ArrayList<>();
		rookSprites = new ArrayList<>();
		knightSprites = new ArrayList<>();
		bishopSprites = new ArrayList<>();
		queenSprites = new ArrayList<>();
		kingSprites = new ArrayList<>();
		
//		loadResources();
	}

	public static ResourceLoader getInstance()
	{
		return instance;
	}
	
	private List<List<Image>> playerSprites, pawnSprites, rookSprites, knightSprites, bishopSprites, queenSprites, kingSprites;
	
	public void loadResources()
	{
		
	}
	
	public Image getPlayerSprite(EntityState state, int frame)
	{
		return playerSprites.get(state.getIndex()).get(frame);
	}
	
	public Image getPawnSprite(EntityState state, int frame)
	{
		return pawnSprites.get(state.getIndex()).get(frame);
	}
	
	public Image getRookSprite(EntityState state, int frame)
	{
		return rookSprites.get(state.getIndex()).get(frame);
	}
	
	public Image getKnightSprite(EntityState state, int frame)
	{
		return knightSprites.get(state.getIndex()).get(frame);
	}
	
	public Image getBishopSprite(EntityState state, int frame)
	{
		return bishopSprites.get(state.getIndex()).get(frame);
	}
	
	public Image getQueenSprite(EntityState state, int frame)
	{
		return queenSprites.get(state.getIndex()).get(frame);
	}
	
	public Image getKingSprite(EntityState state, int frame)
	{
		return kingSprites.get(state.getIndex()).get(frame);
	}
	
	public int getPlayerTotalSprites(EntityState state)
	{
		return playerSprites.get(state.getIndex()).size();
	}
	
	public int getPawnTotalSprites(EntityState state)
	{
		return pawnSprites.get(state.getIndex()).size();
	}
	
	public int getRookTotalSprites(EntityState state)
	{
		return rookSprites.get(state.getIndex()).size();
	}
	
	public int getKnightTotalSprites(EntityState state)
	{
		return knightSprites.get(state.getIndex()).size();
	}
	
	public int getBishopTotalSprites(EntityState state)
	{
		return bishopSprites.get(state.getIndex()).size();
	}
	
	public int getQueenTotalSprites(EntityState state)
	{
		return queenSprites.get(state.getIndex()).size();
	}
	
	public int getKingTotalSprites(EntityState state)
	{
		return kingSprites.get(state.getIndex()).size();
	}
}
