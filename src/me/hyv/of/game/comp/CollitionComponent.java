package me.hyv.of.game.comp;

import me.hyv.of.scene.Component;
import me.hyv.of.shape.ConvexPolygon;

public class CollitionComponent extends Component {

	private ConvexPolygon shape;
	
	public CollitionComponent(ConvexPolygon shape) {
		this.shape = shape;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		
	}
	
	public ConvexPolygon getShape() {
		return shape;
	}
}
