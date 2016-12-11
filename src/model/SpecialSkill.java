package model;

public abstract class SpecialSkill implements IRenderable
{
	private boolean isVisible;
	private int age, lifespan;
	private double x, y;
	private Runnable effect, reverseEffect;
	
	public SpecialSkill(int lifespan, double x, double y, Runnable effect, Runnable reverseEffect)
	{
		this.age = 0;
		this.lifespan = lifespan;
		this.x = x;
		this.y = y;
		this.isVisible = true;
		this.effect = effect;
		this.reverseEffect = reverseEffect;
		this.effect.run();
	}
	
	public void update()
	{
		age++;
		if(age >= lifespan)
		{
			isVisible = false;
			reverseEffect.run();
		}
	}
	
	@Override
	public boolean isVisible()
	{
		return isVisible;
	}
	
	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	@Override
	public int getZ()
	{
		return 2;
	}
}
