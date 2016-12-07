package model;

public class KnightEnemy extends Enemy<KnightDirection>
{

	public KnightEnemy(Field field, double x, double y, int speed, int spriteDelay, int directionChangeDelay)
	{
		super(field, x, y, speed, spriteDelay, directionChangeDelay);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void calculateNextState()
	{
		super.calculateNextState();
		//TODO change x and y according to moving "phase"
		nextX = x + direction.getDx();
		nextY = y + direction.getDy();
	}
}
