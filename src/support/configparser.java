package support;

import java.io.IOException;
import java.util.Properties;

public class configparser {
    private final Properties configFile = new Properties();

    public configparser(String fileName) throws IOException {
        String configFileName = null;
        configFileName = fileName;
        configFile.load(getClass().getResourceAsStream(configFileName));
    }

    public String getPropertyValue(String key) {
        return configFile.getProperty(key);
    }
}
