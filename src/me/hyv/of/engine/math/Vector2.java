package me.hyv.of.engine.math;

public class Vector2 {
	
	public static final ReuseableStack<Vector2> REUSEABLE = new ReuseableStack<>(()->new Vector2());
	
	public float x, y;
	
	public Vector2() {
		this(0, 0);
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float dot(Vector2 other) {
		return x*other.x+y*other.y;
	}
	
	public void normalizeSelf() {
		float len = length();
		x /= len;
		y /= len;
	}
	
	public float length() {
		return (float)Math.sqrt(lengthSquared());
	}
	
	public float lengthSquared() {
		return x*x+y*y;
	}
	
	public void set(Vector2 v) {
		x = v.x;
		y = v.y;
	}
	
	public void setToNormalized(Vector2 v) {
		set(v);
		normalizeSelf();
	}
	
	public void setToSum(Vector2 a, Vector2 b) {
		x = a.x+b.x;
		y = a.y+b.y;
	}
	
	public void setToDifference(Vector2 a, Vector2 b) {
		x = a.x-b.x;
		y = a.y-b.y;
	}
}
