package me.hyv.of.shape;

import static org.lwjgl.opengl.GL11.*;

public class ConvexPolygon {
	public static final ConvexPolygon UNIT_SQUARE = 
			new ConvexPolygon(new float[]{-.5f, .5f, .5f, -.5f}, new float[]{-.5f, -.5f, .5f, .5f});
	public static final ConvexPolygon CIRCLE_SHAPE_100 = createNPoly(100);
	public static final ConvexPolygon EQ_TRIANGLE_Y_SYM = createNPoly(3);
	public static final ConvexPolygon ROMBE = createNPoly(4);
	public static final ConvexPolygon PENTAGON = createNPoly(5);
	public static final ConvexPolygon HEXAGON = createNPoly(6);
	public static final ConvexPolygon RIGHT_ANGLE_BL = 
			new ConvexPolygon(new float[]{-.5f, .5f, -.5f}, new float[]{-.5f, -.5f, .5f});
	
	public float[] x, y;
	public float maxRadius;
	
	public ConvexPolygon(float[] x, float[] y) {
		this.x = x;
		this.y = y;
		if(x.length != y.length)
			throw new RuntimeException("A shape must have an equal number of x and y coordinates");
		if(x.length < 3)
			throw new RuntimeException("A shape must have at least 3 nodes");
		
		for(int i = 0; i < x.length; i++) {
			maxRadius = Math.max(maxRadius, x[i]*x[i]+y[i]*y[i]);
		}
		
		maxRadius = (float) Math.sqrt(maxRadius);
	}
	
	public void renderGL11() {
		glBegin(GL_TRIANGLE_FAN);
		glVertex2f(x[0], 	y[0]);
		for(int i = 1; i < x.length-1; i++) {
			glVertex2f(x[i], 	y[i]);
			glVertex2f(x[i+1], 	y[i+1]);
		}
		glEnd();
	}
	
	/**
	 * The nodes get listed counterclockwise around the shape
	 */
	
	public static ConvexPolygon createNPoly(int n) {
		float[] x = new float[n], y = new float[n];
		
		double step = Math.PI*2/n;
		
		for(int i = 0; i < n; i++) {
			x[i] = -(float)Math.sin(step*i)/2;
			y[i] = (float)Math.cos(step*i)/2;
		}
		
		return new ConvexPolygon(x, y);
	}
}
