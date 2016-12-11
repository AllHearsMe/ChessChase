package util;

import model.EntityState;

public class Config
{
	//X, Y in relation to sprite
	public static final double
		PLAYER_HITBOX_X = 57,
		PLAYER_HITBOX_Y = 30,
		PLAYER_HITBOX_W = 88,
		PLAYER_HITBOX_H = 165,
	
		PAWN_HITBOX_X = 60,
		PAWN_HITBOX_Y = 11,
		PAWN_HITBOX_W = 87,
		PAWN_HITBOX_H = 165,
			
		ROOK_HITBOX_X = 49,
		ROOK_HITBOX_Y = 15,
		ROOK_HITBOX_W = 110,
		ROOK_HITBOX_H = 162,
	
		KNIGHT_HITBOX_X = 33,
		KNIGHT_HITBOX_Y = 18,
		KNIGHT_HITBOX_W = 124,
		KNIGHT_HITBOX_H = 168,
			
		BISHOP_HITBOX_X = 56,
		BISHOP_HITBOX_Y = 10,
		BISHOP_HITBOX_W = 74,
		BISHOP_HITBOX_H = 194,
			
		QUEEN_HITBOX_X = 48,
		QUEEN_HITBOX_Y = 2,
		QUEEN_HITBOX_W = 97,
		QUEEN_HITBOX_H = 197,
			
		KING_HITBOX_X = 227,
		KING_HITBOX_Y = 48,
		KING_HITBOX_W = 291,
		KING_HITBOX_H = 809;

	public static final double
		PLAYER_SPEED = 10,
		PAWN_SPEED = 10,
		ROOK_SPEED = 10,
		KNIGHT_SPEED = 10,
		BISHOP_SPEED = 10,
		QUEEN_SPEED = 10,
		KING_SPEED = 10;

	public static final int
		PAWN_DIRECTION_CHANGE_DELAY = 30,
		ROOK_DIRECTION_CHANGE_DELAY = 30,
		KNIGHT_DIRECTION_CHANGE_DELAY = 30,
		BISHOP_DIRECTION_CHANGE_DELAY = 30,
		QUEEN_DIRECTION_CHANGE_DELAY = 30,
		KING_DIRECTION_CHANGE_DELAY = 30;

	public static final int
		PAWN_LIFESPAN = 100,
		ROOK_LIFESPAN = 100,
		KNIGHT_LIFESPAN = 100,
		BISHOP_LIFESPAN = 100,
		QUEEN_LIFESPAN = 100,
		KING_LIFESPAN = 100;

	public static final double
		AWAKEN_RANGE_WIDTH = 500,
		AWAKEN_RANGE_HEIGHT = 500;
	
	public static final int
		SPAWNING_FRAMES = 15,
		AWAKENING_FRAMES = 15,
		RUNNING_FRAMES = 15,
		DYING_FRAMES = 15;
	
	public static final int
		FRAME_DELAY = 2,
		NORMAL_TICK_PER_SECOND = 60,
		NORMAL_FRAME_PER_SECOND = NORMAL_TICK_PER_SECOND / FRAME_DELAY;
	
	public static final int
		SCREEN_WIDTH = 1024,
		SCREEN_HEIGHT = 768;
	
	public static final String
		PLAYER_PATH = "pawn/",
		PAWN_PATH = "enemypawn/",
		ROOK_PATH = "rook/",
		KNIGHT_PATH = "knight/",
		BISHOP_PATH = "bishop/",
		QUEEN_PATH = "queen/",
		KING_PATH = "king/";
	
	public static String getSpriteFileName(EntityState state)
	{
		switch(state)
		{
			case SPAWNING:
				return "bornsprite.png";
			case IDLE:
				return "idle.png";
			case AWAKENING:
				return "awakesprite.png";
			case RUNNING:
				return "walksprite.png";
			case DYING:
				return "dead.png";
			default:
				return "";
		}
	}
	
	public static int getFrameCount(EntityState state)
	{
		switch(state)
		{
			case SPAWNING:
				return SPAWNING_FRAMES;
			case AWAKENING:
				return AWAKENING_FRAMES;
			case RUNNING:
				return RUNNING_FRAMES;
			case DYING:
				return DYING_FRAMES;
			default:
				return 1;
		}
	}
}
