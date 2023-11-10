package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = r;
            System.out.println("Резюме " + uuid + " успешно обновлено");
        } else {
            System.out.println("Ошибка. Резюме " + uuid + " не найдено. Попробуйте еще раз");
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index > 0) {
            System.out.println("Ошибка. Резюме " + uuid + " уже находится в базе данных");
            return;
        } else if (size >= storage.length) {
            System.out.println("Ошибка. База данных заполнена!");
            return;
        }
        index = -(index + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
            /*
                for (int i = size - 1; i >= index; i--) {
                    storage[i + 1] = storage[i];
                }

             */
        storage[index] = r;
        size++;
        System.out.println("Резюме " + uuid + " успешно добавлено");
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с UUID " + uuid + " не найдено. Попробуйте еще раз!");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            /* for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }

             */
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
