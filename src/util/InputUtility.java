package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;

public class InputUtility
{
	private static InputUtility instance = new InputUtility();
	
	private List<KeyCode> keysTriggered, keysPressed;
	
	private InputUtility()
	{
		keysTriggered = new ArrayList<>();
		keysPressed = new ArrayList<>();
	}

	public static InputUtility getInstance()
	{
		return instance;
	}
	
	public static void handleKeyPress(KeyCode code)
	{
		getInstance();
		if(!instance.keysPressed.contains(code))
		{
			instance.keysTriggered.add(code);
			instance.keysPressed.add(code);
		}
	}
	
	public static void handleKeyRelease(KeyCode code)
	{
		getInstance();
		instance.keysTriggered.remove(code);
		instance.keysPressed.remove(code);
	}
	
	public static boolean getKeyTriggered(KeyCode code)
	{
		return getInstance().keysTriggered.contains(code);
	}
	
	public static boolean getKeyPressed(KeyCode code)
	{
		return getInstance().keysPressed.contains(code);
	}
	
	public static void reset()
	{
		getInstance();
		instance.keysPressed.clear();
		instance.keysTriggered.clear();
	}
	
	public static void postUpdate()
	{
		getInstance().keysTriggered.clear();
	}
}
