package apis.loader.config;

import apis.console.Console;
import apis.errorHandler.ErrorHandler;
import apis.loader.Loader;
import dataTypes.AnyType;
import dataTypes.ConfigData;
import dataTypes.TextFile;
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
			ErrorHandler.handle(e);
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
			AnyType<Object> anyType = null;
			anyType = jsonValue(jsonValue);

			data.data.put(line, anyType);
		}

		return data;
	}

	private AnyType<Object> jsonValue(JsonValue jsonValue) {
		AnyType<Object> anyType = null;

		// We need this annoying if structure because of the way JsonValue works- don't remove unless library changes!
		if (jsonValue.isBoolean()) {
			anyType = new AnyType<Object>(jsonValue.asBoolean());
		} else if (jsonValue.isNumber()) {
			anyType = new AnyType<Object>(jsonValue.asDouble());
		} else if (jsonValue.isString()) {
			anyType = new AnyType<Object>(jsonValue.asString());
		} else if (jsonValue.isArray()) {
			anyType = jsonArray(jsonValue);
		}

		return anyType;
	}

	private AnyType<Object> jsonArray(JsonValue jsonValue) {
		JsonArray jsonArray = jsonValue.asArray();

		@SuppressWarnings("unchecked")
		AnyType<Object>[] array = new AnyType[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			array[i] = jsonValue(jsonArray.get(i));
		}

		return new AnyType<Object>(array);
	}
}
