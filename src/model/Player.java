package model;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class Player extends Entity
{
	protected int dx, dy;
	
	public Player(Field field, double x, double y)
	{
		super(field, x, y, Config.PLAYER_SPEED, Config.FRAME_DELAY, Config.PLAYER_HITBOX_X, Config.PLAYER_HITBOX_Y, Config.PLAYER_HITBOX_W, Config.PLAYER_HITBOX_H);
		this.dx = this.dy = 0;
		this.state = EntityState.IDLE;
	}

	@Override
	public int getZ()
	{
		return 1;
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		DrawingUtility.drawPlayer(gc, this, field);
	}
	
	@Override
	public void update()
	{
		if(isDestroyed) return;
		if(move())
		{
			spriteDelayCounter++;
			if (spriteDelayCounter >= spriteDelay)
			{
				spriteDelayCounter = 0;
				spriteCounter++;
			}
			
			switch(state)
			{
				case RUNNING:
					if(dx == 0 && dy == 0)
					{
						state = EntityState.IDLE;
						spriteDelayCounter = 0;
						spriteCounter = 0;
					}
					break;
				case DYING:
					if(spriteDelayCounter <= 0 && spriteCounter >= getTotalSprites())
						isDestroyed = true;
					break;
				case IDLE:
					if(dx != 0 || dy != 0)
					{
						state = EntityState.RUNNING;
						spriteDelayCounter = 0;
						spriteCounter = 0;
					}
					break;
				default:
					state = EntityState.IDLE;
					break;
			}
			
			if(field.checkLoseCondition() && state != EntityState.DYING)
			{
				state = EntityState.DYING;
				spriteDelayCounter = 0;
				spriteCounter = 0;
			}

			if(spriteCounter >= getTotalSprites()) spriteCounter = 0;
		}
	}
	
	@Override
	protected void calculateNextPosition()
	{
		nextX = x + dx * speed;
		nextY = y + dy * speed;
	}

	public int getDx()
	{
		return dx;
	}

	public void setDx(int dx)
	{
		this.dx = (dx > 1) ? 1 : (dx < -1) ? -1 : dx;
	}

	public int getDy()
	{
		return dy;
	}

	public void setDy(int dy)
	{
		this.dy = (dy > 1) ? 1 : (dy < -1) ? -1 : dy;
	}

	@Override
	protected int getTotalSprites()
	{
		switch(state)
		{
			case RUNNING:
				return Config.RUNNING_FRAMES;
			case DYING:
				return Config.DYING_FRAMES;
			default:
				return 1;
		}
	}
}
