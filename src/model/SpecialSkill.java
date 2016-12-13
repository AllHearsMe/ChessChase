/**
 * PROG METH 2110215
 * Project: Chess Chase
 * @author Vivattanachai Sangsa-nga 5831065021, Attawit Chaiyaroj 5831079921
 */

package model;

public abstract class SpecialSkill implements IRenderable
{
	protected boolean isActive;
	protected int age, lifespan;
	protected Runnable effect, reverseEffect;
	
	public SpecialSkill(int lifespan, Runnable effect, Runnable reverseEffect)
	{
		this.age = 0;
		this.lifespan = lifespan;
		this.isActive = true;
		this.effect = effect;
		this.reverseEffect = reverseEffect;
		RenderableHolder.add(this);
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
	
	abstract public boolean needsUpdating();
	
	public boolean isActive()
	{
		return isActive;
	}

	@Override
	public int getZ()
	{
		return 2;
	}
}
