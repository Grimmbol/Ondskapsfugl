package me.hyv.of.shape;

import me.hyv.of.engine.math.Vector2;
import me.hyv.of.game.comp.CollitionComponent;
import me.hyv.of.scene.Entity;

import static org.lwjgl.opengl.GL11.*;

public class CollisionFinder {
	public static class Result {
		public boolean ainb, bina, intersect;
		public Vector2 overlapN = new Vector2();
		public float overlap;
	}
	
	public static boolean testCollition(CollitionComponent a, CollitionComponent b, Result result) {
		float dist = a.getParent().getDistance(b.getParent());
		if(dist > a.getMaxRadius() + b.getMaxRadius())
			return false;
		
		Entity e = a.getParent();
		float aX = e.x, aY = e.y, aXS = e.xSize, aYS = e.ySize;
		e = b.getParent();
		float bX = e.x, bY = e.y, bXS = e.xSize, bYS = e.ySize;
		
		Vector2 aC = Vector2.REUSEABLE.pop();
		Vector2 bC = Vector2.REUSEABLE.pop();
		Vector2 point = Vector2.REUSEABLE.pop();
		
		ConvexPolygon aS = a.getShape();
		ConvexPolygon bS = b.getShape();
		
		result.intersect = false;
		
		mainLoop:
		for(int i = 0; i < aS.x.length; i++) {
			for(int j = 0; j < 1; j++) {
				
				point.set(aS.x[i]*aXS+aX-bX, aS.y[i]*aYS+aY-bY); //We first see if a is in b
				int xp1 = (j+1)%bS.x.length;
				aC.set(bS.x[j]*bXS, bS.y[j]*bYS);
				bC.set(bS.x[xp1]*bXS, bS.y[xp1]*bYS);
				if(intersectTriangleAndPoint(aC, bC, point, result)) {
					result.ainb = true;
					result.bina = false;
					break mainLoop;
				}
				
				glBegin(GL_LINE_STRIP);
				glColor3f(1, 0.8f, 0.3f);
				glVertex2f(aC.x+e.x, aC.y+e.y);
				glVertex2f(bC.x+e.x, bC.y+e.y);
				glVertex2f(point.x+e.x, point.y+e.y);
				glEnd();
				
				/*point.set(bS.x[j]*bXS+bX-aX, bS.y[j]*bYS+bY-aY); //We then check if b is in a
				xp1 = (i+1)%aS.x.length;
				aC.set(aS.x[i]*aXS, 	aS.y[i]*aYS);
				bC.set(aS.x[xp1]*aXS, 	aS.y[xp1]*aYS);
				
				if(intersectTriangleAndPoint(aC, bC, point, result)) {
					result.bina = true;
					result.ainb = false;
					break mainLoop;
				}*/
			}
		}
		
		Vector2.REUSEABLE.push(aC);
		Vector2.REUSEABLE.push(bC);
		Vector2.REUSEABLE.push(point);
		return result.intersect;
	}
	
	public static boolean intersectTriangleAndPoint(Vector2 a, Vector2 b, Vector2 point, Result result) {
		float ap = a.dotNormalized(point);
		float ab = a.dotNormalized(b);
		float bp = b.dotNormalized(point);
		
		if(ap<ab || bp < ab)
			return false;
		
		float dist = getDistanceToLine(a, b, point);
		if(dist < 0) {
			result.intersect = true;
			result.overlap = dist;
			result.overlapN.set(b.y-a.y, a.x-b.x);
			result.overlapN.normalizeSelf();
			
			/*glBegin(GL_QUADS);
			glColor3f(1.f, 1.f, 1.f);
			glVertex2f(0, 0);
			glVertex2f(1000, 0);
			glVertex2f(1000, 20);
			glVertex2f(0, 20);
			glEnd();*/
			
			return true;
		}
		return false;
	}
	
	/**
	 * @param a the a point of the line
	 * @param b the b point of the line
	 * @param point the point
	 * @return the distance from the line to the point. When positive, a is to the right of the normal
	 */
	public static final float getDistanceToLine(Vector2 a, Vector2 b, Vector2 point) {
		if(a.x == b.x)
			return a.y > b.y ? a.x - point.x : point.x - a.x;
	
		float line = (a.y-b.y)/(a.x-b.x);
		float norm = line * -1;
		
		float skj = a.y - a.x * line;
		float normSkj = point.y - point.x * norm;
		
		float x = (normSkj-skj) / (line-norm);
		float y = x*line+skj;
		
		float dist = (float)Math.hypot(point.x-x, point.y-y);
		
		return point.y > y != a.x < b.x ? dist : -dist;
	}
	
	public static void main(String[] args) {
		Vector2 a = new Vector2(4, 10);
		Vector2 b = new Vector2(12, 3);
		Vector2 c = new Vector2(9, 9);
		Vector2 d = new Vector2(6, 4);
		Vector2 e = new Vector2(2, 6);
		Vector2 f = new Vector2(3, 4);
		Vector2 g = new Vector2(7, 2);
		Vector2 h = new Vector2(5, 8);
		Vector2 i = new Vector2(4, 8);
		Result result = new Result();
		
		System.out.println(intersectTriangleAndPoint(d, h, f, result));
		System.out.println(result.overlap + ":" + result.overlapN);
	}
}
