package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isValid(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        } else return true;
    }

    @Override
    protected void saveElement(Resume r) {
        storage.add(r);
    }

    @Override
    protected final void removeAll() {
        storage.clear();
    }

    @Override
    protected void removeElement(int index) {
        storage.remove(index);
    }

    @Override
    protected int findIndex(String uuid) {
        Resume r = get(uuid);
        return storage.indexOf(r);
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public final Resume[] getAll() {
        Resume[] storageArray = new Resume[storage.size()];
        return storage.toArray(storageArray);
    }

    @Override
    protected void replace(int index, Resume r) {
        storage.set(index, r);
    }
}
