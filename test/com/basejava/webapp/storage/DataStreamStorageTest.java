package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.DataStreamSerializer;

public class DataStreamStorageTest extends AbstractStorageTest {
    public DataStreamStorageTest() {
        super(new FileStorage(STORAGE_DIRECTORY, new DataStreamSerializer()));
    }
}
