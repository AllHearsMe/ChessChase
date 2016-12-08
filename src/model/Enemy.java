package model;

public abstract class Enemy<D extends IDirection> extends Entity
{
	protected IDirection direction, nextDirection;
	protected int directionChangeDelay, directionChangeDelayCounter;
	protected int age, lifeSpan;
	
	protected static boolean isPaused = false;

	public Enemy(Field field, double x, double y, int speed, int spriteDelay, double drawX, double drawY, double hitH, double hitW, int directionChangeDelay, int lifeSpan)
	{
		super(field, x, y, speed, spriteDelay, drawX, drawY, hitH, hitW);
		this.direction = this.nextDirection = direction;
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
		double phase = (double) directionChangeDelayCounter / (double) directionChangeDelay;
		nextX = x + direction.getDx(phase) * speed;
		nextY = y + direction.getDy(phase) * speed;
		
		if (directionChangeDelayCounter <= 0)
		{
			directionChangeDelayCounter = directionChangeDelay;
			
			double minDistance = calculateNewDistance(direction);
			
			//get all directions, find one that brings this closest to player
			for(IDirection dir : direction.getClass().getEnumConstants())
			{
				double temp = calculateNewDistance(dir); 
				if(temp < minDistance)
				{
					nextDirection = dir;
					minDistance = temp;
				}
			}
		}
		directionChangeDelayCounter--;
	}

	@Override
	protected void updateSprite()
	{
		//TODO
	}
	
	private double calculateNewDistance(IDirection dir)
	{
		return Math.abs(this.x + dir.getDx(1) * this.speed * this.directionChangeDelay - field.getPlayer().getX()) + Math.abs(this.y + dir.getDy(1) * this.speed * this.directionChangeDelay - field.getPlayer().getY());
	}
	
	
}
