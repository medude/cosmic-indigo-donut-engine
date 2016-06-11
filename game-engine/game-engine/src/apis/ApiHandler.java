package apis;

import apis.WindowManager.GLFWWindow;
import apis.WindowManager.NullWindow;
import apis.WindowManager.WindowType;
import apis.loader.JavaFileLoader;
import apis.loader.LoaderType;
import apis.loader.NullFileLoader;
import apis.renderer.NullRenderer;
import apis.renderer.OpenGLRenderer;
import apis.renderer.RendererType;
import dataTypes.ModelData;
import services.console.CoreConsole;
import services.console.JavaConsole;
import services.console.NullConsole;
import services.errorHandler.CoreErrorHandler;
import services.errorHandler.JavaErrorHandler;
import services.errorHandler.NullErrorHandler;
import services.shaders.CoreShader;
import services.shaders.GLSLShader;
import services.shaders.NullShader;

public class ApiHandler {
		public static void init(){
			consoles[0]=new JavaConsole();
			consoles[1]=new NullConsole();
			
			errorHandlers[0]=new JavaErrorHandler();
			errorHandlers[1]=new NullErrorHandler();
			
			windows[0]=new GLFWWindow();
			windows[1]=new NullWindow();
			
			windows[0].init();
			
			loaders[0]=new JavaFileLoader();
			loaders[1]=new NullFileLoader();
			
			renderers[0]=new OpenGLRenderer();
			renderers[1]=new NullRenderer();
			
			shaders[0]=new GLSLShader();
			shaders[1]=new NullShader();
		}
		
		public static void cleanup(){
			ModelData data=new ModelData(new float[0], new int[0]);
			data.cleanup();
			
			getShader().cleanup();
			getLoader().cleanup();
		}
		
		//////////////////////////////////
		//Error Handlers                //
		//////////////////////////////////
		private static CoreErrorHandler[] errorHandlers=new CoreErrorHandler[2];
		
		public static CoreErrorHandler getErrorHandler(){
			return errorHandlers[0];
		}
		
		//////////////////////////////////
		//Consoles                      //
		//////////////////////////////////
		private static CoreConsole[] consoles=new CoreConsole[2];
		
		public static CoreConsole getConsole(){
			return consoles[0];
		}
		
		//////////////////////////////////
		//Windows                       //
		//////////////////////////////////
		private static WindowType[] windows=new WindowType[2];
		
		public static WindowType getWindow(){
			return windows[0];
		}
		
		//////////////////////////////////
		//Loaders                       //
		//////////////////////////////////
		private static LoaderType[] loaders=new LoaderType[2];
		
		public static LoaderType getLoader(){
			return loaders[0];
		}
		
		//////////////////////////////////
		//Renderer                      //
		//////////////////////////////////
		private static RendererType[] renderers=new RendererType[2];
		
		public static RendererType getRenderer(){
			renderers[0].init();
			return renderers[0];
		}
		
		//////////////////////////////////
		//Shader                        //
		//////////////////////////////////
		private static CoreShader[] shaders=new CoreShader[2];
		
		public static CoreShader getShader(){
			return shaders[0];
		}
}
