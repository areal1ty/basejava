package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage{
    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }
}
