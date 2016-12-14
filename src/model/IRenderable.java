/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable
{
	public boolean isVisible();
	public int getZ();
	public void draw(GraphicsContext gc);
}
