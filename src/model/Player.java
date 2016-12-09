package model;

import util.Config;

public class Player extends Entity
{
	protected int dx, dy;
	
	public Player(Field field, double x, double y)
	{
		super(field, x, y, 10, 2, Config.PLAYER_HITBOX_X, Config.PLAYER_HITBOX_Y, Config.PLAYER_HITBOX_W, Config.PLAYER_HITBOX_H);
		this.dx = this.dy = 0;
		this.state = EntityState.IDLE;
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
		System.out.println("State: " + state.getName() + ", Sprite: " + spriteCounter + ", Pos: (" + this.x + ", " + this.y + ")");
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
			
			if(field.checkLoseCondition())
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
		nextY = y + dx * speed;
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
		return 5;
//		return (state == EntityState.DYING) ? Config.DYING_DURATION : ResourceLoader.getInstance().getPlayerTotalSprites(state);
	}
	
	public static void main(String[] args)
	{
		Field field = new Field(1000, 1000);
		
		Player player = new Player(field, 100, 100);
		field.setPlayer(player);
		
		while(true)
		{
			double r = Math.random();
			player.setDx(r < 0.2 ? 1 : r >= 0.8 ? -1 : 0);
			r = Math.random();
			player.setDy(r < 0.2 ? 1 : r >= 0.8 ? -1 : 0);
			field.updateFieldState();
			player.draw();
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
