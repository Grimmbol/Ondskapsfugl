package me.hyv.of.engine;

public class Time {
	private static int deltaMillis;
	private static float deltaSeconds;
	
	public static void setDelta(long millis) {
		deltaMillis = (int)millis;
		deltaSeconds = deltaMillis / 1000f;
	}
	
	public static int getDeltaMillis() {
		return deltaMillis;
	}
	
	public static float getDelta() {
		return deltaSeconds;
	}
}
