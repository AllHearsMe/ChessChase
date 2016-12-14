/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class KingEnemy extends Enemy<NormalDirection>
{
	
	public KingEnemy(Field field, double x, double y)
	{
		super(field, x, y, Config.KING_SPEED, Config.KING_HITBOX_X, Config.KING_HITBOX_Y, Config.KING_HITBOX_H, Config.KING_HITBOX_W,
				NormalDirection.values()[0], Config.KING_DIRECTION_CHANGE_DELAY, Config.KING_LIFESPAN);
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawKing(gc, this, field);
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
