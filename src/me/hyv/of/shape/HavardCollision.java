package me.hyv.of.shape;

import me.hyv.of.game.comp.CollitionComponent;

public class HavardCollision {
	public static boolean intersecting(CollitionComponent c1, CollitionComponent c2) {
		float dist = c1.getParent().getDistance(c2.getParent());
		ConvexPolygon p1 = c1.getShape();
		ConvexPolygon p2 = c2.getShape();
		if(dist > p1.maxRadius + p2.maxRadius)
			return false;
		
		return true;
	}
}
