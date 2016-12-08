package model;

import util.Config;

public class Player extends Entity
{
	protected int dx, dy;
	
	public Player(Field field, double x, double y, int speed)
	{
		super(field, x, y, speed, 2, Config.PLAYER_HITBOX_X, Config.PLAYER_HITBOX_Y, Config.PLAYER_HITBOX_W, Config.PLAYER_HITBOX_H);
		this.dx = this.dy = 0;
		this.state = EntityState.IDLE;
		this.nextState = EntityState.IDLE;
	}

	@Override
	public int getZ()
	{
		return 1;
	}
	
	@Override
	public void draw()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update()
	{
		if(isDestroyed) return;
		if(move())
		{
			//Maybe do something?
		}
	}
	
	@Override
	protected void calculateNextState()
	{
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return 0;
	}
	
}
