package dataTypes.anyType;

public class AnyBool extends AnyType {
	private boolean boolValue;

	public AnyBool(boolean boolValue) {
		super();
		this.boolValue = boolValue;
	}

	@Override
	public boolean getBool() {
		return boolValue;
	}

	@Override
	public void setBool(boolean boolValue) {
		this.boolValue = boolValue;
	}

	@Override
	public boolean isBool() {
		return true;
	}
}
