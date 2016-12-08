package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.KnightDirection;

public class TestDirection
{
	
	@Test
	public void test()
	{
		int n = 6, i = 0;
		for(; i < n * 2 / 3; i++)
			assertEquals(KnightDirection.EAST_CW.getDx(1), KnightDirection.EAST_CW.getDx(i / (double) n), 0.01);
		for(; i < n; i++)
			assertEquals(0, KnightDirection.EAST_CW.getDx(i / (double) n), 0.01);
	}
	
}
