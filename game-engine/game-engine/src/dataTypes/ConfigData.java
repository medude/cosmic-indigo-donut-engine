package dataTypes;

import java.util.HashMap;

public class ConfigData {
	public int bigVersion;
	public int midVersion;
	public int lilVersion;

	public HashMap<String, AnyType<Object>> data = new HashMap<String, AnyType<Object>>();
}