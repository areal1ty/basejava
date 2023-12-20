package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void insert(int index, Resume r) {
        storage[size] = r;
    }

    @Override
    public void fillEmpty(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Integer findSearchKey(String searchKey) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(searchKey)) return i;
        }
        return -1;
    }

}
