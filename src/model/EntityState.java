/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

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

