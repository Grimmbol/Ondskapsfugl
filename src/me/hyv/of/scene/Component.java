package me.hyv.of.scene;

public abstract class Component {
	protected Entity parent;
	private boolean alive = true;
	
	public void setParent(Entity parent) {
		if(this.parent != null)
			this.parent.removeComponentWithoutKilling(this);
		this.parent = parent;
	}
	
	public abstract void update();
	
	public abstract void render();
	
	public void kill() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}
}
