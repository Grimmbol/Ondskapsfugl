package me.hyv.of.shape;

import me.hyv.of.game.comp.CollitionComponent;

public class HavardCollision {
	public static boolean intersecting(CollitionComponent c1, CollitionComponent c2) {
		float dist = c1.getParent().getDistance(c2.getParent());
		if(dist > c1.getMaxRadius() + c2.getMaxRadius())
			return false;
		return true;
	}
}
