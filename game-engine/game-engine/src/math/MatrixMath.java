package math;

public class MatrixMath {
	public static Matrix4 dotProduct(Matrix4 a, Matrix4 b) {
		Matrix4 newMatrix = new Matrix4();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Vector4 vector1 = new Vector4(a.m[i][0], a.m[i][1], a.m[i][2], a.m[i][3]);
				Vector4 vector2 = new Vector4(b.m[0][j], b.m[1][j], b.m[2][j], b.m[3][j]);

				double c = VectorMath.dotProduct(vector1, vector2);

				newMatrix.m[i][j] = c;
			}
		}

		return newMatrix;
	}
}
