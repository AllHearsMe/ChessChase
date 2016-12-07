package model;

public enum BishopDirection implements IDirection
{
	NORTHEAST(1, -1),
	NORTHWEST(-1, -1),
	SOUTHWEST(-1, 1),
	SOUTHEAST(1, 1);
	
	private double dx, dy;

	private BishopDirection(double dx, double dy)
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
