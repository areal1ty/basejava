package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.basejava.webapp.test.ResumeTestData.createResume;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    private static final String DUMMY =  "dummy";
    protected static final File STORAGE_DIRECTORY = new File("C:\\Users\\User\\Projects\\basejava\\storage");
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid";

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;

    static {
        RESUME_1 = createResume(UUID_1, "Test Name");
        RESUME_2 = createResume(UUID_2, "Test Nameone");
        RESUME_3 = createResume(UUID_3, "Test Nametwo");
        RESUME_4 = createResume(UUID_4, "Test Namethree");
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_3);
        storage.save(RESUME_2);
        storage.save(RESUME_1);
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
        Resume updateResume = createResume(UUID_1, "Update Name");
        storage.update(updateResume);
        assertEquals(updateResume, storage.get(UUID_1));
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
    void getAllSorted() {
        List<Resume> actual = storage.getAllSorted();
        assertSize(3);
        assertEquals(actual, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }
}
