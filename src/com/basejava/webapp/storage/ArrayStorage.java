package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        if (size > 0) {
            Arrays.fill(storage, 0, size, null);
            size = 0;
        } else {
            System.out.println("Ошибка. База данных пустая");
        }
    }

    public void update(Resume r) {
        int i = findIndex(r.getUuid());
        if (i != -1) {
            storage[i] = r;
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
        } else if (size > storage.length){
            System.out.println("Ошибка. База данных заполнена!");
        } else {
            System.out.println("Ошибка. Резюме " + r.getUuid() + " уже находится в базе данных");
        }
    }

    public Resume get(String uuid) {
        int i = findIndex(uuid);
        if (i == -1) {
            System.out.println("Резюме " + uuid + " не найдено. Попробуйте еще раз!");
            return null;
        }
        return storage[i];
    }

    public void delete(String uuid) {
        int i = findIndex(uuid);
        if (i == -1) {
            System.out.println("Резюме с UUID " + uuid + " не найдено. Попробуйте еще раз!");
        }
        else if (size == 0) {
            System.out.println("База данных пустая, удаление невозможно");
        } else {
        System.arraycopy(storage, i + 1, storage, i, size - 1);
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
