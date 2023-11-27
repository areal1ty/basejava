package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    protected final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    public Resume testResume1 = new Resume(UUID_1);
    Resume testResume2 = new Resume(UUID_2);
    Resume testResume3 = new Resume(UUID_3);

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(testResume1);
        storage.save(testResume2);
        storage.save(testResume3);
    }

    @Test
    void storageOverflow() {
        try {
            for (int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("u" + i));
            }
            fail("Unexpected overflow");
        } catch (StorageException e) {
            Resume lastResume = new Resume("lastResume");
            assertThrows(StorageException.class, () -> storage.save(lastResume));
        }
    }

    @Test
    public void save() {
        Resume resultResume = storage.get(testResume1.getUuid());
        assertEquals(testResume1, resultResume);
    }

    @Test
    public void saveIfExist() {
        try {
            storage.save(testResume3);
        } catch (ExistStorageException e) {
            assertThrows(ExistStorageException.class, () -> storage.save(testResume3));
        }
    }

    @Test
    void update() {
        try {
            storage.delete(testResume1.getUuid());
            storage.update(testResume1);
        } catch (NotExistStorageException e) {
            assertThrows(NotExistStorageException.class, () -> storage.get(testResume1.getUuid()));
        }
    }

    @Test
    void isExist() {
        try {
            storage.delete(UUID_1);
        } catch (NotExistStorageException e) {
            assertThrows(NotExistStorageException.class, () -> storage.get(testResume1.getUuid()));
        }
    }

    @Test
    void delete() {
        String UUID12 = "testUuid";
        try {
            Resume testResume = new Resume(UUID12);
            storage.save(testResume);
            storage.delete(UUID12);
        } catch (NotExistStorageException e) {
            storage.get(UUID12);
            assertThrows(NotExistStorageException.class, () -> storage.get(UUID12));
        }
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void get() {
        Resume r = storage.get(UUID_3);
        assertEquals(r, testResume3);
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void getAll() {
        Resume[] result = storage.getAll();
        assertEquals(3, storage.size());
        for (int i = 1; i < storage.size(); i++) {
            assertEquals(storage.get("uuid" + i), result[i - 1]);
        }
    }

}