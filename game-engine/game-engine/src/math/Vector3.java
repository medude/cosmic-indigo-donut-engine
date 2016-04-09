package math;

public class Vector3 {
	public float x=0;
	public float y=0;
	public float z=0;
	
	public Vector3(){}
	
	public Vector3(float x, float y, float z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public Vector3(float all){
		this.x=all;
		this.y=all;
		this.z=all;
	}
}