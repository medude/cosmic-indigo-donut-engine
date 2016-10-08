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

	public T data() {
		return data;
	}

	public void data(T newData) {
		data = newData;
	}
}