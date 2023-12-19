package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public class MapFullNameStorage extends AbstractStorage{
    @Override
    protected void doSave(Object searchKey, Resume r) {

    }

    @Override
    protected void doClear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected Object findSearchKey(String uuid) {
        return null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected List<Resume> sortResumes(Comparator<Resume> comparator) {
        return null;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {

    }
}
