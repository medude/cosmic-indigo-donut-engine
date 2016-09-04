package dataTypes.anyType;

public class AnyInt extends AnyType {
	private int intValue;

	public AnyInt(int intValue) {
		super();
		this.intValue = intValue;
	}

	@Override
	public int getInt() {
		return intValue;
	}

	@Override
	public void setInt(int intValue) {
		this.intValue = intValue;
	}

	@Override
	public boolean isInt() {
		return true;
	}
}
