package apis;

import apis.WindowManager.GLFWWindow;
import apis.WindowManager.NullWindow;
import apis.WindowManager.WindowType;
import apis.console.ConsoleType;
import apis.console.JavaConsole;
import apis.console.NullConsole;
import apis.errorHandle.ErrorHandleType;
import apis.errorHandle.JavaErrorHandler;
import apis.errorHandle.NullErrorHandler;
import apis.loader.JavaFileLoader;
import apis.loader.LoaderType;
import apis.loader.NullFileLoader;
import apis.renderer.NullRenderer;
import apis.renderer.OpenGLRenderer;
import apis.renderer.RendererType;
import dataTypes.ModelData;
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
		private static ErrorHandleType[] errorHandlers=new ErrorHandleType[2];
		
		public static ErrorHandleType getErrorHandler(){
			return errorHandlers[0];
		}
		
		//////////////////////////////////
		//Consoles                      //
		//////////////////////////////////
		private static ConsoleType[] consoles=new ConsoleType[2];
		
		public static ConsoleType getConsole(){
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
