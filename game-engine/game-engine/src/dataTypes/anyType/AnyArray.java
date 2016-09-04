package dataTypes.anyType;

public class AnyArray extends AnyType {
	private AnyType[] arrayValue;

	public AnyArray(AnyType[] arrayValue) {
		super();
		this.arrayValue = arrayValue;
	}

	@Override
	public AnyType[] getArray() {
		return arrayValue;
	}

	@Override
	public void setArray(AnyType[] arrayValue) {
		this.arrayValue = arrayValue;
	}

	@Override
	public boolean isArray() {
		return true;
	}
}
