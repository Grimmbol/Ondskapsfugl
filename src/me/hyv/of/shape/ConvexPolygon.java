package me.hyv.of.shape;

import static org.lwjgl.opengl.GL11.*;

public class ConvexPolygon {
	public static final ConvexPolygon UNIT_SQUARE = 
			new ConvexPolygon(new float[]{-.5f, .5f, .5f, -.5f}, new float[]{-.5f, -.5f, .5f, .5f});
	
	public float[] x, y;
	
	public ConvexPolygon(float[] x, float[] y) {
		this.x = x;
		this.y = y;
		if(x.length != y.length)
			throw new RuntimeException("A shape must have an equal number of x and y coordinates");
		if(x.length < 3)
			throw new RuntimeException("A shape must have at least 3 nodes");
	}
	
	public void renderGL11() {
		glBegin(GL_TRIANGLES);
		for(int i = 1; i < x.length-1; i++) {
			glVertex2f(x[0], 	y[0]);
			glVertex2f(x[i], 	y[i]);
			glVertex2f(x[i+1], 	y[i+1]);
		}
		glEnd();
	}
}
