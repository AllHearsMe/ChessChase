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
		switch(state)
		{
			case DYING:
			case IDLE:
				return playerSprites.get(state.getIndex()).get(0);
			default:
				return playerSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getPawnSprite(EntityState state, int frame)
	{
		switch(state)
		{
			case DYING:
			case IDLE:
				return pawnSprites.get(state.getIndex()).get(0);
			default:
				return pawnSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getRookSprite(EntityState state, int frame)
	{
		switch(state)
		{
			case DYING:
			case IDLE:
				return rookSprites.get(state.getIndex()).get(0);
			default:
				return rookSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getKnightSprite(EntityState state, int frame)
	{
		switch(state)
		{
			case DYING:
			case IDLE:
				return knightSprites.get(state.getIndex()).get(0);
			default:
				return knightSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getBishopSprite(EntityState state, int frame)
	{
		switch(state)
		{
			case DYING:
			case IDLE:
				return bishopSprites.get(state.getIndex()).get(0);
			default:
				return bishopSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getQueenSprite(EntityState state, int frame)
	{
		switch(state)
		{
			case DYING:
			case IDLE:
				return queenSprites.get(state.getIndex()).get(0);
			default:
				return queenSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getKingSprite(EntityState state, int frame)
	{
		switch(state)
		{
			case DYING:
			case IDLE:
				return kingSprites.get(state.getIndex()).get(0);
			default:
				return kingSprites.get(state.getIndex()).get(frame);
		}
	}
}
