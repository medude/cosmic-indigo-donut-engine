package apis.config;

import apis.loader.Loader;
import dataTypes.TextFile;

public class JavaConfig implements ConfigType {
	@Override
	public ConfigData readFile(String location) {
		TextFile configFile = Loader.loadFile("config/" + location);
		ConfigData data = new ConfigData();
		
		while(true) {
			String line = configFile.getNextLine();
			
			if(line == null) {
				break;
				
			} else if(line.startsWith("#")) {
				continue;
				
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
