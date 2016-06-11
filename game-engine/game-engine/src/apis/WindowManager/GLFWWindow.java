package apis.WindowManager;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import dataTypes.Window;

public class GLFWWindow extends WindowType {
	private static GLFWErrorCallback errorCallback;
	private static GLFWKeyCallback keyCallback;
	
	private static GLFWVidMode mode;
	
	@Override
	public void init(){
		//Print errors to the error console
		GLFW.glfwSetErrorCallback(errorCallback=GLFWErrorCallback.createPrint(System.err));
		
		//Stuff better be setup
		if(GLFW.glfwInit()!=GLFW.GLFW_TRUE){
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		//Get the monitor
		mode=GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
	}
	
	@Override
	public void update(Window window){
		GLFW.glfwSwapBuffers(window.id);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GLFW.glfwPollEvents();
	}
	
	@Override
	public boolean testForClose(Window window){
		return GLFW.glfwWindowShouldClose(window.id)==GLFW.GLFW_FALSE;
	}
	
	@Override
	public Window create(String windowName, int height, int width){
		//Configure window
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); //Hide window- for now
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); //Let it resize!
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3); //Set stuff (version)
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE); 
		
		//Make window...
		long window=GLFW.glfwCreateWindow(width, height, windowName, MemoryUtil.NULL, MemoryUtil.NULL);
		
		//...and check that it was actually made.
		if(window==MemoryUtil.NULL){
			throw new RuntimeException("Window not made.");
		}
		
		//Key callback- ESC=close
		GLFW.glfwSetKeyCallback(window, keyCallback=new GLFWKeyCallback(){
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods){
				if(key==GLFW.GLFW_KEY_ESCAPE&&action==GLFW.GLFW_RELEASE){
					GLFW.glfwSetWindowShouldClose(window, GLFW.GLFW_TRUE);
				}
			}
		});
		
		GLFW.glfwSetWindowPos(window, 0, 0);
		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(window);
		
		GL.createCapabilities();
		
		GL11.glViewport(0, 0, width, height);
		
		return new Window(window);
	}
	
	@Override
	public void close(Window window){
		long id=window.id;
		GLFW.glfwSetWindowShouldClose(id, GLFW.GLFW_TRUE);
		GLFW.glfwDestroyWindow(id);
		keyCallback.release();
		GLFW.glfwTerminate();
		errorCallback.release();
	}
	
	@Override
	public int getScreenWidth(){
		return (int) mode.width();
	}
	
	@Override
	public int getScreenHeight(){
		return mode.height();
	}
}
