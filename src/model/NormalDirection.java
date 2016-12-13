/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

public enum NormalDirection implements IDirection
{
	EAST(1, 0),
	NORTHEAST(1, -1),
	NORTH(0, -1),
	NORTHWEST(-1, -1),
	WEST(-1, 0),
	SOUTHWEST(-1, 1),
	SOUTH(0, 1),
	SOUTHEAST(1, 1);
	
	private double dx, dy;

	private NormalDirection(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

	public double getDx(double phase)
	{
		return dx;
	}

	public double getDy(double phase)
	{
		return dy;
	}
	
	public int getFacing()
	{
		return dx < 0 ? -1 : 1;
	}
}
