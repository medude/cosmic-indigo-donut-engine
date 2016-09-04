package dataTypes.anyType;

public class AnyDouble extends AnyType {
	private double doubleValue;

	public AnyDouble(double doubleValue) {
		super();
		this.doubleValue = doubleValue;
	}

	@Override
	public double getDouble() {
		return doubleValue;
	}

	@Override
	public void setDouble(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}
