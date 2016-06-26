package me.hyv.of.game;

import me.hyv.of.engine.AbstractGame;
import me.hyv.of.engine.BaseEngine;
import me.hyv.of.engine.RenderEngine;
import me.hyv.of.engine.Time;
import me.hyv.of.game.comp.CollitionComponent;
import me.hyv.of.game.comp.PhysicsPool;
import me.hyv.of.game.comp.PlayerComponent;
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
		glEnable(GL_CULL_FACE);
		glClearColor(0.2f, 0.3f, 0.6f, 0);
		
		gameScene = new Scene();
		
		PhysicsPool pool = new PhysicsPool();
		
		Entity floor = new Entity(1000, 30, 1800, 50);
		floor.addComponent(new ShapeRenderComponent(ConvexPolygon.UNIT_SQUARE, 0.6f, 0.7f, 0.5f));
		//floor.addComponent(new CollitionComponent(ConvexPolygon.UNIT_SQUARE, pool));
		gameScene.addEntity(floor);
		
		
		Entity hexagon = new Entity(547, 400, 80, 100);
		hexagon.addComponent(new ShapeRenderComponent(ConvexPolygon.HEXAGON, 0.8f, 0.6f, 0.4f));
		hexagon.addComponent(new CollitionComponent(ConvexPolygon.HEXAGON, pool));
		gameScene.addEntity(hexagon);
		
		Entity bigSquare = new Entity(800, 600, 200, 200);
		bigSquare.addComponent(new ShapeRenderComponent(ConvexPolygon.UNIT_SQUARE, 0.4f, 0.8f, 0.2f));
		bigSquare.addComponent(new CollitionComponent(ConvexPolygon.UNIT_SQUARE, pool));
		gameScene.addEntity(bigSquare);
		
		Entity rombe = new Entity(900, 300, 200, 120);
		rombe.addComponent(new ShapeRenderComponent(ConvexPolygon.ROMBE, 0.6f, 0.8f, 0.2f));
		rombe.addComponent(new CollitionComponent(ConvexPolygon.ROMBE, pool));
		gameScene.addEntity(rombe);
		
		Entity pentagon = new Entity(1500, 200, 200, 200);
		pentagon.addComponent(new ShapeRenderComponent(ConvexPolygon.PENTAGON, 0.9f, 0.8f, 0.2f));
		pentagon.addComponent(new CollitionComponent(ConvexPolygon.PENTAGON, pool));
		gameScene.addEntity(pentagon);
		
		Entity circle = new Entity(1700, 700, 300, 300);
		circle.addComponent(new ShapeRenderComponent(ConvexPolygon.CIRCLE_SHAPE_100, 0.9f, 0.8f, 0.2f));
		circle.addComponent(new CollitionComponent(ConvexPolygon.CIRCLE_SHAPE_100, pool));
		gameScene.addEntity(circle);
		
		Entity player = new Entity(200, 200, 100, 200);
		player.addComponent(new ShapeRenderComponent(ConvexPolygon.HEXAGON, 0.8f, 0.2f, 0.4f));
		player.addComponent(new PlayerComponent(ConvexPolygon.HEXAGON, pool));
		gameScene.addEntity(player);
		
		Entity physicsPoolEntity = new Entity();
		physicsPoolEntity.addComponent(pool);
		gameScene.addEntity(physicsPoolEntity);
	}

	@Override
	public void resize(int width, int height) {
		glViewport(0, 0, width, height);
		RenderEngine.setCanvasSize(width, height);
	}

	@Override
	public void onFrame() {
		countFPS();
		glClear(GL_COLOR_BUFFER_BIT);
		gameScene.update();
		gameScene.render();
	}
	
	int frames;
	float time;
	private void countFPS() {
		frames++;
		time += Time.getDelta();
		if(time > 1) {
			System.out.printf("FPS: %f%n", frames/time);
			time -= 1;
			frames = 0;
		}
	}

	@Override
	public void onKeyStateChange(int key, int action) {
		Input.onKeyStateChanged(key, action);
	}

	@Override
	public void destroy() {
		
	}
	
	public static void main(String[] args) {
		BaseEngine engine = new BaseEngine(new LogFGame(), "Larvetass og Fluegreie", 1600, 900, true, false);
		engine.run();
	}
}
