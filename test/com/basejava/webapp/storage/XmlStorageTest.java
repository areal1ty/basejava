package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.XmlStreamSerializer;

public class XmlStorageTest extends AbstractStorageTest{
    public XmlStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
