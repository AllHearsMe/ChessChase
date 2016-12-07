package model;

public abstract class Enemy<D extends IDirection> extends Entity
{
	protected D direction, nextDirection;

	public Enemy(Field field, double x, double y, int speed, int spriteDelay)
	{
		super(field, x, y, speed, spriteDelay);
		this.direction = this.nextDirection = direction;
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

	@Override
	protected void changeSprite()
	{
		// TODO Auto-generated method stub
		
	}
	
}
