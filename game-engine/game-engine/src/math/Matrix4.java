package math;

public class Matrix4 {
	
	
	public float m00=1;	public float m10=0;	public float m20=0;	public float m30=0;
	
	
	public float m01=0;	public float m11=1;	public float m21=0;	public float m31=0;
	
	
	public float m02=0;	public float m12=0;	public float m22=1;	public float m32=0;
	
	
	public float m03=0;	public float m13=0;	public float m23=0;	public float m33=1;
	
	
	public void translate(Vector3 vec){
		this.m30+=this.m00*vec.x+this.m10*vec.y+this.m20*vec.z;
		this.m31+=this.m01*vec.x+this.m11*vec.y+this.m21*vec.z;
		this.m32+=this.m02*vec.x+this.m12*vec.y+this.m22*vec.z;
		this.m33+=this.m03*vec.x+this.m13*vec.y+this.m23*vec.z;
	}
	
	public void rotate(float degrees, Vector3 axis){
		degrees=(float) Math.toRadians(degrees);
		float c=(float) Math.cos(degrees);
		float s=(float) Math.sin(degrees);
		float oneMinusC=1-c;
		float xy=axis.x*axis.y;
		float yz=axis.y*axis.z;
		float xz=axis.x*axis.z;
		float xs=axis.x*s;
		float ys=axis.y*s;
		float zs=axis.z*s;

		float f00=axis.x*axis.x*oneMinusC+c;
		float f01=xy*oneMinusC+zs;
		float f02=xz*oneMinusC-ys;
		//n[3] not used
		float f10=xy*oneMinusC-zs;
		float f11=axis.y*axis.y*oneMinusC+c;
		float f12=yz*oneMinusC+xs;
		//n[7] not used
		float f20=xz*oneMinusC+ys;
		float f21=yz*oneMinusC-xs;
		float f22=axis.z*axis.z*oneMinusC+c;

		float t00=this.m00*f00+this.m10*f01+this.m20*f02;
		float t01=this.m01*f00+this.m11*f01+this.m21*f02;
		float t02=this.m02*f00+this.m12*f01+this.m22*f02;
		float t03=this.m03*f00+this.m13*f01+this.m23*f02;
		float t10=this.m00*f10+this.m10*f11+this.m20*f12;
		float t11=this.m01*f10+this.m11*f11+this.m21*f12;
		float t12=this.m02*f10+this.m12*f11+this.m22*f12;
		float t13=this.m03*f10+this.m13*f11+this.m23*f12;
		this.m20=this.m00*f20+this.m10*f21+this.m20*f22;
		this.m21=this.m01*f20+this.m11*f21+this.m21*f22;
		this.m22=this.m02*f20+this.m12*f21+this.m22*f22;
		this.m23=this.m03*f20+this.m13*f21+this.m23*f22;
		this.m00=t00;
		this.m01=t01;
		this.m02=t02;
		this.m03=t03;
		this.m10=t10;
		this.m11=t11;
		this.m12=t12;
		this.m13=t13;
	}
	
	public void scale(Vector3 vec){
		this.m00*=vec.x;
		this.m01*=vec.x;
		this.m02*=vec.x;
		this.m03*=vec.x;
		this.m10*=vec.y;
		this.m11*=vec.y;
		this.m12*=vec.y;
		this.m13*=vec.y;
		this.m20*=vec.z;
		this.m21*=vec.z;
		this.m22*=vec.z;
		this.m23*=vec.z;
	}
	
	public void setIdentity(){
		this.m00=1.0f;
		this.m01=0.0f;
		this.m02=0.0f;
		this.m03=0.0f;
		this.m10=0.0f;
		this.m11=1.0f;
		this.m12=0.0f;
		this.m13=0.0f;
		this.m20=0.0f;
		this.m21=0.0f;
		this.m22=1.0f;
		this.m23=0.0f;
		this.m30=0.0f;
		this.m31=0.0f;
		this.m32=0.0f;
		this.m33=1.0f;
	}
	
	public Matrix4(float m00, float m10, float m20, float m30, float m01, float m11, float m21, float m31, float m02,
			float m12, float m22, float m32, float m03, float m13, float m23, float m33){
		this.m00=m00;
		this.m10=m10;
		this.m20=m20;
		this.m30=m30;
		this.m01=m01;
		this.m11=m11;
		this.m21=m21;
		this.m31=m31;
		this.m02=m02;
		this.m12=m12;
		this.m22=m22;
		this.m32=m32;
		this.m03=m03;
		this.m13=m13;
		this.m23=m23;
		this.m33=m33;
	}
	
	public Matrix4(){}
}
