package me.hyv.of.engine.math;

public class Vector2 {
	
	public static final Vector2 ZERO = new Vector2(0, 0);
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
	
	public static float dot(float aX, float aY, float bX, float bY) {
		return aX*bX+aY*bY;
	}
	
	public float dotNormalized(Vector2 other) {
		float len = length();
		float otherLen = other.length();
		return dot(x/len, y/len, other.x/otherLen, other.y/otherLen);
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
	
	public float distanceTo(Vector2 v) {
		return (float)Math.hypot(x-v.x, y-v.y);
	}
	
	public void set(Vector2 v) {
		x = v.x;
		y = v.y;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
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
	
	public void subtractSelf(float x, float y) {
		this.x -= x;
		this.y -= y;
	}
	
	@Override
	public String toString() {
		return String.format("[%f, %f]", x, y);
	}
}
