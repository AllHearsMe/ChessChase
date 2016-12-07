package model;

public abstract class Entity<D extends IDirection> implements IRenderable
{
	protected int x, y, z;
	protected int nextX, nextY;
	protected D direction, nextDirection;
	protected int speed;
	protected boolean visible;
	protected boolean isDestroyed, toBeDestroyed;
	private int movingDelay, movingDelayCounter;
	Field field;
	
	
}
