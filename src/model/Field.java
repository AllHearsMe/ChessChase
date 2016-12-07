package model;

import java.util.ArrayList;
import java.util.List;

public class Field implements IRenderable
{
	private double x, y;
	private double width, height;

	private List<Enemy<?>> enemies;
	private Player player;
	
	public Field()
	{
		enemies = new ArrayList<>();
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
	
	public void addEnemy(Enemy<?> e)
	{
		enemies.add(e);
	}
	
	public double getWidth()
	{
		return width;
	}

	public double getHeight()
	{
		return height;
	}

	public List<Enemy<?>> getEnemies()
	{
		return enemies;
	}

	public Player getPlayer()
	{
		return player;
	}
}
