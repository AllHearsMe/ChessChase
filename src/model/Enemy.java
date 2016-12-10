package model;

import util.Config;

public abstract class Enemy<D extends IDirection> extends Entity
{
	protected IDirection direction;
	protected int directionChangeDelay, directionChangeDelayCounter;
	protected int age, lifespan;
	
	protected static boolean isPaused = false;
	
	public Enemy(Field field, double x, double y, double speed, double drawX, double drawY, double hitH, double hitW, IDirection direction,
			int directionChangeDelay, int lifeSpan)
	{
		super(field, x, y, speed, Config.FRAME_DELAY, drawX, drawY, hitH, hitW);
		this.direction = direction;
		this.direction = getClosestDirection();
		this.directionChangeDelay = directionChangeDelay;
		this.directionChangeDelayCounter = 0;
		this.lifespan = lifeSpan;
		this.age = 0;
	}
	
	@Override
	public int getZ()
	{
		return 2;
	}
	
	@Override
	public void update()
	{
		if (isDestroyed || isPaused) return;
		if (move())
		{
			spriteDelayCounter++;
			if (spriteDelayCounter >= spriteDelay)
			{
				spriteDelayCounter = 0;
				spriteCounter++;
			}
			
			switch (state)
			{
				case CREATED:
					state = EntityState.SPAWNING;
					spriteDelayCounter = 0;
					spriteCounter = 0;
					break;
				case SPAWNING:
					if (spriteDelayCounter <= 0 && spriteCounter >= getTotalSprites()) state = EntityState.IDLE;
					break;
				case IDLE:
					if (Math.abs(this.x - field.getPlayer().x) <= Config.AWAKEN_RANGE_WIDTH
							&& Math.abs(this.y - field.getPlayer().y) <= Config.AWAKEN_RANGE_HEIGHT)
					{
						state = EntityState.AWAKENING;
						spriteDelayCounter = 0;
						spriteCounter = 0;
					}
					break;
				case AWAKENING:
					if (spriteDelayCounter <= 0 && spriteCounter >= getTotalSprites())
					{
						state = EntityState.RUNNING;
						directionChangeDelayCounter = 1;
						direction = getClosestDirection();
						calculateNextPosition();
					}
					break;
				case RUNNING:
					directionChangeDelayCounter++;
					if (directionChangeDelayCounter >= directionChangeDelay)
					{
						directionChangeDelayCounter = 0;
						direction = getClosestDirection();
					}
					age++;
					if (age >= lifespan)
					{
						state = EntityState.DYING;
						spriteDelayCounter = 0;
						spriteCounter = 0;
					}
					break;
				case DYING:
					if (spriteDelayCounter <= 0 && spriteCounter >= getTotalSprites()) isDestroyed = true;
					break;
			}
			
			if (spriteCounter >= getTotalSprites()) spriteCounter = 0;
		}
	}
	
	@Override
	protected void calculateNextPosition()
	{
		if (state == EntityState.RUNNING)
		{
			double phase = (double) directionChangeDelayCounter / (double) directionChangeDelay;
			nextX = x + direction.getDx(phase) * speed;
			nextY = y + direction.getDy(phase) * speed;
		}
	}
	
	private double calculateNewDistance(IDirection dir)
	{
		return Math.abs(this.x + dir.getDx(1) * this.speed * this.directionChangeDelay - field.getPlayer().getX())
				+ Math.abs(this.y + dir.getDy(1) * this.speed * this.directionChangeDelay - field.getPlayer().getY());
	}
	
	// get all directions, find one that brings this closest to player
	private IDirection getClosestDirection()
	{
		double minDistance = Double.MAX_VALUE;
		IDirection minDir = direction.getClass().getEnumConstants()[0];
		for (IDirection dir : direction.getClass().getEnumConstants())
		{
			double temp = calculateNewDistance(dir);
			if (temp < minDistance)
			{
				minDir = dir;
				minDistance = temp;
			}
		}
		return minDir;
	}
}
