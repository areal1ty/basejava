package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        if (size > 0) {
          for (int i = 0; i < size; i++) {
              storage[i] = null;
          }
          size = 0;
        }
    }

    public void update(Resume r) {

    }
    public void save(Resume r) {
        // TODO check if resume is not present
        // size < storage.length
        if (findIndex(r.getUuid()) == -1) {
            storage[size] = r;
            size++;
            System.out.println("Резюме " + r.getUuid() + " успешно добавлено");
        } else if (size < storage.length){
            System.out.println("Ошибка. База данных заполнена!");
        } else {
            System.out.println("Ошибка. Резюме " + r.getUuid() + " уже находится в базе данных");
        }
    }

    public Resume get(String uuid) {
        int i = findIndex(uuid);
        if (i == -1) {
            System.out.println("Резюме " + uuid + " не найдено. Попробуйте еще раз!");
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
        }
        System.arraycopy(storage, i + 1, storage, i, size - 1);
        size--;
        System.out.println("Резюме " + uuid + " успешно удалено");
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
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    public int size() {
        return size;
    }
}
