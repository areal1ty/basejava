package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractStorageTest {
    private static final String DUMMY =  "dummy";
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid";


    public static final Resume RESUME_1 = new Resume(UUID_1);
    public static final Resume RESUME_2 = new Resume(UUID_2);
    public static final Resume RESUME_3 = new Resume(UUID_3);
    public static final Resume RESUME_4 = new Resume(UUID_4);

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void saveIfExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    void update() {
        Resume RESUME_UPDATE = new Resume(UUID_1);
        storage.update(RESUME_UPDATE);
        assertSame(RESUME_UPDATE, storage.get(UUID_1));
    }

    @Test
    void delete() {
        storage.delete(UUID_3);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3));
        assertSize(2);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        List<Resume> actual = storage.getAllSorted();
        List<Resume> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(DUMMY));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    @Test
    void getAll() {
        List<Resume> actual = storage.getAllSorted();
        assertSize(3);
        List<Resume> expected = new ArrayList<>();
        Collections.addAll(expected, RESUME_1, RESUME_2, RESUME_3);
        assertEquals(expected, actual);
    }
}
