package me.hyv.of.game.comp;

import me.hyv.of.engine.Time;
import me.hyv.of.game.Input;
import me.hyv.of.shape.ConvexPolygon;

public class PlayerComponent extends CollitionComponent {
	
	public static final float DEFAULT_ACCEL = 5000;
	public static final float DEFAULT_FRICTION = 10;
	
	private float walkAccel = DEFAULT_ACCEL;
	
	public PlayerComponent(ConvexPolygon shape, PhysicsPool pool) {
		super(shape, pool);
	}

	@Override
	public void update() {
		if(Input.right)
			xSpeed+=walkAccel*Time.getDelta();
		if(Input.left)
			xSpeed-=walkAccel*Time.getDelta();
		if(Input.up)
			ySpeed+=walkAccel*Time.getDelta();
		if(Input.down)
			ySpeed-=walkAccel*Time.getDelta();
		
		friction(DEFAULT_FRICTION);
		move();
	}

	@Override
	public void render() {
		
	}
}
