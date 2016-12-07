package model;

public class Enemy<D extends IDirection> extends Entity
{
	protected D direction, nextDirection;

	public Enemy(Field field, double x, double y, int speed)
	{
		super(field, x, y, speed);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void calculateNextState()
	{
		// TODO Auto-generated method stub
		
	}
	
}
