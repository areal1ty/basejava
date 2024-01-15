package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}
