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
	
	private static float[] support(float[] dir, float[] point, CollitionComponent diff) {
		
		ConvexPolygon curShape = diff.getShape();
		float[] newPoint = new float[2];
		
		//Creates a function representing the direction vector
		float DRate = dir[1]/dir[0];
		float Dy0   = (point[1]) - (DRate * point[0]) ;
		
		for(int i = 0; i < diff.getShape().x.length; i++) {
			
			float[] curVec = new float[2];
			
			if(i < diff.getShape().x.length -1) {
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
		return newPoint;
	}
	
	public static boolean isIntersecting(CollitionComponent cc1, CollitionComponent cc2) {
		
		float offsetX1 = cc1.getParent().x;
		float offsetY1 = cc1.getParent().y;
		float offsetX2 = cc2.getParent().x;
		float offsetY2 = cc2.getParent().y;
		
		float scaleX1 = cc1.getParent().xSize;
		float scaleY1 = cc1.getParent().ySize;
		float scaleX2 = cc2.getParent().xSize;
		float scaleY2 = cc2.getParent().ySize;
		
		float[] S = {(cc1.getShape().x[0]*scaleX1 + offsetX1) - (cc2.getShape().x[0]*scaleX2 + offsetX2), 
				     (cc1.getShape().y[0]*scaleY1 + offsetY1) - (cc2.getShape().y[0]*scaleY2 + offsetY2) };
		
		float[][] simplex = {S};
		float[] D = {-S[0], -S[1]};
		
		for(int c = 2; c <= 3; c++) {
			float [] A = {support(D,S,cc1)[0] - support(D,S,cc2)[0],
					      support(D,S,cc1)[1] - support(D,S,cc2)[1]};
			
			if (dotProduct (A,D) > 0) {
				return false;
			}
				
			
			float[][] newSimplex = new float[simplex.length +1][2];
			for(int i = 0; i < simplex.length; i++) {
				for(int j = 0; j < simplex[i].length; j++) {
					newSimplex[i][j] = simplex[i][j];
				}
			}
			newSimplex[newSimplex.length-1][0] = A[0];
			newSimplex[newSimplex.length-1][1] = A[1];
			
			if (doSimplex(simplex,D))
				return true;
		}
		return false;
	}
	
	private static boolean doSimplex(float[][] simplex, float[] D) {
		if(simplex.length == 2){
			
			float[] ab = {simplex[0][0]-simplex[1][0],simplex[0][1]-simplex[1][1],0};
			float[] a0 = {simplex[1][0], simplex[1][1], 0};
			
			if(dotProduct(ab,a0) > 0) {
				D = crossProduct(ab, crossProduct(ab, a0));
				
				float[][] resultSimplex = new float[2][2];
				resultSimplex[0][0] = simplex[0][0];
				resultSimplex[0][1] = simplex[0][1];
				resultSimplex[1][0] = simplex[1][0];
				resultSimplex[1][1] = simplex[1][1];
			}
			else {
				float[][] resultSimplex = new float[1][2];
				resultSimplex[0][0] = a0[0];
				resultSimplex[0][1] = a0[1];
				simplex = resultSimplex;
				
				D = a0;
			}
		}
			
		else if(simplex.length == 3){
			
			float[] a0 = {simplex[2][0], simplex[2][1], 0};
			float[] ab = {simplex[1][0] - simplex[2][0], simplex[1][1] - simplex[2][1], 0};
			float[] ac = {simplex[0][0] - simplex[2][0], simplex[0][1] - simplex[2][1], 0};
			float[] abc = crossProduct(ab, ac);
			
			if(dotProduct(a0, crossProduct(abc, ac)) > 0) {
				return false;
			}
			
			else {
				if(dotProduct(a0, crossProduct(ab, abc)) > 0){
					return false;
				}
				
				else {
					return true;
				}
			}
		}
		return false;
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
	
//	public static void main(String[] args) {
//		long time = System.currentTimeMillis();
//		
//		float[] dir   = {1,1};
//		float[] point = {0,0};
//
//		CollitionComponent component1 = new CollitionComponent(ConvexPolygon.PENTAGON, new PhysicsPool());
//		CollitionComponent component2 = new CollitionComponent(ConvexPolygon.PENTAGON, new PhysicsPool());
//		Entity entity1 = new Entity();
//		Entity entity2 = new Entity();
//		entity1.addComponent(component1);
//		entity2.addComponent(component2);
//		CollitionComponent component = new CollitionComponent(ConvexPolygon.CIRCLE_SHAPE_100, new PhysicsPool());
//		Entity entity = new Entity();
//		entity.addComponent(component);
//		
////		for(int i = 0; i < component.getShape().x.length; i++) {
////			System.out.printf("%f %f \n",component.getShape().x[i], component.getShape().y[i]);
////		}
//		
//		System.out.println(isIntersecting(component1, component2));
//		
//	}
}
