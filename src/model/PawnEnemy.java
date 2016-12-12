package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class PawnEnemy extends Enemy<NormalDirection>
{
	
	public PawnEnemy(Field field, double x, double y)
	{
		super(field, x, y, Config.PAWN_SPEED, Config.PAWN_HITBOX_X, Config.PAWN_HITBOX_Y, Config.PAWN_HITBOX_H, Config.PAWN_HITBOX_W,
				NormalDirection.values()[0], Config.PAWN_DIRECTION_CHANGE_DELAY, Config.PAWN_LIFESPAN);
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawPawn(gc, this, field);
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
