package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void clear() {
        removeAll();
        System.out.println("База данных успешно очищена");
    }

    public void update(Resume r) {
        String uuid = r.getUuid();
        Object index = findExistingIndex(uuid);
        replace((Integer) index, r);
        System.out.println("Резюме " + uuid + " успешно обновлено");
        }

    public void save(Resume r) {
        String uuid = r.getUuid();
        Object index = findNonExistingIndex(uuid);
        saveElement(index, r);
        System.out.println("Резюме " + uuid + " успешно добавлено");
    }

    public final Resume get(String uuid) {
        Object index = findExistingIndex(uuid);
        return getResume((Integer) index);
    }

    public void delete(String uuid) {
        Object index = findExistingIndex(uuid);
        removeElement((Integer) index);
        System.out.println("Резюме " + uuid + " успешно удалено");
        }

    private Object findExistingIndex(String uuid) {
        Object index = findIndex(uuid);
        if (!isExist((Integer) index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

    private Object findNonExistingIndex(String uuid) {
        Object index = findIndex(uuid);
        if (isExist((Integer) index)) {
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }
    }


    protected abstract void saveElement(Object index, Resume r);
    protected abstract void removeAll();
    public abstract int size();
    protected abstract void removeElement(int index);
    protected abstract Object findIndex(String uuid);
    protected abstract Resume getResume(int index);
    protected abstract boolean isExist(Integer index);
    public abstract Resume[] getAll();
    protected abstract void replace(int index, Resume r);
}
