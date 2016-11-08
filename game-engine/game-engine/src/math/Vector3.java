package math;

public class Vector3 {
	public double x;
	public double y;
	public double z;

	public Vector3() {
	}

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3(double all) {
		this.x = all;
		this.y = all;
		this.z = all;
	}

	@Override
	public String toString() {
		return this.x + ", " + this.y + ", " + this.z;
	}
}