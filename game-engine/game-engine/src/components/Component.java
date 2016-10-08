package components;

import dataTypes.AnyType;

public class Component {
	private AnyType<Object>[] data;
	private ComponentType type;

	public Component(AnyType<Object>[] data, ComponentType type) {
		this.data = data;
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public Component(AnyType<Object> data, ComponentType type) {
		this.data = new AnyType[] { data };
		this.type = type;
	}

	public Component(ComponentType type) {
		this.data = null;
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public Component(Object data, ComponentType type) {
		this.data = new AnyType[] { new AnyType<Object>(data) };
		this.type = type;
	}

	public AnyType<Object>[] getData() {
		return data;
	}

	public AnyType<Object> getData(int i) {
		return data[i];
	}

	public ComponentType getType() {
		return type;
	}
}