package me.hyv.of.game.comp;

import static org.lwjgl.opengl.GL11.*;

import me.hyv.of.scene.Component;
import me.hyv.of.shape.ConvexPolygon;

public class ShapeRenderComponent extends Component {
	
	private ConvexPolygon shape;
	private float r, g, b;
	
	public ShapeRenderComponent(ConvexPolygon shape, float r, float g, float b) {
		this.shape = shape;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		glPushMatrix();
		glColor4f(r, g, b, 0.4f);
		glTranslatef(parent.x, parent.y, 0);
		glScalef(parent.xSize, parent.ySize, 0);
		shape.renderGL11();
		glPopMatrix();
	}
}
