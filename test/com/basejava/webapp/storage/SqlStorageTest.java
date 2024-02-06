package com.basejava.webapp.storage;

import com.basejava.webapp.Configuration;

public class SqlStorageTest extends AbstractStorageTest{
    public SqlStorageTest() {
        super(Configuration.getInstance().getStorage());
    }
}
