package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
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
        storage[(int) searchKey] = r;
    }

    @Override
    public final void doDelete(Object searchKey) {
        fillEmpty((Integer) searchKey);
        size--;
    }

    @Override
    public final void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    public final boolean isExist(Object searchKey) {
        return ((int) searchKey >= 0);
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final List<Resume> sortResumes(Comparator<Resume> resumeComparator) {
        Resume[] unsortedStorage = Arrays.copyOf(storage, size);
        Arrays.sort(unsortedStorage, resumeComparator);
        return List.of(unsortedStorage);
    }

    protected abstract void insert(int index, Resume r);

    protected abstract Integer findSearchKey(String uuid);

    protected abstract void fillEmpty(int index);
}
