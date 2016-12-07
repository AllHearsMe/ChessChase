package model;

public class Player extends Entity
{
	
	public Player(Field field, double x, double y, int speed, int spriteDelay)
	{
		super(field, x, y, speed, spriteDelay);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getZ()
	{
		return 1;
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
