package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не найдено. Попробуйте еще раз!");
            return null;
        }
        return storage[index];
    }

    protected abstract int findIndex(String uuid);

    public final int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

}
