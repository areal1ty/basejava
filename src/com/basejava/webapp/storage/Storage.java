package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.List;
import java.util.Map;

/**
 * Array based storage for Resumes
 */

public interface Storage {

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    int size();

}
