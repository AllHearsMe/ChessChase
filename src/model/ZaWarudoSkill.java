package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class ZaWarudoSkill extends SpecialSkill
{
	public ZaWarudoSkill()
	{
		super(Config.BURST_LINK_DURATION, () -> Enemy.isPaused = true, () -> Enemy.isPaused = false);
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
	
	@Override
	public boolean needsUpdating()
	{
		return isActive;
	}
}
