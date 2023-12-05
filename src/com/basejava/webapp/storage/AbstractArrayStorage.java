package com.basejava.webapp.storage;
import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        } else if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        } else {
            insert(index, r);
            size++;
            System.out.println("Резюме " + uuid + " успешно добавлено");
        }
    }

    public final void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (isExist(index)) {
            storage[index] = r;
            System.out.println("Резюме " + uuid + " успешно обновлено");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final boolean isExist(int index) {
        return (index >= 0);
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (isExist(index)) {
            fillEmpty(index);
            size--;
            System.out.println("Резюме " + uuid + " успешно удалено");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("База данных успешно очищена");
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }


    public final int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void insert(int index, Resume r);

    protected abstract int findIndex(String uuid);

    protected abstract void fillEmpty(int index);
}
