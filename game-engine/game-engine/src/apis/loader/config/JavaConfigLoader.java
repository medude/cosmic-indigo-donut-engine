package apis.loader.config;

import apis.errorHandle.ErrorHandle;
import apis.loader.Loader;
import dataTypes.ConfigData;
import dataTypes.TextFile;

public class JavaConfigLoader {
    public static ConfigData loadConfig(String url) {
	TextFile configFile = null;
	try {
	    configFile = Loader.loadFile(url);
	} catch (Throwable e) {
	    ErrorHandle.handle(e);
	}

	ConfigData data = new ConfigData();

	while (true) {
	    String line = configFile.getNextLine();

	    if (line == null) {
		break;

	    } else if (line.startsWith("#")) {
		continue;

	    } else if (line.startsWith("v.")) {
		String[] version = line.split("\\.");

		data.bigVersion = Integer.parseInt(version[1]);
		data.midVersion = Integer.parseInt(version[2]);
		data.lilVersion = Integer.parseInt(version[3]);

	    } else if (line.length() == 0) {

	    } else {
		String[] pair = new String[2];

		pair[0] = line;
		line = configFile.getNextLine();
		pair[1] = line;

		data.data.put(pair[0], pair[1]);
	    }
	}

	return data;
    }
}
