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
    protected static final String UUID_4 = "uuid4";


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
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void saveIfExist() {
        try {
            storage.save(RESUME_3);
        } catch (ExistStorageException e) {
            assertThrows(ExistStorageException.class, () -> storage.save(RESUME_3));
        }
    }

    @Test
    void update() {
            storage.update(RESUME_1);
            assertSame(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void isExist() {
        try {
            storage.delete(UUID_1);
        } catch (NotExistStorageException e) {
            assertThrows(NotExistStorageException.class, () -> storage.get(RESUME_1.getUuid()));
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
        assertSize(0);
        Resume[] actual = storage.getAll();
        Resume[] expected = new Resume[0];
        assertArrayEquals(expected, actual);
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
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
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
        Resume[] actual = storage.getAll();
        assertEquals(3, storage.size());
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected, actual);
    }

}