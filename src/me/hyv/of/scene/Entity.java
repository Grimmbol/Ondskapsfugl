package me.hyv.of.scene;

import java.util.ArrayList;

public class Entity {
	
	private Scene scene;
	private ArrayList<Component> components;
	private boolean alive;
	private boolean visible;
	
	public float x, y, xSize, ySize;
	
	public Entity() {
		components = new ArrayList<>();
		alive = true;
		x = 1;
		y = 1;
		visible = true;
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
		cmp.kill();
	}
	
	public void removeComponentWithoutKilling(Component cmp) {
		components.remove(cmp);
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void update() {
		for(int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if(!c.isAlive()) {
				components.remove(i);
				i--;
			}
			else
				c.update();
		}
	}
	
	public void render() {
		if(!visible)
			return;
		for(int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if(!c.isAlive()) {
				components.remove(i);
				i--;
			}
			else
				c.render();
		}
	}
	
	public void kill() {
		for(int i = 0; i < components.size(); i++) {
			components.get(i).kill();
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	public Scene getScene() {
		return scene;
	}
	public void compress() {
		components.trimToSize();
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public float getDistance(Entity e) {
		return (float) Math.hypot(x-e.x, y-e.y);
	}
}
