package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class BurstLinkSkill extends SpecialSkill
{
	public BurstLinkSkill()
	{
		super(Config.BURST_LINK_DURATION, 0, 0, () -> Enemy.isPaused = true, () -> Enemy.isPaused = false);
	}

	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawBurstLinkEffect(gc);
	}
	
	@Override
	public boolean isVisible()
	{
		return isActive;
	}
}
