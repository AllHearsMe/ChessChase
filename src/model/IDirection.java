package model;

public interface IDirection
{
	//phase -> [0, 1); phase = 1 is complete displacement
	public double getDx(double phase);
	public double getDy(double phase);
	public int getFacing();
}
