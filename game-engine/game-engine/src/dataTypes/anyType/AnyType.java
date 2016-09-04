package dataTypes.anyType;

public abstract class AnyType {
	public AnyType() {
	}

	public boolean getBool() {
		throw new UnsupportedOperationException("Is not a bool- cannot get bool.");
	}

	public void setBool(boolean boolValue) {
		throw new UnsupportedOperationException("Is not a bool- cannot set bool.");
	}

	public boolean isBool() {
		return false;
	}

	public float getFloat() {
		throw new UnsupportedOperationException("Is not a float- cannot get a float.");
	}

	public void setFloat(float floatValue) {
		throw new UnsupportedOperationException("Is not a float- cannot set a float.");
	}

	public boolean isFloat() {
		return false;
	}

	public double getDouble() {
		throw new UnsupportedOperationException("Is not a double- cannot get a double.");
	}

	public void setDouble(double doubleValue) {
		throw new UnsupportedOperationException("Is not a double- cannot set a double.");
	}

	public boolean isDouble() {
		return false;
	}

	public int getInt() {
		throw new UnsupportedOperationException("Is not an int- cannot get an int.");
	}

	public void setInt(int intValue) {
		throw new UnsupportedOperationException("Is not an int- cannot set an int.");
	}

	public boolean isInt() {
		return false;
	}

	public String getString() {
		throw new UnsupportedOperationException("Is not a String- cannot get a String.");
	}

	public void setString(String stringValue) {
		throw new UnsupportedOperationException("Is not a String- cannot set a String.");
	}

	public boolean isString() {
		return false;
	}

	public AnyType[] getArray() {
		throw new UnsupportedOperationException("Is not an array- cannot get an array.");
	}

	public void setArray(AnyType[] arrayValue) {
		throw new UnsupportedOperationException("Is not an array- cannot set an array.");
	}

	public boolean isArray() {
		return false;
	}
}
