package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;

public class InputUtility
{
	private static InputUtility instance = new InputUtility();
	
	private List<KeyCode> keysPressed, keysHeld;
	
	private InputUtility()
	{
		keysPressed = new ArrayList<>();
		keysHeld = new ArrayList<>();
	}

	public static InputUtility getInstance()
	{
		return instance;
	}
	
	public void handleKeyPress(KeyCode code)
	{
		if(!keysHeld.contains(code))
		{
			keysPressed.add(code);
			keysHeld.add(code);
		}
		else
		{
			keysPressed.remove(code);
		}
	}
	
	public void handleKeyRelease(KeyCode code)
	{
		keysPressed.remove(code);
		keysHeld.remove(code);
	}
}
