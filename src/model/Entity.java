package model;

public abstract class Entity implements IRenderable
{
	protected double x, y;
	protected double nextX, nextY;
	protected int z;
	protected double speed;
	protected boolean isDestroyed;
	protected Field field;

	protected int spriteDelay, spriteDelayCounter;
	protected int spriteCounter;
	protected EntityState state;
	protected double hitX, hitY, hitW, hitH;
	
	public Entity(Field field, double x, double y, double speed, int spriteDelay, double hitX, double hitY, double hitH, double hitW)
	{
		this.field = field;
		RenderableHolder.getInstance().add(this);
		
		this.x = this.nextX = x;
		this.y = this.nextY = y;
		this.speed = speed;
		this.isDestroyed = false;

		this.spriteDelay = spriteDelay;
		this.spriteDelayCounter = 0;
		this.spriteCounter = 0;
		this.state = EntityState.CREATED;
		this.hitX = hitX;
		this.hitY = hitY;
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
		x = field.boundX(nextX, hitW);
		y = field.boundY(nextY, hitH);
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

	public double getDrawX()
	{
		return x - hitX;
	}

	public double getDrawY()
	{
		return y - hitY;
	}

	public double getHitW()
	{
		return hitW;
	}

	public double getHitH()
	{
		return hitH;
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
	
	public EntityState getState()
	{
		return state;
	}
	
	public int getSpriteCounter()
	{
		return spriteCounter;
	}

	abstract protected int getTotalSprites();
	
	public static boolean checkCollision(Entity e1, Entity e2)
	{
		return e1.x < e2.x + e2.hitW && e1.x + e1.hitW > e2.x && e1.y < e2.y + e2.hitH && e1.y + e1.hitH > e2.y;
	}
}
