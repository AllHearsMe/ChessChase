package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class RenderableHolder
{
	private static RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> renderables;
	private static Comparator<IRenderable> comparator = (IRenderable o1, IRenderable o2) -> (o1.getZ() == o2.getZ()) ? 0 : o1.getZ() > o2.getZ() ? 1 : -1;
	
	private RenderableHolder()
	{
		renderables = new ArrayList<>();
	}

	private static RenderableHolder getInstance()
	{
		return instance;
	}
	
	public static List<IRenderable> getRenderables()
	{
		return getInstance().renderables;
	}

	public static void add(IRenderable r)
	{
		getInstance().renderables.add(r);
		sort();
	}
	
	public static void sort()
	{
		getInstance().renderables.sort(comparator);
	}
	
	public static void draw(GraphicsContext gc)
	{
		for (IRenderable r : getInstance().renderables)
			if(r.isVisible()) r.draw(gc);
	}
}
