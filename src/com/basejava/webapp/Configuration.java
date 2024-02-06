package com.basejava.webapp;

import com.basejava.webapp.storage.SqlStorage;
import com.basejava.webapp.storage.Storage;
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
    private final Storage storage;
    @Getter
    private final File STORAGE_DIR;
    @Getter
    private final String DB_URL;
    @Getter
    private final String DB_USERNAME;
    @Getter
    private final String DB_PASSWORD;

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
            DB_URL = props.getProperty("db.url");
            DB_USERNAME = props.getProperty("db.username");
            DB_PASSWORD = props.getProperty("db.password");
            storage = new SqlStorage(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid configuration file" + CONFIG_DIR.getAbsolutePath());
        }
    }
}
