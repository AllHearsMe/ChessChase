/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import model.EntityState;

public class ResourceLoader
{
	private static ResourceLoader instance = new ResourceLoader();
	
	private List<List<Image>> playerSprites, pawnSprites, rookSprites, knightSprites, bishopSprites, queenSprites, kingSprites;
	private Image fieldBackground, pauseImage, powerupImage, frontTitleImage, backTitleImage;
	
	private AudioClip zaWarudoSound, tripleAccelSound, gameOverSound, menuBGM, gameBGM;
	
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
	
	public static void loadResources() throws ResourceMissingException
	{
		getInstance();
		
		fillSprites(instance.playerSprites, Config.PLAYER_PATH);
		fillSprites(instance.pawnSprites, Config.PAWN_PATH);
		fillSprites(instance.rookSprites, Config.ROOK_PATH);
		fillSprites(instance.knightSprites, Config.KNIGHT_PATH);
		fillSprites(instance.bishopSprites, Config.BISHOP_PATH);
		fillSprites(instance.queenSprites, Config.QUEEN_PATH);
		fillSprites(instance.kingSprites, Config.KING_PATH);
		
		try
		{
			instance.fieldBackground = new Image(getSystemResourcePath(Config.FIELD_BACKGROUND_PATH));
			instance.pauseImage = new Image(getSystemResourcePath(Config.PAUSE_IMAGE_PATH));
			instance.powerupImage = new Image(getSystemResourcePath(Config.POWERUP_PATH));
			instance.frontTitleImage = new Image(getSystemResourcePath(Config.FRONT_TITLE_PATH));
			instance.backTitleImage = new Image(getSystemResourcePath(Config.BACK_TITLE_PATH));
			
			instance.menuBGM = new AudioClip(getSystemResourcePath(Config.MENU_BGM_PATH));
			instance.gameBGM = new AudioClip(getSystemResourcePath(Config.GAME_BGM_PATH));
			instance.zaWarudoSound = new AudioClip(getSystemResourcePath(Config.ZA_WARUDO_SOUND_PATH));
			instance.tripleAccelSound = new AudioClip(getSystemResourcePath(Config.TRIPLE_ACCEL_SOUND_PATH));
			instance.gameOverSound = new AudioClip(getSystemResourcePath(Config.GAME_OVER_SOUND_PATH));
		}
		catch(ResourceMissingException e)
		{
			throw e;
		}
	}
	
	private static String getSystemResourcePath(String path) throws ResourceMissingException
	{
		URL url = ClassLoader.getSystemResource(path);
		if(url == null) throw new ResourceMissingException();
		return url.toString();
	}
	
	private static void fillSprites(List<List<Image>> list, String path) throws ResourceMissingException
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
				Image image = new Image(getSystemResourcePath(path + Config.getSpriteFileName(state)));
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

	public static AudioClip getZaWarudoSound()
	{
		return getInstance().zaWarudoSound;
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
