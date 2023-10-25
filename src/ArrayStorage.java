/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        while (size() != 0) {
            delete(storage[0].uuid);
        }
    }

    void save(Resume r) {
        int size = size();
        if (size < storage.length) {
            storage[size] = r;
        } else {
            System.out.println("Ошибка. Хранилище заполнено!");
        }
    }

    Resume get(String uuid) {
        int left = 0, right = size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int res = uuid.compareTo(storage[mid].uuid);
            if (res == 0) return storage[mid];
            else if (res > 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    void delete(String uuid) {
        if (size() > 0) {
            int index = -1;
            for (int i = 0; i < size(); i++) {
                if (storage[i].uuid.equals(uuid)) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size() - index);
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[size()];
        System.arraycopy(storage, 0, res, 0, size());
        return res;
    }

    int size() {
        int size = 0;
        for (Resume i : storage) {
            if (i != null) {
                size++;
            }
        }
        return size;
    }
}
