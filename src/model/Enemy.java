package model;

import util.Config;

public abstract class Enemy<D extends IDirection> extends Entity
{
	protected IDirection direction;
	protected int directionChangeDelay, directionChangeDelayCounter;
	protected int age, lifespan;
	protected static double multiplier = 1;
	protected static double divider = 1;
	protected static boolean isPaused = false;
	
	public Enemy(Field field, double x, double y, double speed, double drawX, double drawY, double hitH, double hitW, IDirection direction,
			int directionChangeDelay, int lifeSpan)
	{
		super(field, x, y, speed, Config.FRAME_DELAY, drawX, drawY, hitW, hitH);
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
		return 1;
	}
	
	@Override
	public void update()
	{
		if (isDestroyed || isPaused) return;
		if (move())
		{
			spriteDelayCounter++;
			if (spriteDelayCounter >= spriteDelay * divider)
			{
				spriteDelayCounter = 0;
				spriteCounter++;
			}
			
			switch (state)
			{
				case CREATED:
					state = EntityState.SPAWNING;
					resetSprite();
					break;
				case SPAWNING:
					if (isChangingState()) state = EntityState.IDLE;
					break;
				case IDLE:
					if (isPlayerInAwakenRange())
					{
						state = EntityState.AWAKENING;
						resetSprite();
					}
					break;
				case AWAKENING:
					if (isChangingState())
					{
						state = EntityState.RUNNING;
						directionChangeDelayCounter = 1;
						direction = getClosestDirection();
						calculateNextPosition();
					}
					break;
				case RUNNING:
					directionChangeDelayCounter++;
					if (directionChangeDelayCounter >= directionChangeDelay * divider)
					{
						directionChangeDelayCounter = 0;
						direction = getClosestDirection();
					}
					age++;
					if (age >= lifespan)
					{
						state = EntityState.DYING;
						resetSprite();
					}
					break;
				case DYING:
					if (isChangingState()) isDestroyed = true;
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
			nextX = x + direction.getDx(phase) * speed * multiplier / divider;
			nextY = y + direction.getDy(phase) * speed * multiplier / divider;
		}
	}
	
	private double calculateNewDistance(IDirection dir)
	{
		return Math.abs((this.x + this.hitW / 2 + dir.getDx(1) * this.speed * multiplier / divider * this.directionChangeDelay) - (field.getPlayer().getX() + field.getPlayer().getHitW() / 2))
				+ Math.abs((this.y + this.hitH / 2 + dir.getDy(1) * this.speed * multiplier / divider * this.directionChangeDelay) - (field.getPlayer().getY() + field.getPlayer().getHitH() / 2));
	}
	
	private boolean isPlayerInAwakenRange()
	{
		return Math.abs((this.x + this.hitW / 2) - (field.getPlayer().x + field.getPlayer().hitW / 2))
				<= Config.AWAKEN_RANGE_WIDTH + (this.hitW + field.getPlayer().hitW) / 2
				&& Math.abs((this.y + this.hitH / 2) - (field.getPlayer().y + field.getPlayer().hitH / 2))
				<= Config.AWAKEN_RANGE_HEIGHT + (this.hitH + field.getPlayer().hitH) / 2;
	}
	
	private boolean isChangingState()
	{
		return spriteDelayCounter <= 0 && spriteCounter >= getTotalSprites();
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
	
	public IDirection getDirection()
	{
		return direction;
	}
	
	public static void setMultiplier(double multiplier)
	{
		Enemy.multiplier = multiplier;
	}
	
	public static double getMultiplier()
	{
		return multiplier;
	}
	
	public static void setDivider(double divider)
	{
		Enemy.divider = divider;
	}
	
	public static double getDivider()
	{
		return divider;
	}
	
	public static boolean isPaused()
	{
		return isPaused;
	}
	
	public static void setPaused(boolean isPaused)
	{
		Enemy.isPaused = isPaused;
	}
}
