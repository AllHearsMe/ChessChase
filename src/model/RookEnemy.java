package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class RookEnemy extends Enemy<RookDirection>
{
	
	public RookEnemy(Field field, double x, double y)
	{
		super(field, x, y, Config.ROOK_SPEED, Config.ROOK_HITBOX_X, Config.ROOK_HITBOX_Y, Config.ROOK_HITBOX_H, Config.ROOK_HITBOX_W,
				RookDirection.values()[0], Config.ROOK_DIRECTION_CHANGE_DELAY, Config.ROOK_LIFESPAN);
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawRook(gc, this, field);
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
