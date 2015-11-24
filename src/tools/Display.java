package tools;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

public class Display {
	
	private long window;
	
	public Display(int width, int height) {
		
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		this.window = GLFW.glfwCreateWindow(width, height, "Decimation", GLFW.glfwGetPrimaryMonitor(), NULL);
		
	}
	
	public void showWindow() {
		
		GLFW.glfwShowWindow(window);
		
	}
	
	public void hideWindow() {
		
		GLFW.glfwHideWindow(window);
		
	}
	
	public long getWindow() {
		
		return window;
		
	}
	
	public void destroy() {
		
		GLFW.glfwDestroyWindow(window);
		
	}
	
	public int isCloseRequested() {
		
		return GLFW.glfwWindowShouldClose(window);
		
	}
	
	public void setCloseRequested(int glfwValue) {
		
		GLFW.glfwSetWindowShouldClose(window, glfwValue);
		
	}
	
	public void update() {
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		 
        glfwSwapBuffers(window);
        
        glfwPollEvents();
		
	}
	
	public static void prepare() {
		
		GL.createCapabilities();
		
	}
	
	public static DisplayMode[] getDisplayModes() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		GraphicsDevice myDevice = ge.getDefaultScreenDevice();
        DisplayMode[] modes = myDevice.getDisplayModes();
		
		return modes;
		
	}
	
	public static DisplayMode getOptimalDisplayMode() {
		
		DisplayMode[] modes = Display.getDisplayModes();
		
        DisplayMode usedMode = modes[0];
		
		for(int i = 0 ; i < modes.length ; i++) {
			
			if(modes[i].getHeight() > usedMode.getHeight()) {
				
				if(modes[i].getWidth() >= usedMode.getWidth()) {
					
					usedMode = modes[i];
					
				}
				
			}
			
			if(modes[i].getWidth() > usedMode.getWidth()) {
				
				if(modes[i].getHeight() >= usedMode.getHeight()) {
					
					usedMode = modes[i];
					
				}
				
			}
			
		}
		
		return usedMode;
		
	}

}
