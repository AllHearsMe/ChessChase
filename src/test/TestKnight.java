package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Field;
import model.KnightEnemy;

public class TestKnight
{
	
	@Test
	public void test()
	{
		Field field = new Field(1000, 1000);
		KnightEnemy knight = new KnightEnemy(field, 500, 500);
	}
	
}
