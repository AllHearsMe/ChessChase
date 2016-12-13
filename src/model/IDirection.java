/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

public interface IDirection
{
	//phase -> [0, 1); phase = 1 is complete displacement
	public double getDx(double phase);
	public double getDy(double phase);
	public int getFacing();
}
