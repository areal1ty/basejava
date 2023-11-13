package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (isValidForSave(uuid, index)) {
        shiftAndSave(index, r);
        System.out.println("Резюме " + uuid + " успешно добавлено");
        }
    }

    protected abstract void shiftAndSave(int index, Resume r);

    public final boolean isValidForSave(String uuid, int index){
        if (index > 0) {
            System.out.println("Ошибка. Резюме " + uuid + " уже находится в базе данных");
            return false;
        } else if (size >= storage.length) {
            System.out.println("Ошибка. База данных заполнена!");
            return false;
        }
        return true;
    }

    public final void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (isExist(uuid, index)) {
            storage[index] = r;
            System.out.println("Резюме " + uuid + " успешно обновлено");
        }
    }

    public final boolean isExist(String uuid, int index) {
        if (index < 0) {
            System.out.println("Резюме с UUID " + uuid + " не найдено. Попробуйте еще раз!");
            return false;
        }
        return true;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (isExist(uuid, index)) {
            shiftAndDelete(index);
            System.out.println("Резюме " + uuid + " успешно удалено");
        }
    }

    protected abstract void shiftAndDelete(int index);

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (!isExist(uuid,index)) {
            return null;
        }
        return storage[index];
    }

    protected abstract int findIndex(String uuid);

    public final int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

}
