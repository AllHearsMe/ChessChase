package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class QueenEnemy extends Enemy<NormalDirection>
{
	
	public QueenEnemy(Field field, double x, double y)
	{
		super(field, x, y, Config.QUEEN_SPEED, Config.QUEEN_HITBOX_X, Config.QUEEN_HITBOX_Y, Config.QUEEN_HITBOX_H, Config.QUEEN_HITBOX_W,
				NormalDirection.values()[0], Config.QUEEN_DIRECTION_CHANGE_DELAY, Config.QUEEN_LIFESPAN);
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawQueen(gc, this, field);
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
