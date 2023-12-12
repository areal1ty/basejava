package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class MapStorage extends AbstractStorage{

    @Override
    protected void saveElement(Object index, Resume r) {

    }

    @Override
    protected void removeAll() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void removeElement(int index) {

    }

    @Override
    protected Integer findIndex(String uuid) {
        return 0;
    }

    @Override
    protected Resume getResume(int index) {
        return null;
    }

    @Override
    protected boolean isExist(Integer index) {
        return false;
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected void replace(int index, Resume r) {

    }
}
