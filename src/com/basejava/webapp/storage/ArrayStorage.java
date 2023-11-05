package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " успешно обновлено");
        } else {
            System.out.println("Ошибка. Резюме " + r.getUuid() + " не найдено. Попробуйте еще раз");
        }
    }

    public void save(Resume r) {
        if (findIndex(r.getUuid()) == -1) {
            storage[size] = r;
            size++;
            System.out.println("Резюме " + r.getUuid() + " успешно добавлено");
        } else if (size > storage.length) {
            System.out.println("Ошибка. База данных заполнена!");
        } else {
            System.out.println("Ошибка. Резюме " + r.getUuid() + " уже находится в базе данных");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " не найдено. Попробуйте еще раз!");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме с UUID " + uuid + " не найдено. Попробуйте еще раз!");
        } else {
            storage[index] = storage[size - 1];
            // System.arraycopy(storage, index + 1, storage, index, size - 1);
            size--;
            System.out.println("Резюме " + uuid + " успешно удалено");
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
