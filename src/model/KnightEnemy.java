/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class KnightEnemy extends Enemy<KnightDirection>
{
	
	public KnightEnemy(Field field, double x, double y)
	{
		super(field, x, y, Config.KNIGHT_SPEED, Config.KNIGHT_HITBOX_X, Config.KNIGHT_HITBOX_Y, Config.KNIGHT_HITBOX_H, Config.KNIGHT_HITBOX_W,
				KnightDirection.values()[0], Config.KNIGHT_DIRECTION_CHANGE_DELAY, Config.KNIGHT_LIFESPAN);
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawKnight(gc, this, field);
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
