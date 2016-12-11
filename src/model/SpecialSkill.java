package model;

public abstract class SpecialSkill implements IRenderable
{
	protected boolean isActive;
	protected int age, lifespan;
	protected double x, y;
	protected Runnable effect, reverseEffect;
	
	public SpecialSkill(int lifespan, double x, double y, Runnable effect, Runnable reverseEffect)
	{
		this.age = 0;
		this.lifespan = lifespan;
		this.x = x;
		this.y = y;
		this.isActive = true;
		this.effect = effect;
		this.reverseEffect = reverseEffect;
		RenderableHolder.getInstance().add(this);
		this.effect.run();
	}
	
	public void update()
	{
		age++;
		if(age >= lifespan)
		{
			isActive = false;
			reverseEffect.run();
		}
	}
	
	public boolean isActive()
	{
		return isActive;
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
