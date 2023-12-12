package com.basejava.webapp.storage;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public final void saveElement(Object index, Resume r) {
        String uuid = r.getUuid();
        if (size < STORAGE_LIMIT) {
            insert((Integer) index, r);
            size++;
        } else {
            throw new StorageException("Storage overflow", uuid);
        }

        }

    @Override
    public final void replace(int index, Resume r) {
        storage[index] = r;
    }

    @Override
    public final void removeElement(int index) {
        fillEmpty(index);
        size--;
    }

    @Override
    public final void removeAll() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final Resume getResume(int index) {
        return storage[index];
    }

    @Override
    public final boolean isExist(Integer index) {
        return (index >= 0);
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void insert(int index, Resume r);

    protected abstract Integer findIndex(String uuid);

    protected abstract void fillEmpty(int index);
}
