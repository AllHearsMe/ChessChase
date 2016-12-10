package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class Field implements IRenderable
{
	private double x, y;
	private double width, height;

	private List<Enemy<?>> enemies;
	private Player player;
	
	public Field(double width, double height)
	{
		enemies = new ArrayList<>();
		this.x = this.y = 0;
		this.width = width;
		this.height = height;
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
	public void draw(GraphicsContext gc)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void updateFieldState()
	{
		player.update();
		for(Enemy<?> e : enemies)
			e.update();
		
		for(Iterator<Enemy<?>> i = enemies.iterator(); i.hasNext();)
		{
			if(i.next().isDestroyed())
				i.remove();
		}
		if(player.isDestroyed())
		{
			//TODO game ends
		}
	}
	
	public void addEnemy(Enemy<?> e)
	{
		enemies.add(e);
	}
	
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
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

	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public boolean checkLoseCondition()
	{
		for(Enemy<?> e : enemies)
		{
			if(Entity.checkCollision(player, e))
				return true;
		}
		return false;
	}
	
	public double boundX(double x)
	{
		return x < 0 ? 0 : x > width ? width : x;
	}
	
	public double boundY(double y)
	{
		return y < 0 ? 0 : y > height ? height : y;
	}
}
