package me.hyv.of.shape;

import me.hyv.of.game.comp.CollitionComponent;
import me.hyv.of.scene.Entity;

public class ColliderHelper {
	public static float[] support(float[] dir, float[] point, CollitionComponent cc) {
		
		Entity currentEntity = cc.getParent();
		float[] newPoint = new float[2];
		
		//Creates a function representing the direction vector
		float DRate = dir[1]/dir[2];
		float Dy0 = point[1] - DRate * point[0];
		
		for(int i = 0; i < cc.getShape().x.length; i++) {
			//Creates a function representing vector that intersection is checked up against
//			float Vrate = 
		}
		
		
		
		return null;
	}
}
