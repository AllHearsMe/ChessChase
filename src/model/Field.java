/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class Field implements IRenderable
{
	private double x, y;
	private double width, height;

	private List<Enemy<?>> enemies;
	private Player player;
	private SpecialSkill skill;
	
	public Field(double width, double height)
	{
		enemies = new ArrayList<>();
		this.x = this.y = 0;
		this.width = width;
		this.height = height;
		RenderableHolder.add(this);
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
		DrawingUtility.drawField(gc, this);
	}
	
	public boolean updateFieldState()
	{
		player.update();
		for(Enemy<?> e : enemies)
			e.update();
		if(skill != null && skill.needsUpdating()) skill.update();
		else skill = null;

		this.x = bound(player.getX() + player.getHitX() / 2 - Config.SCREEN_WIDTH / 2, Config.SCREEN_WIDTH, this.width);
		this.y = bound(player.getY() + player.getHitY() / 2 - Config.SCREEN_HEIGHT / 2, Config.SCREEN_HEIGHT, this.height);
		
		for(Iterator<Enemy<?>> i = enemies.iterator(); i.hasNext();)
		{
			Enemy<?> e = i.next();
			if(e.isDestroyed())
			{
				i.remove();
				RenderableHolder.getRenderables().remove(e);
			}
		}
		if(player.isDestroyed())
			RenderableHolder.getRenderables().remove(player);
		return player.isDestroyed();
		
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
		if(isSkillActive() && skill instanceof ZaWarudoSkill) return false;
		for(Enemy<?> e : enemies)
		{
			if(Entity.checkCollision(player, e) && e.getState() == EntityState.RUNNING)
				return true;
		}
		return false;
	}
	
	private static double bound(double value, double size, double frame)
	{
		return value < 0 ? 0 : value + size > frame ? frame - size : value;
	}
	
	public double boundX(double x, double hitW)
	{
		return bound(x, hitW, width);
	}
	
	public double boundY(double y, double hitH)
	{
		return bound(y, hitH, height);
	}

	public void setSkill(SpecialSkill skill)
	{
		this.skill = skill;
	}
	
	public SpecialSkill getSkill() {
		return skill;
	}

	public boolean isSkillActive()
	{
		return skill != null && skill.isActive();
	}
}
