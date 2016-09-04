package apis.loader.config;

import apis.console.Console;
import apis.errorHandle.ErrorHandle;
import apis.loader.Loader;
import dataTypes.ConfigData;
import dataTypes.TextFile;
import dataTypes.anyType.AnyArray;
import dataTypes.anyType.AnyBool;
import dataTypes.anyType.AnyDouble;
import dataTypes.anyType.AnyString;
import dataTypes.anyType.AnyType;
import externalLibraries.minimalJson.main.Json;
import externalLibraries.minimalJson.main.JsonArray;
import externalLibraries.minimalJson.main.JsonObject;
import externalLibraries.minimalJson.main.JsonValue;

public class JavaConfigLoader {
	public ConfigData loadConfig(String url) {
		TextFile configFile = null;
		try {
			configFile = Loader.loadFile(url);
		} catch (Throwable e) {
			ErrorHandle.handle(e);
		}

		String jsonText = "";

		for (String line : configFile.getLines()) {
			jsonText += line + "\n";
		}

		JsonObject json = Json.parse(jsonText).asObject();

		ConfigData data = jsonObject(json);

		String[] version = json.get("version").asString().substring(1).split("\\.");
		data.bigVersion = Integer.parseInt(version[0]);
		data.midVersion = Integer.parseInt(version[1]);
		data.lilVersion = Integer.parseInt(version[2]);

		return data;

	}

	private ConfigData jsonObject(JsonObject jsonObject) {
		ConfigData data = new ConfigData();

		for (String line : jsonObject.getAllNames()) {
			Console.log(line);
			if (line == "version") {
				break;
			}
			
			JsonValue jsonValue = jsonObject.get(line);
			AnyType anyType = null;
			anyType = jsonValue(jsonValue);

			data.data.put(line, anyType);
		}

		return data;
	}

	private AnyType jsonValue(JsonValue jsonValue) {
		AnyType anyType = null;

		if (jsonValue.isBoolean()) {
			anyType = new AnyBool(jsonValue.asBoolean());
		} else if (jsonValue.isNumber()) {
			anyType = new AnyDouble(jsonValue.asDouble());
		} else if (jsonValue.isString()) {
			anyType = new AnyString(jsonValue.asString());
		} else if (jsonValue.isArray()) {
			anyType = jsonArray(jsonValue);
		}

		return anyType;
	}

	private AnyArray jsonArray(JsonValue jsonValue) {
		JsonArray jsonArray = jsonValue.asArray();

		AnyType[] array = new AnyType[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			array[i] = jsonValue(jsonArray.get(i));
		}

		return new AnyArray(array);
	}
}
