package Utilities;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

		private Properties properties;
		private final String propertyFilePath = System.getProperty("user.dir")
				+ "\\src\\main\\resources\\config.properties";

		public ConfigFileReader() {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(propertyFilePath));
				properties = new Properties();
				try {
					properties.load(reader);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			}
		}

		public String getPropertyValue(String key) {
			String value = properties.getProperty(key);
			if (value != null)
				return value;
			else
				throw new RuntimeException("property not specified in the Configuration.properties file.");
		}

}

