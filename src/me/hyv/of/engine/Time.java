package me.hyv.of.engine;

public class Time {
	private static float deltaSeconds;
	
	public static void setDelta(double seconds) {
		if(seconds > 0.04)
			return;
		deltaSeconds = (float) seconds;
	}
	
	public static float getDelta() {
		return deltaSeconds;
	}
}
