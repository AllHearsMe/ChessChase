/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class ZaWarudoSkill extends SpecialSkill
{
	public ZaWarudoSkill()
	{
		super(Config.ZA_WARUDO_DURATION, () -> Enemy.isPaused = true, () -> Enemy.isPaused = false);
	}

	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawZaWarudoEffect(gc);
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
