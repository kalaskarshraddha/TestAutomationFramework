package org.apiautomation.utilis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

    public static String getDataFromPropertiesFile(String propertyFileName, String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/" + propertyFileName + ".properties");
        properties.load(fis);
        return properties.getProperty(key);

    }
}
