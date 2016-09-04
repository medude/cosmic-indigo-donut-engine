package math;

public class TransformationMatrix {
	public static Matrix4 create(Vector3 translation, Vector3 rotation, float scale) {
		Matrix4 matrix = new Matrix4();
		matrix.translate(translation);
		matrix.rotate(rotation.x, Nums.X_AXIS);
		matrix.rotate(rotation.y, Nums.Y_AXIS);
		matrix.rotate(rotation.z, Nums.Z_AXIS);
		matrix.scale(new Vector3(scale));
		return matrix;
	}
}