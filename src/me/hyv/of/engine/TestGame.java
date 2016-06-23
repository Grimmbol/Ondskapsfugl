package me.hyv.of.engine;

import static org.lwjgl.opengl.GL11.*;

public class TestGame extends AbstractGame {

	@Override
	public void init(BaseEngine engine) {
		glDisable(GL_DEPTH_TEST);
		glClearColor(0.2f, 0.5f, 0.8f, 0);
	}

	@Override
	public void resize(int width, int height) {
		System.out.printf("w: %d, h: %d%n", width, height);
	}

	@Override
	public void onFrame(int deltaMillis) {
		glClear(GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void onKeyStateChange(int key, int action) {
		
	}

	@Override
	public void destroy() {
		
	}

	public static void main(String[] args) {
		BaseEngine engine = new BaseEngine(new TestGame(), "TestGame", 1600, 900, true, false);
		engine.run();
	}
}
