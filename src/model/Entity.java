package model;

public abstract class Entity implements IRenderable
{
	protected double x, y;
	protected double nextX, nextY;
	protected int z;
	protected double speed;
	protected boolean isDestroyed, isDestroyedInNextState;
	protected Field field;

	protected int spriteDelay, spriteDelayCounter;
	protected int spriteCounter;
	protected EntityState state, nextState;
	protected double drawX, drawY, hitW, hitH;
	
	public Entity(Field field, double x, double y, double speed, int spriteDelay, double drawX, double drawY, double hitH, double hitW)
	{
		this.field = field;
		RenderableHolder.getInstance().add(this);
		
		this.x = this.nextX = x;
		this.y = this.nextY = y;
		this.speed = speed;
		this.isDestroyed = /*this.isDestroyedInNextState =*/ false;

		this.spriteDelay = spriteDelay;
		this.spriteDelayCounter = 0;
		this.spriteCounter = 0;
		this.state = this.nextState = EntityState.CREATED;
		this.drawX = drawX;
		this.drawY = drawY;
		this.hitH = hitH;
		this.hitW = hitW;
		
		calculateNextPosition();
	}

	abstract public void update();
	
	abstract protected void calculateNextPosition();
	
	protected boolean move()
	{
		if(isDestroyed)
			return false;
		x = nextX;
		y = nextY;
//		isDestroyed = isDestroyedInNextState;
		calculateNextPosition();
		return true;
	}
	
	@Override
	public boolean isVisible()
	{
		return !isDestroyed;
	}
	
	public boolean isSamePosition(Entity other)
	{
		return this.x == other.x && this.y == other.y;
	}

	public boolean isDestroyed()
	{
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed)
	{
		this.isDestroyed = isDestroyed;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getNextX()
	{
		return nextX;
	}

	public double getNextY()
	{
		return nextY;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	
	abstract protected int getTotalSprites();
}
