package model;

import util.Config;
import util.ResourceLoader;

public class KnightEnemy extends Enemy<KnightDirection>
{

	public KnightEnemy(Field field, double x, double y)
	{
		//TODO add speed, directionChangeDelay, and lifeSpan
		super(field, x, y, 10, 0, 0, 0, 0, KnightDirection.values()[0], 6, 50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw()
	{
		// TODO Auto-generated method stub
		System.out.println("State: " + state.getName() + ", Age: " + age + ", Sprite: " + spriteCounter + ", Pos: (" + this.x + ", " + this.y + ")");
	}

	@Override
	protected int getTotalSprites()
	{
		//DEBUG
		return 5;
//		return (state == EntityState.DYING) ? Config.DYING_DURATION : ResourceLoader.getInstance().getKnightTotalSprites(state);
	}
	
	public static void main(String[] args)
	{
		Field field = new Field(1000, 1000);
		
		Player player = new Player(field, 100, 100);
		field.setPlayer(player);
		
		KnightEnemy knight = new KnightEnemy(field, 500, 500);
		field.addEnemy(knight);
		
		System.out.println("Starting...");
		
		while(!knight.isDestroyed())
		{
			knight.update();
			if(knight.isDestroyed()) break;
			knight.draw();
//			System.out.println(knight.directionChangeDelayCounter + " " + knight.direction.getDx(1) + " " + knight.direction.getDy(1));
//			try
//			{
//				Thread.sleep(200);
//			}
//			catch (InterruptedException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		System.out.println("Ended.");
	}
}
