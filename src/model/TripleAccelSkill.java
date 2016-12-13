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
		int facing;
		
		public AfterImage(double x, double y, int spriteCount, EntityState state, double age, int facing)
		{
			this.x = x;
			this.y = y;
			this.spriteCount = spriteCount;
			this.state = state;
			this.age = age;
			this.facing = facing;
		}
	}
	
	private Queue<AfterImage> afterImages;
	private Player player;
	private Field field;
	
	public TripleAccelSkill(Player player, Field field)
	{
		super(Config.TRIPLE_ACCEL_DURATION, () -> Enemy.setDivider(3), () -> Enemy.setDivider(1));
		this.player = player;
		this.field = field;
		afterImages = new LinkedList<AfterImage>();
		afterImages.offer(new AfterImage(player.getDrawX(), player.getDrawY(), player.getSpriteCounter(), player.getState(), age, player.getFacing()));
	}
	
	@Override
	public void update()
	{
		super.update();
		if(isActive && player.getState() != EntityState.DYING && age % (Config.NORMAL_TICK_PER_SECOND / Config.TRIPLE_ACCEL_AFTER_IMAGE_PER_SECOND) == 0)
			afterImages.offer(new AfterImage(player.getDrawX(), player.getDrawY(), player.getSpriteCounter(), player.getState(), age, player.getFacing()));
		while(afterImages.peek().age - age >= Config.NORMAL_TICK_PER_SECOND)
			afterImages.poll();
		
	}

	@Override
	public boolean isVisible()
	{
		return !afterImages.isEmpty();
	}
	
	@Override
	public boolean needsUpdating()
	{
		return !afterImages.isEmpty();
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		for(AfterImage a : afterImages)
			DrawingUtility.drawPlayerAfterImage(gc, a.x, a.y, a.state, a.spriteCount, a.facing, field, 1 - (age - a.age) / Config.NORMAL_TICK_PER_SECOND);
	}
	
}