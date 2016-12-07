package model;

public enum KnightDirection implements IDirection
{
	EAST_CCW(2, -1),
	EAST_CW(2, 1),
	NORTH_CCW(-1, -2),
	NORTH_CW(1, -2),
	WEST_CCW(-2, 1),
	WEST_CW(-2, -1),
	SOUTH_CCW(1, 2),
	SOUTH_CW(-1, 2);
	
	private double dx, dy;

	private KnightDirection(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

	public double getDx()
	{
		return dx;
	}

	public double getDy()
	{
		return dy;
	}
}
