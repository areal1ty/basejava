package com.basejava.webapp;

import com.basejava.webapp.storage.SqlStorage;
import com.basejava.webapp.storage.Storage;
import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class Configuration {
    private static Configuration INSTANCE;
    protected static final String CONFIG_DIR = "config\\resumes.properties";
    private static final File PROPS = new File(getHomeDirectory(), CONFIG_DIR);
    private final Storage storage;
    private final File STORAGE_DIR;

    private static File getHomeDirectory() {
        String property = System.getProperty("homeDir");
        File homeDir = new File(property == null ? "." : property);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + "isn't a directory!");
        }
        return homeDir;
    }

    public static Configuration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration();
        }
        return INSTANCE;
    }

    private Configuration() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            STORAGE_DIR = new File(props.getProperty("storage.dir"));
            String DB_URL = props.getProperty("db.url");
            String DB_USERNAME = props.getProperty("db.username");
            String DB_PASSWORD = props.getProperty("db.password");
            storage = new SqlStorage(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid configuration file" + PROPS.getAbsolutePath());
        }
    }

}
