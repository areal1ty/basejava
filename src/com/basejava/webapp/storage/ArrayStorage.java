package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {
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

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме с UUID " + uuid + " не найдено. Попробуйте еще раз!");
        } else {
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
