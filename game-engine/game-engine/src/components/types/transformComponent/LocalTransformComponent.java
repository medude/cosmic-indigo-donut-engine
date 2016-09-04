package components.types.transformComponent;

import components.types.TransformComponent;
import math.Matrix4;

public class LocalTransformComponent extends TransformComponent {

	public LocalTransformComponent(Matrix4 transform) {
		super(transform, "LocalTransformComponent");
	}

}
