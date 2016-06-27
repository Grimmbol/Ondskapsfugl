package me.hyv.of.engine.math;

public class Vector3 {
	public static final ReuseableStack<Vector3> REUSEABLE = new ReuseableStack<>(()->new Vector3());
	
	public float x, y, z;
	
	public Vector3() {
		this(0, 0, 0);
	}
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float dot(Vector3 other) {
		return x*other.x+y*other.y+z*other.z;
	}
	
	public void setToCross(Vector3 v1, Vector3 v2) {
		x =   v1.y*v2.z - v2.y*v1.z;
		y = -(v1.z*v2.x - v2.z*v1.x);
		z =   v1.x*v2.y - v2.x*v1.y;
	}
	
	public void normalizeSelf() {
		float len = length();
		x /= len;
		y /= len;
		z /= len;
	}
	
	public float length() {
		return (float)Math.sqrt(lengthSquared());
	}
	
	public float lengthSquared() {
		return x*x+y*y+z*z;
	}
	
	public void set(Vector3 v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public void setToNormalized(Vector3 v) {
		set(v);
		normalizeSelf();
	}
	
	public void setToSum(Vector3 a, Vector3 b) {
		x = a.x+b.x;
		y = a.y+b.y;
		z = a.z+b.z;
	}
	
	public void setToDifference(Vector3 a, Vector3 b) {
		x = a.x-b.x;
		y = a.y-b.y;
		z = a.z-b.z;
	}
}
