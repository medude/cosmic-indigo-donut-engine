package apis.config;

import apis.ApiHandler;

public class Config {
    private static ConfigType configObject = ApiHandler.getConfig();

    public static ConfigData readFile(String location) {
	return configObject.readFile(location);
    }
}
