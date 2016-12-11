package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import util.Config;

public class TripleAccelSkill extends SpecialSkill
{
	List<Pair<Double, Double>> points;
	private Player player;
	
	public TripleAccelSkill(int lifespan, Player player)
	{
		super(lifespan, () -> Enemy.setDivider(3), () -> Enemy.setDivider(1));
		this.player = player;
		points = new ArrayList<>();
		points.add(new Pair<Double, Double>(player.getX(), player.getY()));
	}
	
	@Override
	public void update()
	{
		super.update();
		if(age % (Config.TRIPLE_ACCEL_DURATION / Config.NORMAL_TICK_PER_SECOND) == 0)
		{
			
		}
	}

	@Override
	public boolean isVisible()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		// TODO Auto-generated method stub
		
	}
	
}
