package dataTypes;

import java.util.HashMap;

import dataTypes.anyType.AnyType;

public class ConfigData {
	public int bigVersion;
	public int midVersion;
	public int lilVersion;

	public HashMap<String, AnyType> data = new HashMap<String, AnyType>();
}