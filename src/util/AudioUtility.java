package util;

import javafx.scene.media.AudioClip;

public class AudioUtility
{
	private static AudioUtility instance = new AudioUtility();
	private AudioClip currentBGM, currentSFX;
	
	private AudioUtility()
	{
		currentBGM = ResourceLoader.getMenuBGM();
		currentSFX = ResourceLoader.getGameOverSound();
	}

	private static AudioUtility getInstance()
	{
		return instance;
	}
	
	public static void playBGM(AudioClip bgm)
	{
		getInstance();
		if(instance.currentBGM.isPlaying()) AudioUtility.stopBGM();
		instance.currentBGM = bgm;
		instance.currentBGM.setCycleCount(AudioClip.INDEFINITE);
		instance.currentBGM.play();
	}
	
	public static void playMenuBGM()
	{
		AudioUtility.playBGM(ResourceLoader.getMenuBGM());
	}
	
	public static void playGameBGM()
	{
		AudioUtility.playBGM(ResourceLoader.getGameBGM());
	}
	
	public static void stopBGM()
	{
		getInstance().currentBGM.stop();
	}
	
	public static void playSoundEffect(AudioClip sound)
	{
		getInstance();
		if(instance.currentSFX.isPlaying()) AudioUtility.stopSoundEffect();
		instance.currentSFX = sound;
		instance.currentSFX.play();
	}
	
	public static void stopSoundEffect()
	{
		getInstance().currentSFX.stop();
	}

	public static void playGameOverSound()
	{
		AudioUtility.stopBGM();
		AudioUtility.playSoundEffect(ResourceLoader.getGameOverSound());
	}
	
	public static void playBurstLinkSound()
	{
		AudioUtility.playSoundEffect(ResourceLoader.getBurstLinkSound());
	}
	
	public static void playTripleAccelSound()
	{
		AudioUtility.playSoundEffect(ResourceLoader.getTripleAccelSound());
	}
}
