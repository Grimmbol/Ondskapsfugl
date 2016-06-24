package me.hyv.of.game.comp;

import me.hyv.of.engine.Time;
import me.hyv.of.scene.Component;
import me.hyv.of.scene.Entity;
import me.hyv.of.shape.ConvexPolygon;

public class CollitionComponent extends Component {

	private ConvexPolygon shape;
	
	protected float xSpeed, ySpeed;
	protected boolean collider = true;
	
	public CollitionComponent(ConvexPolygon shape) {
		this.shape = shape;
	}
	
	public Entity getParent() {
		return parent;
	}
	
	@Override
	public void update() {
		move();
	}
	
	protected void move() {
		parent.x += xSpeed * Time.getDelta();
		parent.y += ySpeed * Time.getDelta();
	}

	protected void friction(float friction) {
		float frFactor = 1f/(1+Time.getDelta()*friction);
		xSpeed *= frFactor;
		ySpeed *= frFactor;
	}
	
	@Override
	public void render() {
		
	}
	
	public ConvexPolygon getShape() {
		return shape;
	}
	
	public boolean isSolid() {
		return collider;
	}
}
