package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(Object index, Resume r) {
        storage.add(r);
    }

    @Override
    protected final void doClear() {
        storage.clear();
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) return i;
        }
        return null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    public final boolean isExist(Object searchKey) {
        return (searchKey != null);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public final List<Resume> sortResumes(Comparator<Resume> resumeComparator) {
        storage.sort(resumeComparator);
        return storage;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.set((Integer) searchKey, r);
    }
}
