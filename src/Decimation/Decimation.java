package Decimation;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.DisplayMode;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

import tools.Display;
 
public class Decimation {
    
    //The display, represents graphics window and more.
    public static Display display;
    
    //LWJGL 'Event' system to listen for keys/errors etc.
    public static GLFWKeyCallback keyCallback;
    public static GLFWErrorCallback errorCallback;
    
    //Main game loop.
    private void loop() {
    	
        Display.prepare();
        
        this.render();
        
        while(display.isCloseRequested() == GLFW.GLFW_FALSE) {
        	
            display.update();
            
        }
        
    }
    
    public void render() {
    	
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    	
    }
    
    public void run() {
    	
        try {
        	
            this.init();
            
            this.loop();
            
            this.cleanUp();
            
        } finally {
        	
        	this.stop();
            
        }
        
    }
 
    private void stop() {
    	
        GLFW.glfwTerminate();
        
        errorCallback.release();
		
	}

	private void cleanUp() {
        
        display.destroy();
        
        keyCallback.release();
		
	}

	private void init() {
		
        //Sets up error handling for GLFW.
		errorCallback = GLFWErrorCallback.createPrint(System.err);
		
        GLFW.glfwSetErrorCallback(errorCallback);
 
        // Initialize GLFW.
        if(GLFW.glfwInit() != GLFW_TRUE) {
        	
            throw new IllegalStateException("Unable to initialize GLFW");
            
        }
        
        DisplayMode usedMode = Display.getOptimalDisplayMode();
 
        // Create the window
        display = new Display(usedMode.getWidth(), usedMode.getHeight());
        
        if(display.getWindow() == NULL) {
        	
            throw new RuntimeException("Failed to create the GLFW window");
            
        }
 
        // Key listener.
        GLFW.glfwSetKeyCallback(display.getWindow(), keyCallback = new GLFWKeyCallback() {
        	
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
            	
                if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                	
                    display.setCloseRequested(GLFW_TRUE);
                    
                }
                
            }
            
        });
        
        GLFW.glfwMakeContextCurrent(display.getWindow());
        
        GLFW.glfwSwapInterval(1);
        
        display.showWindow();
        
    }
 
    public static void main(String[] args) {
        new Decimation().run();
    }
 
}