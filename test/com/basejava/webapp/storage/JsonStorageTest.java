package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.JsonStreamSerializer;

public class JsonStorageTest extends AbstractStorageTest{
    public JsonStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
