package model;

public enum KnightDirection implements IDirection
{
	//To be divided by 3.0 in constructor
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
		this.dx = dx / 3.0;
		this.dy = dy / 3.0;
	}

	public double getDx(double phase)
	{
		if(phase < (2.0 / 3.0)) return (Math.abs(dx) > Math.abs(dy)) ? dx : 0;
		else if(phase < 1) return (Math.abs(dx) > Math.abs(dy)) ? 0 : dx;
		else return dx;
	}

	public double getDy(double phase)
	{
		if(phase < (2.0 / 3.0)) return (Math.abs(dy) > Math.abs(dx)) ? dy : 0;
		else if(phase < 1) return (Math.abs(dy) > Math.abs(dx)) ? 0 : dy;
		else return dy;
	}
}
