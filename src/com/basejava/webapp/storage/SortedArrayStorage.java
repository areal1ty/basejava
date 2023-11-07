package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " успешно обновлено");
        } else {
            System.out.println("Ошибка. Резюме " + r.getUuid() + " не найдено. Попробуйте еще раз");
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index < 0) {
            index = -(index + 1);
            storage[index] = r;
            size++;
            System.out.println("Резюме " + uuid + " успешно добавлено");
        } else if (size > storage.length) {
            System.out.println("Ошибка. База данных заполнена!");
        } else {
            System.out.println("Ошибка. Резюме " + uuid + " уже находится в базе данных");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с UUID " + uuid + " не найдено. Попробуйте еще раз!");
        } else {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
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
