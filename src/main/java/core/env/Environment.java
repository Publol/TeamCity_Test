package core.env;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

public enum Environment {
    INSTANCE;

    private Properties properties = new Properties();
    private final Logger logger = Logger.getLogger(Environment.class);

    private String getPropertyBy(String key) {
        String value;
        if (this.properties.isEmpty()) {
            this.logger.error("There are no properties configured");
            throw new MissingResourceException("Properties are empty", Environment.class.getName(), key);
        } else {
            value = this.properties.getProperty(key);
            if (StringUtils.isEmpty(value)) {
                this.logger.warn("The property for the key '" + key + "' is empty.");
                return null;
            } else {
                return value.trim();
            }
        }
    }

    public String getProperty(String key, String defaultValue) {
        try {
            return this.getProperty(key);
        } catch (Exception var4) {
            return defaultValue;
        }
    }

    Environment() {
        this.loadProperties();
        this.displayProperties();
    }

    private void loadProperties() {
        String env = System.getProperty("url");
        if (StringUtils.isEmpty(env)) {
            this.logger.error("'url' argument is empty");
            this.logger.error("Framework will use default URL address...");
        }
        String envFilePath = "config.properties";
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(envFilePath);
        if (in != null) {
            try {
                this.logger.info("Loading properties from " + envFilePath);
                this.properties.load(in);
            } catch (IOException var5) {
                this.logger.error("Can't load properties file: " + envFilePath, var5);
                throw new IllegalArgumentException("Can't load properties file:  " + envFilePath, var5);
            }
        } else {
            this.logger.error("Can't find file : " + envFilePath);
        }
    }

    private void displayProperties(){
        properties.entrySet().stream()
                .forEach(entry->logger.info("Property key: \"" + entry.getKey() + "\", value: " + entry.getValue()));
    }

    public static String getProperty(String key){
        return Environment.INSTANCE.getPropertyBy(key);
    }


}
