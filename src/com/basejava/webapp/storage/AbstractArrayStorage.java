package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    @Override
    public final void doSave(Object index, Resume r) {
        String uuid = r.getUuid();
        if (size < STORAGE_LIMIT) {
            insert((Integer) index, r);
            size++;
        } else {
            throw new StorageException("Storage overflow", uuid);
        }
    }

    @Override
    public final void doUpdate(Object searchKey, Resume r) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    public final void doDelete(Object searchKey) {
        fillEmpty((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public final void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    public final boolean isExist(Object searchKey) {
        return ((Integer) searchKey >= 0);
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size()));
    }

    protected abstract void insert(int index, Resume r);

    protected abstract Integer findSearchKey(String searchKey);

    protected abstract void fillEmpty(int index);
}
