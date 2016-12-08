package model;

import util.Config;
import util.ResourceLoader;

public class KnightEnemy extends Enemy<KnightDirection>
{

	public KnightEnemy(Field field, double x, double y)
	{
		//TODO add speed, directionChangeDelay, and lifeSpan
		super(field, x, y, 10, 0, 0, 0, 0, KnightDirection.values()[0], 6, 100);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw()
	{
		// TODO Auto-generated method stub
		System.out.println("State: " + state.getIndex() + ", Sprite: " + spriteCounter + ", Pos: (" + this.x + ", " + this.y + ")");
	}

	@Override
	protected int getTotalSprites()
	{
		//DEBUG
		return 15;
//		return (state == EntityState.DYING) ? Config.DYING_DURATION : ResourceLoader.getInstance().getKnightTotalSprites(state);
	}
	
	public static void main(String[] args)
	{
		Field field = new Field(1000, 1000);
		
		Player player = new Player(field, 100, 100, 0);
		field.setPlayer(player);
		
		KnightEnemy knight = new KnightEnemy(field, 500, 500);
		field.addEnemy(knight);
		
		System.out.println("Starting...");
		
		while(!knight.isDestroyed())
		{
			knight.update();
			knight.draw();
			System.out.println(knight.directionChangeDelayCounter + " " + knight.direction.getDx(1) + " " + knight.direction.getDy(1));
		}
		
		System.out.println("Ended.");
	}
}
