package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public void clear() {
        doClear();
        System.out.println("База данных успешно очищена");
    }

    public void update(Resume r) {
        String uuid = r.getUuid();
        Object searchKey = findExistingIndex(uuid);
        doUpdate(searchKey, r);
        System.out.println("Резюме " + uuid + " успешно обновлено");
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        Object searchKey = findNonExistingIndex(uuid);
        doSave(searchKey, r);
        System.out.println("Резюме " + uuid + " успешно добавлено");
    }

    public final Resume get(String uuid) {
        Object searchKey = findExistingIndex(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = findExistingIndex(uuid);
        doDelete(searchKey);
        System.out.println("Резюме " + uuid + " успешно удалено");
    }

    private Object findExistingIndex(String uuid) {
        Object searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    private Object findNonExistingIndex(String uuid) {
        Object searchKey = findSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    public final List<Resume> getAllSorted() {
        Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        return sortResumes(resumeComparator);
    }

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract void doClear();

    public abstract int size();

    protected abstract void doDelete(Object searchKey);

    protected abstract Object findSearchKey(String searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> sortResumes(Comparator<Resume> comparator);

    protected abstract void doUpdate(Object searchKey, Resume r);
}
