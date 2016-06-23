package me.hyv.of.engine;

public abstract class AbstractGame {
	public abstract void init(BaseEngine engine);
	public abstract void resize(int width, int height);
	public abstract void onFrame(int deltaMillis);
	public abstract void onKeyStateChange(int key, int action);
	public abstract void destroy();
}
