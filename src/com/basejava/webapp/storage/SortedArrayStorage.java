package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (isExist(uuid, index)) {
            storage[index] = r;
            System.out.println("Резюме " + uuid + " успешно обновлено");
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (isValidForSave(uuid, index)) {
        index = -(index + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
        size++;
        System.out.println("Резюме " + uuid + " успешно добавлено");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (isExist(uuid, index)) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[size] = null;
            size--;
            System.out.println("Резюме " + uuid + " успешно удалено");
        }
    }

    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
        }
}
