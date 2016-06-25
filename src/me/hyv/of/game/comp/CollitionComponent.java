package me.hyv.of.game.comp;

import me.hyv.of.engine.Time;
import me.hyv.of.scene.Component;
import me.hyv.of.scene.Entity;
import me.hyv.of.shape.ConvexPolygon;

public class CollitionComponent extends Component {

	private ConvexPolygon shape;
	
	protected float xSpeed, ySpeed;
	protected boolean collider = true;
	
	private PhysicsPool pool;
	
	public CollitionComponent(ConvexPolygon shape, PhysicsPool pool) {
		this.shape = shape;
		this.pool = pool;
	}
	
	@Override
	public void setParent(Entity parent) {
		if(this.parent == null)
			pool.registerObject(this);
		super.setParent(parent);
	}
	
	@Override
	public void kill() {
		super.kill();
		pool.removeComponent(this);
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
	
	public float getMaxRadius() {
		return (float)Math.hypot(shape.maxX * parent.xSize, shape.maxY * parent.ySize);
	}
	
	public boolean hasSpeed(float max) {
		return Math.abs(xSpeed) > max || Math.abs(ySpeed) > max;
	}
}
