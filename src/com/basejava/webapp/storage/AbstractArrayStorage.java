package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public final void doSave(Integer index, Resume r) {
        String uuid = r.getUuid();
        if (size < STORAGE_LIMIT) {
            insert(index, r);
            size++;
        } else {
            throw new StorageException("Storage overflow", uuid);
        }
    }

    @Override
    public final void doUpdate(Integer searchKey, Resume r) {
        storage[ searchKey] = r;
    }

    @Override
    public final void doDelete(Integer searchKey) {
        fillEmpty( searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public final void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    public final boolean isExist(Integer searchKey) {
        return (searchKey >= 0);
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
