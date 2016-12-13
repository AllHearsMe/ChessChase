/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

import javafx.scene.canvas.GraphicsContext;
import util.DrawingUtility;

public class PauseEffect implements IRenderable
{
	private boolean isPaused = false;
	
	public PauseEffect()
	{
		RenderableHolder.add(this);
	}
	
	public void togglePaused()
	{
		this.isPaused = !this.isPaused;
	}

	@Override
	public boolean isVisible()
	{
		return isPaused;
	}
	
	public boolean isPaused()
	{
		return isPaused;
	}
	
	@Override
	public int getZ()
	{
		return 100;
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawPauseEffect(gc);
	}
	
}
