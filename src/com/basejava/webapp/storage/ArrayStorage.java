package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    public void shiftAndSave(int index, Resume r) {
        storage[size] = r;
        size++;
    }

    public void shiftAndDelete(int index) {
        storage[index] = storage[size - 1];
        size--;
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

}
