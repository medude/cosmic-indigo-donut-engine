package math;

public class VectorMath {
	//Vector2
	public static Vector2 add(Vector2 a, Vector2 b){
		Vector2 c=new Vector2();
		
		c.x=a.x+b.x;
		c.y=a.y+b.y;
		
		return c;
	}
	
	public static Vector2 subtract(Vector2 a, Vector2 b){
		Vector2 c=new Vector2();
		
		c.x=a.x-b.x;
		c.y=a.y-b.y;
		
		return c;
	}
	
	//Vector3
	public static Vector3 add(Vector3 a, Vector3 b){
		Vector3 c=new Vector3();
		
		c.x=a.x+b.x;
		c.y=a.y+b.y;
		c.z=a.z+b.z;
		
		return c;
	}
	
	public static Vector3 subtract(Vector3 a, Vector3 b){
		Vector3 c=new Vector3();
		
		c.x=a.x-b.x;
		c.y=a.y-b.y;
		c.z=a.z-b.z;
		
		return c;
	}
	
	//Vector4
	public static Vector4 add(Vector4 a, Vector4 b){
		Vector4 c=new Vector4();
		
		c.x=a.x+b.x;
		c.y=a.y+b.y;
		c.z=a.z+b.z;
		c.w=a.w+b.w;
		
		return c;
	}
	
	public static Vector4 subtract(Vector4 a, Vector4 b){
		Vector4 c=new Vector4();
		
		c.x=a.x-b.x;
		c.y=a.y-b.y;
		c.z=a.z-b.z;
		c.w=a.w-b.w;
		
		return c;
	}
}
