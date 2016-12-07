package model;

import java.util.ArrayList;
import java.util.List;

public class Field implements IRenderable
{
	private double x, y;
	private double width, height;

	private List<Entity> entities;
	
	public Field()
	{
		entities = new ArrayList<>();
		this.x = this.y = 0;
		//TODO initialize width and height
	}

	@Override
	public boolean isVisible()
	{
		return true;
	}
	
	@Override
	public int getZ()
	{
		return 0;
	}
	
	@Override
	public void render()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	public double getWidth()
	{
		return width;
	}

	public double getHeight()
	{
		return height;
	}
}
