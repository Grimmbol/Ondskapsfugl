package me.hyv.of.game;

import me.hyv.of.engine.AbstractGame;
import me.hyv.of.engine.BaseEngine;
import me.hyv.of.game.comp.ShapeRenderComponent;
import me.hyv.of.scene.Entity;
import me.hyv.of.scene.Scene;
import me.hyv.of.shape.ConvexPolygon;

import static org.lwjgl.opengl.GL11.*;

public class LogFGame extends AbstractGame {
	
	private Scene gameScene; 
	
	@Override
	public void init(BaseEngine engine) {
		glDisable(GL_DEPTH_TEST);
		glClearColor(0.2f, 0.3f, 0.6f, 0);
		
		gameScene = new Scene();
		Entity shape = new Entity(300, 200, 400, 300);
		shape.addComponent(new ShapeRenderComponent(ConvexPolygon.UNIT_SQUARE, 0.3f, 0.7f, 0.5f));
		gameScene.addEntity(shape);
	}

	@Override
	public void resize(int width, int height) {
		glViewport(0, 0, width, height);
		glMatrixMode(GL_PROJECTION_MATRIX);
		glLoadIdentity();
		glOrtho(0, 1920, 0, 1080, 0, 1);
		glMatrixMode(GL_MODELVIEW_MATRIX);
	}

	@Override
	public void onFrame(int deltaMillis) {
		gameScene.update();
		glClear(GL_COLOR_BUFFER_BIT);
		gameScene.render();
	}

	@Override
	public void onKeyStateChange(int key, int action) {
		
	}

	@Override
	public void destroy() {
		
	}
	
	public static void main(String[] args) {
		BaseEngine engine = new BaseEngine(new LogFGame(), "Larvetass og Fluegreie", 1600, 900, true, false);
		engine.run();
	}
}
