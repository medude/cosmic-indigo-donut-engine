package math;

import apis.WindowManager.WindowManager;

public class ProjectionMatrix {
	//Imagine the name without the X. (This is needed to access them as globals in a static context.)
	private static float nearPlanex;
	private static float farPlanex;
	private static float fovx;
	
	public static Matrix4 create(){
		return create(70, 0.01f, 1000);
	}
	
	public static Matrix4 create(float fov, float nearPlane, float farPlane){
		nearPlanex=nearPlane;
		farPlanex=farPlane;
		fovx=fov;
		
		float aspectRatio=(float) WindowManager.getWidth() / (float) WindowManager.getHeight();
		float yScale=(float) (1f/Math.tan(Math.toRadians(fov/2f)))*aspectRatio;
		float xScale=yScale/aspectRatio;
		float frustumLength=farPlane-nearPlane;
		
		Matrix4 matrix=new Matrix4();
		
		matrix.m00=xScale;
		matrix.m11=yScale;
		matrix.m22=-((farPlane+nearPlane)/frustumLength);
		matrix.m23=-1;
		matrix.m32=-((2*nearPlane*farPlane)/frustumLength);
		matrix.m33=0;
		
		return matrix;
	}

	public static float getNearPlane(){
		return nearPlanex;
	}

	public static float getFarPlane(){
		return farPlanex;
	}

	public static float getFov(){
		return fovx;
	}
}