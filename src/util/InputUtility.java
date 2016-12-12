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

	private static InputUtility getInstance()
	{
		return instance;
	}
	
	public static synchronized void handleKeyPress(KeyCode code)
	{
		getInstance();
		if(!instance.keysPressed.contains(code))
		{
			instance.keysTriggered.add(code);
			instance.keysPressed.add(code);
		}
	}
	
	public static synchronized void handleKeyRelease(KeyCode code)
	{
		getInstance();
		instance.keysTriggered.remove(code);
		instance.keysPressed.remove(code);
	}
	
	public static synchronized boolean getKeyTriggered(KeyCode code)
	{
		return getInstance().keysTriggered.contains(code);
	}
	
	public static synchronized boolean getKeyPressed(KeyCode code)
	{
		return getInstance().keysPressed.contains(code);
	}
	
	public static synchronized void reset()
	{
		getInstance();
		instance.keysPressed.clear();
		instance.keysTriggered.clear();
	}
	
	public static synchronized void postUpdate()
	{
		getInstance().keysTriggered.clear();
	}
}
