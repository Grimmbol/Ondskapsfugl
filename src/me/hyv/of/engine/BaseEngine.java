package me.hyv.of.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;

public class BaseEngine {
	
	private AbstractGame game;
	private String title;
	private int w_width, w_height;
	private boolean vsync;
	@SuppressWarnings("unused")
	private boolean fullscreen;
	
	private long windowHandle;
	
	public BaseEngine(AbstractGame game, String title, int width, int height, boolean vsync, boolean fullscreen) {
		this.game = game;
		this.title = title;
		this.w_width = width;
		this.w_height = height;
		this.vsync = vsync;
		this.fullscreen = fullscreen;
	}
	
	public void run() {
		try {
			GLFWErrorCallback.createPrint(System.err).set();
			makeWindowAndContext();
			centerWindow();
			makeKeyCallback();
			glfwMakeContextCurrent(windowHandle);
	        glfwSwapInterval(vsync?1:0);
	        GL.createCapabilities();
	        
	        game.init(this);
	        glfwShowWindow(windowHandle);
	        makeResizeCallback();
	        loop();
		} finally {
			game.destroy();
			glfwSetKeyCallback(windowHandle, null).free();
			//glfwSetFramebufferSizeCallback(windowHandle, null).free();
			glfwTerminate();
			glfwSetErrorCallback(null).free();
		}
	}
	
	private void makeWindowAndContext() {
		if(!glfwInit())
			  throw new IllegalStateException("Unable to initialize GLFW");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_SAMPLES, 8);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		windowHandle = glfwCreateWindow(w_width, w_height, title, NULL, NULL);
		if(windowHandle == NULL)
			throw new RuntimeException();
	}
	
	private void makeKeyCallback() {
		GLFWKeyCallback.create((window, key, scancode, action, mods)->{
			game.onKeyStateChange(key, action);
		}).set(windowHandle);
	}
	
	private void centerWindow() {
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		int[] w = new int[1], h = new int[1];
		glfwGetWindowSize(windowHandle, w, h);
		
        glfwSetWindowPos(
            windowHandle,
            (vidmode.width() - w[0]) / 2,
            (vidmode.height() - h[0]) / 2
        );
	}

	private void makeResizeCallback() {
		GLFWFramebufferSizeCallback.create((window, width, height)->game.resize(width, height)).set(windowHandle);
	
		int[] w = new int[1], h = new int[1];
		glfwGetFramebufferSize(windowHandle, w, h);
		
		game.resize(w[0], h[0]);
	}
	
	private void loop() {
		long prevTime = System.currentTimeMillis()-16; //16 milliseconds is a good number for the first delta time
		while(!glfwWindowShouldClose(windowHandle)) {
			Time.setDelta((int)-(prevTime-(prevTime=System.currentTimeMillis())));
			game.onFrame();
			glfwSwapBuffers(windowHandle);
			glfwPollEvents();
		}
	}
	
	public void closeWindow() {
		glfwSetWindowShouldClose(windowHandle, true);
	}
}
