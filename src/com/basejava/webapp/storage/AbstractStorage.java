package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SearchKey> implements Storage {
    protected final static Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void clear() {
        doClear();
        LOG.info("database cleared");
    }

    public void update(Resume r) {
        String uuid = r.getUuid();
        SearchKey searchKey = findExistingSearchKey(uuid);
        doUpdate(searchKey, r);
        LOG.info(r + " updated");
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        SearchKey searchKey = findNonExistingSearchKey(uuid);
        doSave(searchKey, r);
        LOG.info(r + " saved");
    }

    public final Resume get(String uuid) {
        LOG.info("got " + uuid);
        SearchKey searchKey = findExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        SearchKey searchKey = findExistingSearchKey(uuid);
        doDelete(searchKey);
        LOG.info(uuid + " deleted");
    }

    private SearchKey findExistingSearchKey(String uuid) {
        SearchKey searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    private SearchKey findNonExistingSearchKey(String uuid) {
        SearchKey searchKey = findSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    public final List<Resume> getAllSorted() {
        List<Resume> resumes = doGetAll();
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
    }

    protected abstract void doSave(SearchKey searchKey, Resume r);

    protected abstract void doClear();

    public abstract int size();

    protected abstract void doDelete(SearchKey searchKey);

    protected abstract SearchKey findSearchKey(String searchKey);

    protected abstract Resume doGet(SearchKey searchKey);

    protected abstract boolean isExist(SearchKey searchKey);

    protected abstract List<Resume> doGetAll();

    protected abstract void doUpdate(SearchKey searchKey, Resume r);
}
