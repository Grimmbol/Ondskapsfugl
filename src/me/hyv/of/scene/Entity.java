package me.hyv.of.scene;

import java.util.ArrayList;

public class Entity {
	
	private Scene scene;
	private ArrayList<Component> components;
	private boolean alive;
	
	public float x, y, xSize, ySize;
	
	public Entity() {
		components = new ArrayList<>();
		alive = true;
	}
	
	public Entity(float x, float y, float xSize, float ySize) {
		this();
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	public void addComponent(Component cmp) {
		components.add(cmp);
		cmp.setParent(this);
	}
	
	public void removeComponent(Component cmp) {
		components.remove(cmp);
		cmp.onRemove();
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void update() {
		for(int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if(!c.isAlive()) {
				components.remove(i);
				c.onRemove();
				i--;
			}
			else
				c.update();
		}
	}
	
	public void render() {
		for(int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if(!c.isAlive()) {
				components.remove(i);
				c.onRemove();
				i--;
			}
			else
				c.render();
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	public Scene getScene() {
		return scene;
	}
}
