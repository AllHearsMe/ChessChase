package model;

public abstract class Enemy<D extends IDirection> extends Entity
{
	protected IDirection direction, nextDirection;
	protected int directionChangeDelay, directionChangeDelayCounter;
	
	protected static boolean isPaused = false;

	public Enemy(Field field, double x, double y, int speed, int spriteDelay, int directionChangeDelay)
	{
		super(field, x, y, speed, spriteDelay);
		this.direction = this.nextDirection = direction;
		this.directionChangeDelay = this.directionChangeDelayCounter = directionChangeDelay;
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
	public void render()
	{
		// TODO Auto-generated method stub
		
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
		nextX = x + direction.getDx();
		nextY = y + direction.getDy();
		
		if (directionChangeDelayCounter <= 0)
		{
			directionChangeDelayCounter = directionChangeDelay;
			
			double minDistance = calculateNewDistance(direction);
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
	protected void changeSprite()
	{
		// TODO Auto-generated method stub
		
	}
	
	private double calculateNewDistance(IDirection dir)
	{
		return Math.abs(this.x + dir.getDx() * this.speed * this.directionChangeDelay - field.getPlayer().getX()) + Math.abs(this.y + dir.getDy() * this.speed * this.directionChangeDelay - field.getPlayer().getY());
	}
}
