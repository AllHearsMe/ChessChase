package model;

import util.Config;

public abstract class Enemy<D extends IDirection> extends Entity
{
	protected IDirection direction, nextDirection;
	protected int directionChangeDelay, directionChangeDelayCounter;
	protected int age, lifeSpan;
	
	protected static boolean isPaused = false;

	public Enemy(Field field, double x, double y, int speed, double drawX, double drawY, double hitH, double hitW, int directionChangeDelay, int lifeSpan)
	{
		super(field, x, y, speed, 2, drawX, drawY, hitH, hitW);
		this.direction = this.nextDirection = getClosestDirection();
		this.directionChangeDelay = this.directionChangeDelayCounter = directionChangeDelay;
		this.lifeSpan = lifeSpan;
		this.age = 0;
	}
	
	@Override
	protected boolean move()
	{
		if(!super.move()) return false;
		direction = nextDirection;
		return true;
	}

	@Override
	public int getZ()
	{
		return 2;
	}

	@Override
	public void update()
	{
		if(isDestroyed || isPaused) return;
		if(move())
		{
			//Maybe do something?
		}
	}

	@Override
	protected void calculateNextState()
	{
		switch(state)
		{
			case RUNNING:
				spriteCounter++;
				if(spriteCounter == getTotalSprites())
				{
					spriteCounter = 0;
				}
				double phase = (double) directionChangeDelayCounter / (double) directionChangeDelay;
				nextX = x + direction.getDx(phase) * speed;
				nextY = y + direction.getDy(phase) * speed;
				
				if (directionChangeDelayCounter <= 0)
				{
					directionChangeDelayCounter = directionChangeDelay;
					nextDirection = getClosestDirection();
				}
				directionChangeDelayCounter--;
				break;
			case IDLE:
				if(Math.abs(this.x - field.getPlayer().x) <= Config.AWAKEN_RANGE_WIDTH && Math.abs(this.y - field.getPlayer().y) <= Config.AWAKEN_RANGE_HEIGHT)
					state = EntityState.AWAKENING;
				break;
			case SPAWNING:
				spriteCounter++;
				if(spriteCounter == getTotalSprites())
				{
					spriteCounter = 0;
					state = EntityState.IDLE;
				}
				break;
			case AWAKENING:
				spriteCounter++;
				if(spriteCounter == getTotalSprites())
				{
					spriteCounter = 0;
					state = EntityState.RUNNING;
				}
				break;
			case DYING:
				spriteCounter++;
				if(spriteCounter == Config.DYING_DURATION)
				{
					spriteCounter = 0;
					state = EntityState.DEAD;
				}
				break;
			default:
				break;
		}
		
		
	}
	
	abstract protected int getTotalSprites();

	@Override
	protected void updateSprite()
	{
		//TODO
	}
	
	private double calculateNewDistance(IDirection dir)
	{
		return Math.abs(this.x + dir.getDx(1) * this.speed * this.directionChangeDelay - field.getPlayer().getX()) + Math.abs(this.y + dir.getDy(1) * this.speed * this.directionChangeDelay - field.getPlayer().getY());
	}
	
	//get all directions, find one that brings this closest to player
	private IDirection getClosestDirection()
	{
		double minDistance = Double.MAX_VALUE;
		IDirection minDir = direction.getClass().getEnumConstants()[0];
		for(IDirection dir : direction.getClass().getEnumConstants())
		{
			double temp = calculateNewDistance(dir); 
			if(temp < minDistance)
			{
				minDir = dir;
				minDistance = temp;
			}
		}
		return minDir;
	}
}
