package model;

public abstract class Entity implements IRenderable
{
	protected double x, y;
	protected double nextX, nextY;
	protected int z;
	protected int speed;
	protected boolean isDestroyed, isDestroyedInNextState;
	protected Field field;
	
	public Entity(Field field, double x, double y, int speed)
	{
		this.field = field;
		this.field.addEntity(this);
		
		this.x = this.nextX = x;
		this.y = this.nextY = y;
		this.speed = speed;
		this.isDestroyed = this.isDestroyedInNextState = false;
		
		calculateNextState();
	}

	abstract public void update();
	
	abstract protected void calculateNextState();
	
	protected boolean move()
	{
		if(isDestroyed)
			return false;
		x = nextX;
		y = nextY;
		isDestroyed = isDestroyedInNextState;
		calculateNextState();
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

	public int getSpeed()
	{
		return speed;
	}
	
	
}
