package me.hyv.of.shape;

import java.util.Stack;

import me.hyv.of.game.comp.CollitionComponent;
//import me.hyv.of.game.comp.PhysicsPool;
//import me.hyv.of.scene.Entity;
import me.hyv.of.game.comp.PhysicsPool;

public class ColliderHelper {
	/**
	 * Takes a CollitionComponent as well as a direction vector
	 * and a point, and returns the point farthest along the direction
	 * still contained in the CollitionComponent
	 * 
	 * @param dir
	 * @param point
	 * @param cc
	 * @return
	 */
	
	private static float[] support(float[] dir, float[] point, CollitionComponent diff) {
		
		return null;
	}
	
	/** cc1 and cc2 must both contain a reference to the same CollitionPool
	 * 
	 * @param cc1
	 * @param cc2
	 * @return
	 */
	
	public static boolean isIntersecting(CollitionComponent cc1, CollitionComponent cc2) {
		
		CollitionComponent difference  = new CollitionComponent(minkowskyDifference(cc1.getShape(), cc2.getShape()),cc1.getPhysicsPool());
		
		return false;
	}
	
	
	/**
	 * Takes the minkowsky difference of the given ConvexPolygon arguments
	 * 
	 * @param a
	 * @param b
	 * @return The convex hull of the minowsky difference a-b as a new ConvexPolygon
	 */
	
	public static ConvexPolygon minkowskyDifference(ConvexPolygon a, ConvexPolygon b) {
			
		return null;
	}
	
	public static float orientation(float[] a, float[] b, float[] c) {
		return (b[0]*c[1] - b[1]*c[0]) - (a[0]*(b[1]-c[1])) + (c[0] - b[0]);
	}
	
	public static float dotProduct(float [] a, float[] b) {
		
		if(a.length != b.length)
			throw new IllegalArgumentException("The two vectors must belong to the "
					+ "same space. For instance, if a is in Q^3, so must b");
	
		float result = 0; 
		
		for(int i = 0; i < a.length; i++) {
			result += a[i]*b[i];
		}
		
		return result;
	}
	
	public static float[] crossProduct (float[] a, float[] b) {
		float[] result = {a[1]*b[2] - a[2]*b[1], a[2]*b[0] - a[0]*b[2], 
				          a[0]*b[1] - a[1]*b[0]};
		return result;
	}
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		
		float[] dir   = {1,1};
		float[] point = {0,0};

		CollitionComponent component = new CollitionComponent(ConvexPolygon.PENTAGON, new PhysicsPool());
		
		for(int i = 0; i < component.getShape().x.length; i++) {
			System.out.printf("%f %f \n",component.getShape().x[i], component.getShape().y[i]);
		}
	}
}
