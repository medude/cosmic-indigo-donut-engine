package dataTypes.anyType;

public class AnyString extends AnyType {
	private String stringValue;

	public AnyString(String stringValue) {
		super();
		this.stringValue = stringValue;
	}

	@Override
	public String getString() {
		return stringValue;
	}

	@Override
	public void setString(String stringValue) {
		this.stringValue = stringValue;
	}

	@Override
	public boolean isString() {
		return true;
	}
}
