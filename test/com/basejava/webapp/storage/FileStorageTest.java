package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.ObjectStreamSerializer;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIRECTORY.getAbsoluteFile(), new ObjectStreamSerializer()));
    }
}
