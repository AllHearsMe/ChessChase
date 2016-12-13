/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class BishopEnemy extends Enemy<BishopDirection>
{
	public BishopEnemy(Field field, double x, double y)
	{
		super(field, x, y, Config.BISHOP_SPEED, Config.BISHOP_HITBOX_X, Config.BISHOP_HITBOX_Y, Config.BISHOP_HITBOX_H, Config.BISHOP_HITBOX_W,
				BishopDirection.values()[0], Config.BISHOP_DIRECTION_CHANGE_DELAY, Config.BISHOP_LIFESPAN);
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawBishop(gc, this, field);
	}
	
	@Override
	protected int getTotalSprites()
	{
		switch (state)
		{
			case SPAWNING:
				return Config.SPAWNING_FRAMES;
			case AWAKENING:
				return Config.AWAKENING_FRAMES;
			case RUNNING:
				return Config.RUNNING_FRAMES;
			case DYING:
				return Config.DYING_FRAMES;
			default:
				return 1;
		}
	}
}
