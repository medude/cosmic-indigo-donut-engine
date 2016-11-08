package dataTypes;

public class AnyType<T> {
	T data;

	public AnyType(T data) {
		this.data = data;
	}

	public boolean isType(Class<?> type) {
		return data.getClass() == type;
	}

	public boolean isType(Object type) {
		return data.getClass() == type.getClass();
	}

	public T getData() {
		return data;
	}

	public void setData(T newData) {
		data = newData;
	}
}