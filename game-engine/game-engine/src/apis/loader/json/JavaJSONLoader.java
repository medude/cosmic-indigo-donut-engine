package apis.loader.json;

import apis.loader.Loader;
import dataTypes.TextFile;
import exceptions.MalformedFileException;
import externalLibraries.minimalJson.main.Json;
import externalLibraries.minimalJson.main.JsonObject;

public class JavaJSONLoader {
	public JsonObject processJSON(String filename) throws MalformedFileException {
		TextFile file = Loader.loadFile(filename);

		String json = "";

		for (String line : file.getLines()) {
			json += line + "\n";
		}

		JsonObject jsonObject = Json.parse(json).asObject();

		return jsonObject;
	}
}
