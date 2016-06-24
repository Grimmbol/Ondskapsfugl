package me.hyv.of.shape;

import me.hyv.of.game.comp.CollitionComponent;
import me.hyv.of.game.comp.PhysicsPool;
import me.hyv.of.scene.Entity;

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
	
	private static float[] support(float[] dir, float[] point, CollitionComponent cc) {
		
		ConvexPolygon curShape = cc.getShape();
		float[] newPoint = new float[2];
		
		//Creates a function representing the direction vector
		float DRate = dir[1]/dir[0];
		float Dy0   = (point[1]) - (DRate * point[0]) ;
		
		for(int i = 0; i < cc.getShape().x.length; i++) {
			
			float[] curVec = new float[2];
			
			if(i < cc.getShape().x.length -1) {
				curVec[0] = (curShape.x[i+1]) - (curShape.x[i]);
				curVec[1] = (curShape.y[i+1]) - (curShape.y[i]);
			
				//Creates a function representing the vector that intersection is checked up against
				float VRate = curVec[1]/ curVec[0];
				float Vy0   = (curShape.y[i+1]) - (VRate * curShape.x[i+1]);
			
				if(DRate - VRate != 0) {
					newPoint[0] = (Vy0 - Dy0)/(DRate - VRate);
				
					if(newPoint[0] >= Math.min(curShape.x[i+1], curShape.x[i]) && 
					   newPoint[0] <= Math.max(curShape.x[i+1], curShape.x[i])) {
						
						newPoint[1] = VRate * newPoint[0] + Vy0;
						
						if(Math.signum(dir[0]) == Math.signum(newPoint[0]) && 
						   Math.signum(dir[1]) == Math.signum(newPoint[1])) {
							return newPoint;
						}
					}
				}
			}
			else {
				curVec[0] = (curShape.x[0]) - (curShape.x[i]);
				curVec[1] = (curShape.y[0]) - (curShape.y[i]);
				
				//Creates a function representing the vector that intersection is checked up against
				float VRate = curVec[1]/ curVec[0];
				float Vy0   = (curShape.y[0]) - (VRate * curShape.x[0]);
				
				if(DRate - VRate != 0) {
					newPoint[0] = (Vy0 - Dy0)/(DRate - VRate);
				
					if(newPoint[0] >= Math.min(curShape.x[0], curShape.x[i]) && 
					   newPoint[0] <= Math.max(curShape.x[0], curShape.x[i])) {
						
						newPoint[1] = VRate * newPoint[0] + Vy0;
						
						if(Math.signum(dir[0]) == Math.signum(newPoint[0]) && 
						   Math.signum(dir[1]) == Math.signum(newPoint[1])) {
								return newPoint;
						}
					}
				}
			}	
		}
		return null;
	}
	
	
	
	
	public static void main(String[] args) {
		
		long time = System.currentTimeMillis();
		
		float[] dir   = {1,1};
		float[] point = {0,0};
		CollitionComponent component = new CollitionComponent(ConvexPolygon.CIRCLE_SHAPE_100, new PhysicsPool());
		Entity entity = new Entity();
		entity.addComponent(component);
		
//		for(int i = 0; i < component.getShape().x.length; i++) {
//			System.out.printf("%f %f \n",component.getShape().x[i], component.getShape().y[i]);
//		}
		
		float[] result = support(dir, point, component);
		
		System.out.printf("Operasjonen tok %d millisekunder\n", System.currentTimeMillis()-time);
		
		if(result != null)
			System.out.printf("%f %f", result[0], result[1]);
		else
			System.out.println("Fant ikke noe passende punkt!");
	}
}
