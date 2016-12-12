package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
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
	}
	
	private static ResourceLoader getInstance()
	{
		return instance;
	}
	
	private List<List<Image>> playerSprites, pawnSprites, rookSprites, knightSprites, bishopSprites, queenSprites, kingSprites;
	private Image fieldBackground, pauseImage;
	
	private AudioClip burstLinkSound, tripleAccelSound, gameOverSound, menuBGM, gameBGM;
	
	public static void loadResources()
	{
		fillSprites(getInstance().playerSprites, Config.PLAYER_PATH);
		fillSprites(getInstance().pawnSprites, Config.PAWN_PATH);
		fillSprites(getInstance().rookSprites, Config.ROOK_PATH);
		fillSprites(getInstance().knightSprites, Config.KNIGHT_PATH);
		fillSprites(getInstance().bishopSprites, Config.BISHOP_PATH);
		fillSprites(getInstance().queenSprites, Config.QUEEN_PATH);
		fillSprites(getInstance().kingSprites, Config.KING_PATH);
		
		getInstance().fieldBackground = new Image(ClassLoader.getSystemResource(Config.FIELD_BACKGROUND_PATH).toString());
		getInstance().pauseImage = new Image(ClassLoader.getSystemResource(Config.PAUSE_IMAGE_PATH).toString());
		
		getInstance().menuBGM = new AudioClip(ClassLoader.getSystemResource(Config.MENU_BGM_PATH).toString());
		getInstance().gameBGM = new AudioClip(ClassLoader.getSystemResource(Config.GAME_BGM_PATH).toString());
		getInstance().burstLinkSound = new AudioClip(ClassLoader.getSystemResource(Config.BURST_LINK_SOUND_PATH).toString());
		getInstance().tripleAccelSound = new AudioClip(ClassLoader.getSystemResource(Config.TRIPLE_ACCEL_SOUND_PATH).toString());
		getInstance().gameOverSound = new AudioClip(ClassLoader.getSystemResource(Config.GAME_OVER_SOUND_PATH).toString());
	}
	
	private static void fillSprites(List<List<Image>> list, String path)
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
	
	public static Image getPlayerSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return getInstance().playerSprites.get(state.getIndex()).get(0);
			case CREATED:
				return getInstance().playerSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return getInstance().playerSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getPawnSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return getInstance().pawnSprites.get(state.getIndex()).get(0);
			case CREATED:
				return getInstance().pawnSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return getInstance().pawnSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getRookSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return getInstance().rookSprites.get(state.getIndex()).get(0);
			case CREATED:
				return getInstance().rookSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return getInstance().rookSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getKnightSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return getInstance().knightSprites.get(state.getIndex()).get(0);
			case CREATED:
				return getInstance().knightSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return getInstance().knightSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getBishopSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return getInstance().bishopSprites.get(state.getIndex()).get(0);
			case CREATED:
				return getInstance().bishopSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return getInstance().bishopSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getQueenSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return getInstance().queenSprites.get(state.getIndex()).get(0);
			case CREATED:
				return getInstance().queenSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return getInstance().queenSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getKingSprite(EntityState state, int frame)
	{
		switch (state)
		{
			case DYING:
			case IDLE:
				return getInstance().kingSprites.get(state.getIndex()).get(0);
			case CREATED:
				return getInstance().kingSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return getInstance().kingSprites.get(state.getIndex()).get(frame);
		}
	}

	public static Image getFieldBackground()
	{
		return getInstance().fieldBackground;
	}

	public static Image getPauseImage()
	{
		return getInstance().pauseImage;
	}

	public static AudioClip getBurstLinkSound()
	{
		return getInstance().burstLinkSound;
	}

	public static AudioClip getTripleAccelSound()
	{
		return getInstance().tripleAccelSound;
	}

	public static AudioClip getGameOverSound()
	{
		return getInstance().gameOverSound;
	}

	public static AudioClip getMenuBGM()
	{
		return getInstance().menuBGM;
	}

	public static AudioClip getGameBGM()
	{
		return getInstance().gameBGM;
	}
}
