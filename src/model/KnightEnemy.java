package model;

public class KnightEnemy extends Enemy<KnightDirection>
{

	public KnightEnemy(Field field, double x, double y)
	{
		//TODO add speed, directionChangeDelay, and lifeSpan
		super(field, x, y, 0, 0, 0, 0, 0, 0, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getTotalSprites()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
