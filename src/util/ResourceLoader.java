package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.EntityState;

public class ResourceLoader
{
	private static ResourceLoader instance = new ResourceLoader();
	
	private ResourceLoader()
	{
		// Should be called at program's start instead
		// loadResources();
	}
	
	public static ResourceLoader getInstance()
	{
		return instance;
	}
	
	private List<List<Image>> playerSprites, pawnSprites, rookSprites, knightSprites, bishopSprites, queenSprites, kingSprites;
	private Image fieldBackground, pauseImage;
	
	public void loadResources()
	{
		playerSprites = new ArrayList<>();
		pawnSprites = new ArrayList<>();
		rookSprites = new ArrayList<>();
		knightSprites = new ArrayList<>();
		bishopSprites = new ArrayList<>();
		queenSprites = new ArrayList<>();
		kingSprites = new ArrayList<>();
		
		fillSprites(playerSprites, Config.PLAYER_PATH);
		fillSprites(pawnSprites, Config.PAWN_PATH);
		fillSprites(rookSprites, Config.ROOK_PATH);
		fillSprites(knightSprites, Config.KNIGHT_PATH);
		fillSprites(bishopSprites, Config.BISHOP_PATH);
		fillSprites(queenSprites, Config.QUEEN_PATH);
		fillSprites(kingSprites, Config.KING_PATH);
		
		fieldBackground = new Image(ClassLoader.getSystemResource(Config.FIELD_BACKGROUND_PATH).toString());
		pauseImage = new Image(ClassLoader.getSystemResource(Config.PAUSE_IMAGE_PATH).toString());
	}
	
	private void fillSprites(List<List<Image>> list, String path)
	{
		for (EntityState state : EntityState.values())
		{
			if (state != EntityState.CREATED)
			{
				if((state == EntityState.AWAKENING || state == EntityState.SPAWNING) && path.equals(Config.PLAYER_PATH))
				{
					list.add(state.getIndex(), new ArrayList<>());
					continue;
				}
				
				List<Image> tempList = new ArrayList<>();
				Image image = new Image(ClassLoader.getSystemResource(path + Config.getSpriteFileName(state)).toString());
				for (int i = 0; i < Config.getFrameCount(state); i++)
				{
					tempList.add(new WritableImage(image.getPixelReader(), i * (int) image.getWidth() / Config.getFrameCount(state),
							0, (int) image.getWidth() / Config.getFrameCount(state), (int) image.getHeight()));
				}
				list.add(state.getIndex(), tempList);
			}
		}
	}
	
	public Image getPlayerSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return playerSprites.get(state.getIndex()).get(0);
			case CREATED:
				return playerSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return playerSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getPawnSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return pawnSprites.get(state.getIndex()).get(0);
			case CREATED:
				return pawnSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return pawnSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getRookSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return rookSprites.get(state.getIndex()).get(0);
			case CREATED:
				return rookSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return rookSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getKnightSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return knightSprites.get(state.getIndex()).get(0);
			case CREATED:
				return knightSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return knightSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getBishopSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return bishopSprites.get(state.getIndex()).get(0);
			case CREATED:
				return bishopSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return bishopSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getQueenSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return queenSprites.get(state.getIndex()).get(0);
			case CREATED:
				return queenSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return queenSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public Image getKingSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return kingSprites.get(state.getIndex()).get(0);
			case CREATED:
				return kingSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return kingSprites.get(state.getIndex()).get(frame);
		}
	}

	public Image getFieldBackground()
	{
		return fieldBackground;
	}

	public Image getPauseImage()
	{
		return pauseImage;
	}
}
