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


    public void save(Resume r) {
        // TODO check if resume is not present
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Ошибка. Хранилище заполнено!");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return storage[i];
        }
        return null;
    }

    public void delete(String uuid) {
        // TODO check if resume is not present
        if (size > 0) {
            for (int index = 0; index < size; index++) {
                if (storage[index].getUuid().equals(uuid)) {
                    System.arraycopy(storage, index + 1, storage, index, size - index);
                    size--;
                    break;
                }
            }
        }
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
