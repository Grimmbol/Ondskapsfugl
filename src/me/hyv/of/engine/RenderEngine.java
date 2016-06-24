package me.hyv.of.engine;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW_MATRIX;
import static org.lwjgl.opengl.GL11.GL_PROJECTION_MATRIX;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

public class RenderEngine {
	
	private static int canvasWidth, canvasHeight;
	
	public static void setCanvasSize(int width, int height) {
		canvasWidth = width;
		canvasHeight = height;
		glMatrixMode(GL_PROJECTION_MATRIX);
		glLoadIdentity();
		glOrtho(0, width, 0, height, 0, 1);
		glMatrixMode(GL_MODELVIEW_MATRIX);
	}
	
	public static int getCanvasWidth() {
		return canvasWidth;
	}
	
	public static int getCanvasHeight() {
		return canvasHeight;
	}
}
