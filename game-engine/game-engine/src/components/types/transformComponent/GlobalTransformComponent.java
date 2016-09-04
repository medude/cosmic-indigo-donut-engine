package components.types.transformComponent;

import components.types.TransformComponent;
import math.Matrix4;

public class GlobalTransformComponent extends TransformComponent {

	public GlobalTransformComponent(Matrix4 transform) {
		super(transform, "GlobalTransformComponent");
	}

}
