package model;

public enum EntityState
{
	CREATED("Created", -1),
	SPAWNING("Spawning", 0),
	IDLE("Idle", 1),
	AWAKENING("Awakening", 2),
	RUNNING("Running", 3),
	DYING("Dying", 4);
	
	private String name;
	private int index;
	
	private EntityState(String name, int index)
	{
		this.name = name;
		this.index = index;
	}

	public int getIndex()
	{
		return index;
	}

	public String getName()
	{
		return name;
	}
}

