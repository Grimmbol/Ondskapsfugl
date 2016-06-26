package me.hyv.of.shape;

import java.util.Stack;

import me.hyv.of.engine.math.*;
import me.hyv.of.game.comp.CollitionComponent;
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
	
	private static float[] support(Vector2 dir, Vector2 point, CollitionComponent diff) {
		
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
	
	public static float orientation(Vector2 v1, Vector2 v2, Vector2 v3) {
		return (v2.y*v3.y - v2.y*v3.x) - (v1.x*(v2.y-v3.y)) + (v3.x - v2.x);
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
