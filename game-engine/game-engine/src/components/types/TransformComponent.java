package components.types;

import components.Component;
import math.Matrix4;
import math.TransformationMatrix;
import math.Vector3;

public class TransformComponent extends Component {
	public Matrix4 transform;

	public TransformComponent(Matrix4 transform) {
		super("TransformComponent");

		if (transform == null) {
			this.transform = TransformationMatrix.create(new Vector3(0), new Vector3(0), 1);
		} else {
			this.transform = transform;
		}
	}

}
