package me.hyv.of.game;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
	private static final int UP_KEY = GLFW_KEY_W;
	private static final int DOWN_KEY = GLFW_KEY_S;
	private static final int LEFT_KEY = GLFW_KEY_A;
	private static final int RIGHT_KEY = GLFW_KEY_D;
	
	public static boolean up, down, left, right;
	
	public static void onKeyStateChanged(int key, int action) {
		if(action != GLFW_PRESS && action != GLFW_RELEASE)
			return;
		
		boolean key_press = action == GLFW_PRESS;
		
		switch(key) {
		case UP_KEY: up = key_press; break;
		case DOWN_KEY: down = key_press; break;
		case LEFT_KEY: left = key_press; break;
		case RIGHT_KEY: right = key_press; break;
		default: break;
		}
	}
}
