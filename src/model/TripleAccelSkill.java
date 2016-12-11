package model;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.canvas.GraphicsContext;
import util.Config;
import util.DrawingUtility;

public class TripleAccelSkill extends SpecialSkill
{
	private class AfterImage
	{
		double x, y;
		int spriteCount;
		EntityState state;
		double age;
		
		public AfterImage(double x, double y, int spriteCount, EntityState state, double age)
		{
			this.x = x;
			this.y = y;
			this.spriteCount = spriteCount;
			this.state = state;
			this.age = age;
		}
	}
	
	Queue<AfterImage> points;
	private Player player;
	private Field field;
	
	public TripleAccelSkill(Player player, Field field)
	{
		super(Config.TRIPLE_ACCEL_DURATION, () -> Enemy.setDivider(3), () -> Enemy.setDivider(1));
		this.player = player;
		this.field = field;
		points = new LinkedList<AfterImage>();
		points.offer(new AfterImage(player.getDrawX(), player.getDrawY(), player.getSpriteCounter(), player.getState(), age));
	}
	
	@Override
	public void update()
	{
		super.update();
//		if(age % (Config.TRIPLE_ACCEL_DURATION / Config.NORMAL_TICK_PER_SECOND) == 0)
		if(age % (Config.NORMAL_TICK_PER_SECOND / 10) == 0)
			points.offer(new AfterImage(player.getDrawX(), player.getDrawY(), player.getSpriteCounter(), player.getState(), age));
		if(points.peek().age - age >= Config.NORMAL_TICK_PER_SECOND)
			points.poll();
		
	}

	@Override
	public boolean isVisible()
	{
		return !points.isEmpty();
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		for(AfterImage a : points)
			DrawingUtility.drawPlayerAfterImage(gc, a.x, a.y, a.state, a.spriteCount, player, field, 1 - (age - a.age) / Config.NORMAL_TICK_PER_SECOND);
	}
	
}