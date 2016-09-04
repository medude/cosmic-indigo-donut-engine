package dataTypes.anyType;

public class AnyFloat extends AnyType {
	private float floatValue;

	public AnyFloat(float floatValue) {
		super();
		this.floatValue = floatValue;
	}

	@Override
	public float getFloat() {
		return floatValue;
	}

	@Override
	public void setFloat(float floatValue) {
		this.floatValue = floatValue;
	}

	@Override
	public boolean isFloat() {
		return true;
	}
}
