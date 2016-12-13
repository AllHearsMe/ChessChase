package model;

public enum EntityState
{
	CREATED(-1),
	SPAWNING(0),
	IDLE(1),
	AWAKENING(2),
	RUNNING(3),
	DYING(4);
	
	private int index;
	
	private EntityState(int index)
	{
		this.index = index;
	}

	public int getIndex()
	{
		return index;
	}
}

