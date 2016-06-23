package me.hyv.of.scene;

import java.util.ArrayList;

public class Scene {
	private ArrayList<Entity> entities;
	
	public Scene() {
		entities = new ArrayList<>();
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void update() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(!e.isAlive()) {
				entities.remove(i);
				i--;
			} else
				e.update();
		}
	}
	
	public void render() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(!e.isAlive()) {
				entities.remove(i);
				i--;
			} else
				e.render();
		}
	}
}
