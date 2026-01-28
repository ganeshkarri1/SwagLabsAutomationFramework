package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	static Properties properties;
	static {
		//Object of Properties class to read based on keys
				properties=new Properties();
				//Reading the properties file
				FileInputStream file = null;
				try {
					file = new FileInputStream("./src/test/resources/config/config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("Properties FileNotFound...");
				}
				//Loading the properties file
				try {
					properties.load(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("Properties couldnt be loaded...");
				}	
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("File cannot be closed...");
				}
				//Returning the value based on key passed
	}
	public static String getProperty(String key) {
		
		String value = properties.getProperty(key);

	    if (value == null) {
	        throw new RuntimeException(
	            "Configuration key '" + key + "' is missing in config.properties"
	        );
	    }

	    if (value.trim().isEmpty()) {
	        throw new RuntimeException(
	            "Configuration key '" + key + "' is empty in config.properties"
	        );
	    }

	    return value.trim();
	}

}

