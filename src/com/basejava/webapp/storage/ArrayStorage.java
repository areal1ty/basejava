package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {
    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (isExist(uuid, index)) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " успешно обновлено");
        }
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (isValidForSave(uuid, index)) {
            storage[size] = r;
            size++;
            System.out.println("Резюме " + uuid + " успешно добавлено");
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (isExist(uuid, index)) {
            storage[index] = storage[size - 1];
            size--;
            System.out.println("Резюме " + uuid + " успешно удалено");
        }
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

}
