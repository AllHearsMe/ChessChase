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
	private Image fieldBackground, pauseImage, powerupImage, frontTitleImage, backTitleImage;
	
	private AudioClip burstLinkSound, tripleAccelSound, gameOverSound, menuBGM, gameBGM;
	
	public static void loadResources()
	{
		getInstance();
		
		fillSprites(instance.playerSprites, Config.PLAYER_PATH);
		fillSprites(instance.pawnSprites, Config.PAWN_PATH);
		fillSprites(instance.rookSprites, Config.ROOK_PATH);
		fillSprites(instance.knightSprites, Config.KNIGHT_PATH);
		fillSprites(instance.bishopSprites, Config.BISHOP_PATH);
		fillSprites(instance.queenSprites, Config.QUEEN_PATH);
		fillSprites(instance.kingSprites, Config.KING_PATH);
		
		instance.fieldBackground = new Image(ClassLoader.getSystemResource(Config.FIELD_BACKGROUND_PATH).toString());
		instance.pauseImage = new Image(ClassLoader.getSystemResource(Config.PAUSE_IMAGE_PATH).toString());
		instance.powerupImage = new Image(ClassLoader.getSystemResource(Config.POWERUP_PATH).toString());
		instance.frontTitleImage = new Image(ClassLoader.getSystemResource(Config.FRONT_TITLE_PATH).toString());
		instance.backTitleImage = new Image(ClassLoader.getSystemResource(Config.BACK_TITLE_PATH).toString());
		
		instance.menuBGM = new AudioClip(ClassLoader.getSystemResource(Config.MENU_BGM_PATH).toString());
		instance.gameBGM = new AudioClip(ClassLoader.getSystemResource(Config.GAME_BGM_PATH).toString());
		instance.burstLinkSound = new AudioClip(ClassLoader.getSystemResource(Config.BURST_LINK_SOUND_PATH).toString());
		instance.tripleAccelSound = new AudioClip(ClassLoader.getSystemResource(Config.TRIPLE_ACCEL_SOUND_PATH).toString());
		instance.gameOverSound = new AudioClip(ClassLoader.getSystemResource(Config.GAME_OVER_SOUND_PATH).toString());
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
		getInstance();
		switch (state)
		{
			case DYING:
			case IDLE:
				return instance.playerSprites.get(state.getIndex()).get(0);
			case CREATED:
				return instance.playerSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return instance.playerSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getPawnSprite(EntityState state, int frame)
	{
		getInstance();
		switch (state)
		{
			case DYING:
			case IDLE:
				return instance.pawnSprites.get(state.getIndex()).get(0);
			case CREATED:
				return instance.pawnSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return instance.pawnSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getRookSprite(EntityState state, int frame)
	{
		getInstance();
		switch (state)
		{
			case DYING:
			case IDLE:
				return instance.rookSprites.get(state.getIndex()).get(0);
			case CREATED:
				return instance.rookSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return instance.rookSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getKnightSprite(EntityState state, int frame)
	{
		getInstance();
		switch (state)
		{
			case DYING:
			case IDLE:
				return instance.knightSprites.get(state.getIndex()).get(0);
			case CREATED:
				return instance.knightSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return instance.knightSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getBishopSprite(EntityState state, int frame)
	{
		getInstance();
		switch (state)
		{
			case DYING:
			case IDLE:
				return instance.bishopSprites.get(state.getIndex()).get(0);
			case CREATED:
				return instance.bishopSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return instance.bishopSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getQueenSprite(EntityState state, int frame)
	{
		getInstance();
		switch (state)
		{
			case DYING:
			case IDLE:
				return instance.queenSprites.get(state.getIndex()).get(0);
			case CREATED:
				return instance.queenSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return instance.queenSprites.get(state.getIndex()).get(frame);
		}
	}
	
	public static Image getKingSprite(EntityState state, int frame)
	{
		getInstance();
		switch (state)
		{
			case DYING:
			case IDLE:
				return instance.kingSprites.get(state.getIndex()).get(0);
			case CREATED:
				return instance.kingSprites.get(EntityState.SPAWNING.getIndex()).get(0);
			default:
				return instance.kingSprites.get(state.getIndex()).get(frame);
		}
	}

	public static Image getFrontTitleImage()
	{
		return getInstance().frontTitleImage;
	}

	public static Image getBackTitleImage()
	{
		return getInstance().backTitleImage;
	}

	public static Image getFieldBackground()
	{
		return getInstance().fieldBackground;
	}

	public static Image getPauseImage()
	{
		return getInstance().pauseImage;
	}

	public static Image getPowerupImage()
	{
		return getInstance().powerupImage;
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
