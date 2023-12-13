package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doSave(Object index, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected Object findSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    public Resume[] getAll() {
        Resume[] res = storage.values().toArray(new Resume[0]);
        Arrays.sort(res);
        return res;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.replace((String) searchKey, r);
    }
}
