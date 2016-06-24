package me.hyv.of.shape;

import me.hyv.of.game.comp.CollitionComponent;

import static org.lwjgl.opengl.GL11.*;

public class HavardCollision {
	public static boolean intersecting(CollitionComponent c1, CollitionComponent c2) {
		
		float dist = c1.getParent().getDistance(c2.getParent());
		if(dist > c1.getMaxRadius() + c2.getMaxRadius())
			return false;
		
		ConvexPolygon s1 = c1.getShape();
		ConvexPolygon s2 = c2.getShape();
		
		float s1xMove = c1.getParent().x;
		float s1yMove = c1.getParent().y;
		float s1xSize = c1.getParent().xSize;
		float s1ySize = c1.getParent().ySize;
		
		float s2xMove = c2.getParent().x;
		float s2yMove = c2.getParent().y;
		float s2xSize = c2.getParent().xSize;
		float s2ySize = c2.getParent().ySize;
		
		for(int i = 0; i < s1.x.length; i++) {
			for(int j = 0; j < s2.x.length; j++) {
				if(lineIntersect(s1.x[i]*s1xSize+s1xMove, s1.y[i]*s1ySize+s1yMove, 
									s1.x[(i+1)%s1.x.length]*s1xSize+s1xMove, s1.y[(i+1)%s1.y.length]*s1ySize+s1yMove,
									
									s2.x[j]*s2xSize+s2xMove, s2.y[j]*s2ySize+s2yMove, 
									s2.x[(j+1)%s2.x.length]*s2xSize+s2xMove, s2.y[(j+1)%s2.y.length]*s2ySize+s2yMove)) {
					
					glBegin(GL_LINES);
					
					glColor3f(0.6f, 0.9f, 0.3f);
					glVertex2f(s1.x[i]*s1xSize+s1xMove, s1.y[i]*s1ySize+s1yMove);
					glVertex2f(s1.x[(i+1)%s1.x.length]*s1xSize+s1xMove, s1.y[(i+1)%s1.y.length]*s1ySize+s1yMove);
					glColor3f(0.2f, 0.9f, 0.8f);
					glVertex2f(s2.x[j]*s2xSize+s2xMove, s2.y[j]*s2ySize+s2yMove);
					glVertex2f(s2.x[(j+1)%s2.x.length]*s2xSize+s2xMove, s2.y[(j+1)%s2.y.length]*s2ySize+s2yMove);
					glEnd();
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean lineIntersect(float aX, float aY, float bX, float bY, float cX, float cY, float dX, float dY) {
		if(aX == bX) {
			if(cX == dX)
				return false;
			
			float p = (cY-dY)/(cX-dX)*(aX-cX)+cY;
			return ((aX<cX) != (aX<dX)) && (p>aY == p<bY);
		}
		if(cX == dX) {
			float p = (aY-bY)/(aX-bX)*(cX-aX)+aY;
			return ((aX<cX) != (bX<cX)) && (p>cY == p<dY);
		}
				
		float a = (aY-bY)/(aX-bX);
		float b = aY;
		float c = (cY-dY)/(cX-dX);
		float d = cY + c * (aX - cX);
		
		if(a == c) //Two parallel lines
			return false;
		
		float x = (d-b)/(a-c) + aX;
		
		return aX <= x == x <= bX && cX <= x == x <= dX;
	}
}
