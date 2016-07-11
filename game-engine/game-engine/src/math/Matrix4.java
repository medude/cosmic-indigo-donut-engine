package math;

import java.nio.FloatBuffer;

public class Matrix4 {
	
	public double[][] m = {
			{ 1, 0, 0, 0 },
			{ 0, 1, 0, 0 },
			{ 0, 0, 1, 0 },
			{ 0, 0, 0, 1 }
	};
	
	
	public void translate(Vector3 vec) {
		this.m[3][0] += this.m[0][0] * vec.x + this.m[1][0] * vec.y + this.m[2][0] * vec.z;
		this.m[3][1] += this.m[0][1] * vec.x + this.m[1][1] * vec.y + this.m[2][1] * vec.z;
		this.m[3][2] += this.m[0][2] * vec.x + this.m[1][2] * vec.y + this.m[2][2] * vec.z;
		this.m[3][3] += this.m[0][3] * vec.x + this.m[1][3] * vec.y + this.m[2][3] * vec.z;
	}
	
	public void rotate(double degrees, Vector3 axis) {
		degrees = Math.toRadians(degrees);
		double c = Math.cos(degrees);
		double s = Math.sin(degrees);
		double oneMinusC = 1 - c;
		double xy = axis.x * axis.y;
		double yz = axis.y * axis.z;
		double xz = axis.x * axis.z;
		double xs = axis.x * s;
		double ys = axis.y * s;
		double zs = axis.z * s;

		double f00 = axis.x * axis.x * oneMinusC + c;
		double f01 = xy * oneMinusC + zs;
		double f02 = xz * oneMinusC - ys;
		//n[3] not used
		double f10 = xy * oneMinusC - zs;
		double f11 = axis.y * axis.y * oneMinusC + c;
		double f12 = yz * oneMinusC + xs;
		//n[7] not used
		double f20 = xz * oneMinusC + ys;
		double f21 = yz * oneMinusC - xs;
		double f22 = axis.z * axis.z * oneMinusC + c;

		double t00 = this.m[0][0] * f00 + this.m[1][0] * f01 + this.m[2][0] * f02;
		double t01 = this.m[0][1] * f00 + this.m[1][1] * f01 + this.m[2][1] * f02;
		double t02 = this.m[0][2] * f00 + this.m[1][2] * f01 + this.m[2][2] * f02;
		double t03 = this.m[0][3] * f00 + this.m[1][3] * f01 + this.m[2][3] * f02;
		double t10 = this.m[0][0] * f10 + this.m[1][0] * f11 + this.m[2][0] * f12;
		double t11 = this.m[0][1] * f10 + this.m[1][1] * f11 + this.m[2][1] * f12;
		double t12 = this.m[0][2] * f10 + this.m[1][2] * f11 + this.m[2][2] * f12;
		double t13 = this.m[0][3] * f10 + this.m[1][3] * f11 + this.m[2][3] * f12;
		
		this.m[2][0] = this.m[0][0] * f20 + this.m[1][0] * f21 + this.m[2][0] * f22;
		this.m[2][1] = this.m[0][1] * f20 + this.m[1][1] * f21 + this.m[2][1] * f22;
		this.m[2][2] = this.m[0][2] * f20 + this.m[1][2] * f21 + this.m[2][2] * f22;
		this.m[2][3] = this.m[0][3] * f20 + this.m[1][3] * f21 + this.m[2][3] * f22;
		
		this.m[0][0] = t00;
		this.m[0][1] = t01;
		this.m[0][2] = t02;
		this.m[0][3] = t03;
		this.m[1][0] = t10;
		this.m[1][1] = t11;
		this.m[1][2] = t12;
		this.m[1][3] = t13;
	}
	
	public void scale(Vector3 vec) {
		this.m[0][0] *= vec.x;
		this.m[0][1] *= vec.x;
		this.m[0][2] *= vec.x;
		this.m[0][3] *= vec.x;
		this.m[1][0] *= vec.y;
		this.m[1][1] *= vec.y;
		this.m[1][2] *= vec.y;
		this.m[1][3] *= vec.y;
		this.m[2][0] *= vec.z;
		this.m[2][1] *= vec.z;
		this.m[2][2] *= vec.z;
		this.m[2][3] *= vec.z;
	}
	
	public void setIdentity() {
		this.m[0][0] = 1.0f;
		this.m[0][1] = 0.0f;
		this.m[0][2] = 0.0f;
		this.m[0][3] = 0.0f;
		this.m[1][0] = 0.0f;
		this.m[1][1] = 1.0f;
		this.m[1][2] = 0.0f;
		this.m[1][3] = 0.0f;
		this.m[2][0] = 0.0f;
		this.m[2][1] = 0.0f;
		this.m[2][2] = 1.0f;
		this.m[2][3] = 0.0f;
		this.m[3][0] = 0.0f;
		this.m[3][1] = 0.0f;
		this.m[3][2] = 0.0f;
		this.m[3][3] = 1.0f;
	}
	
	public void store(FloatBuffer buf) {
		buf.put((float) m[0][0]);
		buf.put((float) m[0][1]);
		buf.put((float) m[0][2]);
		buf.put((float) m[0][3]);
		buf.put((float) m[1][0]);
		buf.put((float) m[1][1]);
		buf.put((float) m[1][2]);
		buf.put((float) m[1][3]);
		buf.put((float) m[2][0]);
		buf.put((float) m[2][1]);
		buf.put((float) m[2][2]);
		buf.put((float) m[2][3]);
		buf.put((float) m[3][0]);
		buf.put((float) m[3][1]);
		buf.put((float) m[3][2]);
		buf.put((float) m[3][3]);
	}
	
	@Override
	public String toString() {
		String output = "";
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				add(output, this.m[i][j]);
				if(j < 3){
					output += ", ";
				}
			}
			
			output += "\n";
		}
		
		return output;
	}
	
	private void add(String a, double b) {
		a += String.valueOf(b);
	}
	
	public Matrix4(double m00, double m10, double m20, double m30, double m01, double m11, double m21, double m31,
			double m02, double m12, double m22, double m32, double m03, double m13, double m23, double m33) {
		this.m[0][0] = m00;
		this.m[1][0] = m10;
		this.m[2][0] = m20;
		this.m[3][0] = m30;
		this.m[0][1] = m01;
		this.m[1][1] = m11;
		this.m[2][1] = m21;
		this.m[3][1] = m31;
		this.m[0][2] = m02;
		this.m[1][2] = m12;
		this.m[2][2] = m22;
		this.m[3][2] = m32;
		this.m[0][3] = m03;
		this.m[1][3] = m13;
		this.m[2][3] = m23;
		this.m[3][3] = m33;
	}
	
	public Matrix4() {}
}
