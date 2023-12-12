package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected void saveElement(Object index, Resume r) {
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
    protected Integer findIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) return i;
        }
        return null;
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    public final boolean isExist(Integer index) {
        return (index != null);
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
