package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder
{
	private static RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> renderables;
	private static Comparator<IRenderable> comparator = (IRenderable o1, IRenderable o2) -> o1.getZ() > o2.getZ() ? 1 : -1;
	
	private RenderableHolder()
	{
		renderables = new ArrayList<>();
	}

	public static RenderableHolder getInstance()
	{
		return instance;
	}
	
	public List<IRenderable> getRenderables()
	{
		return renderables;
	}

	public void add(IRenderable r)
	{
		renderables.add(r);
		sort();
	}
	
	public void sort()
	{
		renderables.sort(comparator);
	}
}
