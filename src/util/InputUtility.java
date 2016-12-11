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
	
	public void handleKeyPress(KeyCode code)
	{
		if(!keysPressed.contains(code))
		{
			keysTriggered.add(code);
			keysPressed.add(code);
		}
	}
	
	public void handleKeyRelease(KeyCode code)
	{
		keysTriggered.remove(code);
		keysPressed.remove(code);
	}
	
	public boolean getKeyTriggered(KeyCode code)
	{
		return keysTriggered.contains(code);
	}
	
	public boolean getKeyPressed(KeyCode code)
	{
		return keysPressed.contains(code);
	}
	
	public void postUpdate()
	{
		keysTriggered.clear();
	}
}
