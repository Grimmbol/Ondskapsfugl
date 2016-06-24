package me.hyv.of.game.comp;

import me.hyv.of.scene.Component;

import static org.lwjgl.opengl.GL11.*;

public class BoxComponent extends Component {
	private float r, g, b;
	
	public BoxComponent(float r, float g, float b) {
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
		glColor3f(r, g, b);
		glTranslatef(parent.x, parent.y, 0);
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(parent.xSize, 0);
		glVertex2f(parent.xSize, parent.ySize);
		glVertex2f(0, parent.ySize);
		glEnd();
		glPopMatrix();
	}
}
