package model;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable
{
	public boolean isVisible();
	public int getZ();
	public void draw(GraphicsContext gc);
}
