package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void clear() {
        removeAll();
        System.out.println("База данных успешно очищена");
    }

    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (doesNotExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            replace(index, r);
            System.out.println("Резюме " + uuid + " успешно обновлено");
        }
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        if (isValid(r)) {
            saveElement(r);
            System.out.println("Резюме " + uuid + " успешно добавлено");
        }
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (doesNotExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getResume(index);
        }
    }

    public final boolean doesNotExist(int index) {
        return (index < 0);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (doesNotExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            removeElement(index);
            System.out.println("Резюме " + uuid + " успешно удалено");
        }
    }

    protected abstract boolean isValid(Resume r);
    protected abstract void saveElement(Resume r);
    protected abstract void removeAll();
    public abstract int size();
    protected abstract void removeElement(int index);
    protected abstract int findIndex(String uuid);
    protected abstract Resume getResume(int index);
    public abstract Resume[] getAll();
    protected abstract void replace(int index, Resume r);
}
