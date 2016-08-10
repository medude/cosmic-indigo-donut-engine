package apis.loader.json;

import java.util.HashMap;

import apis.console.Console;
import apis.loader.Loader;
import dataTypes.TextFile;
import exceptions.MalformedFileException;

public class JavaJSONLoader {
	public void processJSON(String filename) throws MalformedFileException {
		TextFile file = Loader.loadFile(filename);

		String json = "";

		for (String line : file.getLines()) {
			json += line;
		}

		json = json.replaceAll("\\s", "");

		Console.log(json);
		char[] jsonChar = json.toCharArray();

		int currentChar = 0;

		HashMap<String, Object> pairs = new HashMap<String, Object>();
		String key = null;
		Object value = null;

		while (true) {
			switch (jsonChar[currentChar]) {
			case '{':
				currentChar++;

				key = processKey(jsonChar, currentChar, filename);
				break;

			case ':':
				currentChar++;

				value = processValue(jsonChar, currentChar, filename);

				pairs.put(key, value);
				break;
			}

			currentChar++;
		}
	}

	private Object processValue(char[] jsonChar, Integer currentChar, String filename) throws MalformedFileException {
		Object value = null;
		// Is a string
		if (jsonChar[currentChar] == '"') {
			value = getString(jsonChar, currentChar);
			// Is true/false
		} else if (jsonChar[currentChar] == 't' || jsonChar[currentChar] == 'f') {
			if (jsonChar[currentChar] == 't') {
				value = true;
				currentChar += 4;
			}
			// Is a number
		} else if (String.copyValueOf(new char[] { jsonChar[currentChar] }).matches("^[0-9]")) {
			value = getNumber(jsonChar, currentChar);
		} else {
			throw new MalformedFileException(
					"File " + filename + " is missing a value after a key/the value is formatted incorrectly.");
		}

		return value;
	}

	private String processKey(char[] jsonChar, Integer currentChar, String filename) throws MalformedFileException {
		String key = "";

		if (jsonChar[currentChar] == '"') {
			key = getString(jsonChar, currentChar);

		} else if (jsonChar[currentChar] != '"') {
			throw new MalformedFileException("File " + filename
					+ " either didn't quote a key properly and needs to use double quotes (\"<key>\"),"
					+ "is missing a key , or is empty.");
		}

		return key;
	}

	private double getNumber(char[] jsonChar, Integer currentChar) {
		String numberS = "";

		while (String.copyValueOf(new char[] { jsonChar[currentChar] }).matches("^[0-9]")) {
			numberS += jsonChar[currentChar];

			currentChar++;
		}

		return Double.parseDouble(numberS);
	}

	private String getString(char[] jsonChar, Integer currentChar) {
		String string = "";

		while (jsonChar[++currentChar] != '"') {
			string += jsonChar[currentChar];
		}

		return string;
	}
}
