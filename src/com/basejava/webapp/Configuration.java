package com.basejava.webapp;

import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Configuration INSTANCE;
    protected static final File CONFIG_DIR = new File("./config/resumes.properties");
    private final Properties props = new Properties();
    @Getter
    private final File STORAGE_DIR;
    @Getter
    private final File DB_URL;
    @Getter
    private final File DB_USERNAME;
    @Getter
    private final File DB_PASSWORD;

    public static Configuration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration();
        }
        return INSTANCE;
    }
    private Configuration() {
        try (InputStream is = new FileInputStream(CONFIG_DIR)) {
            props.load(is);
            STORAGE_DIR = new File(props.getProperty("storage.dir"));
            DB_URL = new File(props.getProperty("db.url"));
            DB_USERNAME = new File(props.getProperty("db.username"));
            DB_PASSWORD = new File(props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid configuration file" + CONFIG_DIR.getAbsolutePath());
        }
    }
}
