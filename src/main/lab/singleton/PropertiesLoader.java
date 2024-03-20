package main.lab.singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static volatile PropertiesLoader instance;
    private static Properties properties;
    private final static String PROPERTIES_FILE_NAME = "config.properties";

    private PropertiesLoader(){}

    public Properties getProperties(){
        return properties;
    }

    public static PropertiesLoader getInstance(){
        return getInstanceSynchronized();
    }

    private static PropertiesLoader getInstanceSynchronized(){
        PropertiesLoader localInstance = instance;
        if(localInstance == null){
            synchronized (PropertiesLoader.class) {
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new PropertiesLoader();
                    properties = loadProperties();
                }
            }
        }
        return localInstance;
    }

    private static Properties loadProperties(){
        properties = new Properties();
        try (InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Property file '"+ PROPERTIES_FILE_NAME +"' not found");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
        return properties;
    }
}
