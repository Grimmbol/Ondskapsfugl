package me.hyv.of.shape;

import java.util.Stack;

import me.hyv.of.engine.math.*;
import me.hyv.of.game.comp.CollitionComponent;
import me.hyv.of.game.comp.PhysicsPool;
import me.hyv.of.scene.Entity;

public class ColliderHelper {
	/**
	 * Takes a CollitionComponent as well as a direction vector
	 * and a point, and returns the point farthest along the direction
	 * still contained in the CollitionComponent. The coordinates of 
	 * dir and point must be relative to the origin of the scene
	 * 
	 * @param dir
	 * @param point
	 * @param cc
	 * @return the point farthest along the direction
	 * still contained in the CollitionComponent if it exists, otherwise null
	 */
	
	private static Vector2 support(Vector2 dir, Vector2 point, CollitionComponent cc) {
		
		float scaleX  = cc.getParent().xSize;
		float scaleY  = cc.getParent().ySize;
		float offsetX = cc.getParent().x;
		float offsetY = cc.getParent().y;
		
		float DRate;
		float Dy0;

	    if(dir.x != 0) {
		    if(dir.y == 0)
				DRate = 0;
			else
				DRate = dir.y/dir.x;
				
			Dy0 = point.y - DRate*point.x;
			System.out.println("Direction: " + DRate + "x + " + Dy0);
	    }
	    
		Vector2 currentVector = Vector2.REUSEABLE.pop();
		Vector2 currentStartVertix = Vector2.REUSEABLE.pop();
		
		Vector2 intersection = Vector2.REUSEABLE.pop();
		
		for(int i = 0; i < cc.getShape().x.length; i++) {
			if(i == cc.getShape().x.length-1) {
				
				currentVector.x = (cc.getShape().x[0] + offsetX - cc.getShape().x[i] - offsetX)*scaleX;
				currentVector.y = (cc.getShape().y[0] + offsetY - cc.getShape().y[i] - offsetY)*scaleY;
			}
			else {
				currentVector.x = (cc.getShape().x[i+1] + offsetX - cc.getShape().x[i] - offsetX)*scaleX;
				currentVector.y = (cc.getShape().y[i+1] + offsetY - cc.getShape().y[i] - offsetY)*scaleY;
				System.out.println("Current vector " + currentVector.x + " " + currentVector.y);
			}
			
			currentStartVertix.x = cc.getShape().x[i]*scaleX + offsetX;
			currentStartVertix.y = cc.getShape().y[i]*scaleY + offsetY;
			
			float VRate;
			if(currentVector.x == 0)
				VRate = currentVector.x;
			else if (currentVector.y == 0)
				VRate = currentVector.x;
			else
				VRate = currentVector.y/currentVector.x;
			float Vy0 = currentStartVertix.y - VRate*currentStartVertix.x;
			
			System.out.println("Nr."+i+": Vector: " + VRate + "x + " + Vy0);
			
			if(DRate-VRate == 0) {
				if(Vy0-Dy0 == 0) {
					intersection.x = currentStartVertix.x + currentVector.x;
					intersection.y = currentStartVertix.y + currentVector.y;
					Vector2.REUSEABLE.push(currentVector);
					Vector2.REUSEABLE.push(currentStartVertix);
					return intersection;
				}
				else{
					Vector2.REUSEABLE.push(intersection);
					return null;
				}
			}
			else {
				intersection.x = (Vy0-Dy0)/(DRate-VRate);
				intersection.y = DRate*intersection.x + Dy0;
			}
			if(intersection.x >= Math.min(currentStartVertix.x, currentStartVertix.x + currentVector.x)&&
			   intersection.x <= Math.max(currentStartVertix.x, currentStartVertix.x + currentVector.x)){
				
				System.out.println(i+" "+VRate+"x + " + Vy0);
				
				Vector2.REUSEABLE.push(currentVector);
				Vector2.REUSEABLE.push(currentStartVertix);
				return intersection;
			}
		}
		
		Vector2.REUSEABLE.push(currentVector);
		Vector2.REUSEABLE.push(currentStartVertix);
		Vector2.REUSEABLE.push(intersection);
		
		return null;
	}
	
	/** cc1 and cc2 must both contain a reference to the same CollitionPool
	 * 
	 * @param cc1
	 * @param cc2
	 * @return
	 */
	
	public static boolean isIntersecting(CollitionComponent cc1, CollitionComponent cc2) {
		
		Vector2 S = Vector2.REUSEABLE.pop();
		
		return false;
	}
	
	public static float orientation(Vector2 v1, Vector2 v2, Vector2 v3) {
		return (v2.y*v3.y - v2.y*v3.x) - (v1.x*(v2.y-v3.y)) + (v3.x - v2.x);
	}
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		
		Vector2 dir   = Vector2.REUSEABLE.pop();
		Vector2 point = Vector2.REUSEABLE.pop();
		
		dir.x=1;
		dir.y=1;
		
		point.x=2;
		point.y=1;

		CollitionComponent component = new CollitionComponent(ConvexPolygon.UNIT_SQUARE, new PhysicsPool());
		Entity entity = new Entity();
		entity.addComponent(component);
		entity.xSize = 2;
		entity.x = 2;
		entity.y = 1;
		
		for(int i = 0; i < component.getShape().x.length; i++) {
			System.out.println("("+(component.getShape().x[i]*component.getParent().xSize + component.getParent().x) + "," +
					           (component.getShape().y[i]*component.getParent().ySize + component.getParent().y)+")");
		}
		Vector2 result = support(dir, point, component);
		
		System.out.println("("+result.x + ", " + result.y+")");
	}
}
